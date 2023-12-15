package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import model.FileMetadata.FileMetadata;
import model.processoseletivo.Fase;
import model.processoseletivo.ProcessoSeletivo;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import model.usuario.Usuario;
import util.TwitterBean;

@Named
@ViewScoped
public class NovaPublicacaoController implements Serializable {

    private List<Publicacao> publicacoes;

    private Publicacao selectedPublicacao;

    private List<Publicacao> selectedPublicacoes;

    @Inject
    private PublicacaoServiceLocal publicacaoService;

    @Inject
    UsuarioController usuarioController;

    @Inject
    TwitterBean twitterBean;

    @PostConstruct
    public void init() {
        this.publicacoes = this.publicacaoService.getAllPublicacoes();
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public Publicacao getSelectedPublicacao() {
        return selectedPublicacao;
    }

    public void setSelectedPublicacao(Publicacao selectedPublicacao) {
        this.selectedPublicacao = selectedPublicacao;
    }

    public List<Publicacao> getSelectedPublicacoes() {
        return selectedPublicacoes;
    }

    public void setSelectedPublicacoes(List<Publicacao> selectedPublicacoes) {
        this.selectedPublicacoes = selectedPublicacoes;
    }

    public void openNew() {
        this.selectedPublicacao = new Publicacao();
    }

    public FileMetadata uploadFile() throws NoSuchAlgorithmException {
        FileMetadata fileMetadata = new FileMetadata();
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        String basePath = "C:\\uploads\\";

        String uploadDir = basePath + File.separator;

        try {
            Part filePart = request.getPart("filepicker");
            if (filePart == null) {
                return null;
            }
            System.out.println("PART: " + filePart.getSubmittedFileName());
            System.out.println("PATH: " + uploadDir);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            File uploads = new File(uploadDir);
            if (!uploads.exists() && !uploads.mkdirs()) {
                throw new IOException("Unable to create upload directory");
            }

            File file = new File(uploads, fileName);

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

                fileMetadata.setFileName(fileName);
                fileMetadata.setFilePath(basePath + fileName);
                fileMetadata.setFileSize(file.length());
                fileMetadata.setMimeType(Files.probeContentType(file.toPath())); // Get MIME type
                fileMetadata.setHashCode(computeFileHash(file)); // Compute file hash if needed

                context.addMessage(null, new FacesMessage("File upload was successful."));
            } catch (IOException e) {
                context.addMessage(null, new FacesMessage("File upload was unsuccessful."));
            }
        } catch (IOException | ServletException e) {
            // Tratar exception
        }
        return fileMetadata;
    }

    private String computeFileHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (InputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public void savePublicacao() throws Exception {

        if (this.selectedPublicacao.getAutor() == null) {
            this.selectedPublicacao.setAutor(usuarioController.getCurrentUser().getUsername());
            this.selectedPublicacao.setDataPublicacao(Calendar.getInstance().getTime());
            this.selectedPublicacao.setDataEdicao(null);
            List<Publicacao> publicacoes = publicacaoService.getAllPublicacoes();

            if (publicacoes != null && !publicacoes.isEmpty()) {
                Long lastId = publicacoes.get(publicacoes.size() - 1).getId();
                this.selectedPublicacao.setLinkTwitter(twitterBean.postTwitter(
                        "Nova publicação em nosso site Processos Seletivos! Acesse através do link: "
                        + "http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=" + (lastId + 1)
                ));
            } else {
                this.selectedPublicacao.setLinkTwitter(twitterBean.postTwitter(
                        "Nova publicação em nosso site Processos Seletivos! Acesse através do link: "
                        + "http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=1"
                ));
            }
            this.selectedPublicacao.setTrash(false);
            this.selectedPublicacao.setFileMetadata(uploadFile());
            System.out.println("TIPO EDITAL " + this.selectedPublicacao.getTipo());
            if (this.selectedPublicacao.getTipo() == Publicacao.TipoPublicacao.EDITAL) {
                ProcessoSeletivo processoSeletivo = new ProcessoSeletivo();
                processoSeletivo.setFase(Fase.INSCRICAO);
                this.selectedPublicacao.setProcessoSeletivo(processoSeletivo);
            } else {
                // TODO temporario
                ProcessoSeletivo processoSeletivo = new ProcessoSeletivo();
                List<Usuario> candidatos = new ArrayList<>();
                processoSeletivo.setCandidatos(candidatos);
                this.selectedPublicacao.setProcessoSeletivo(processoSeletivo);
            }

            this.publicacoes.add(this.selectedPublicacao);
            publicacaoService.savePublicacao(this.selectedPublicacao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação Adicionada"));
        } else {
            this.selectedPublicacao.setDataEdicao(Calendar.getInstance().getTime());
            publicacaoService.savePublicacao(this.selectedPublicacao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação Atualizada"));
        }
        PrimeFaces.current().executeScript("PF('managePublicacaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-publicacoes");
    }

    public void deletePublicacao() {
        this.publicacoes.remove(this.selectedPublicacao);
        this.selectedPublicacao = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação Removida"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-publicacoes");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedPublicacoes()) {
            int size = this.selectedPublicacoes.size();
            return size > 1 ? size + " publicacoes selecionadas" : "1 publicacao selecionada";
        }

        return "Remover";
    }

    public boolean hasSelectedPublicacoes() {
        return this.selectedPublicacoes != null && !this.selectedPublicacoes.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.publicacoes.removeAll(this.selectedPublicacoes);
        this.selectedPublicacoes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicacoes Removidas"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-publicacoes");
    }
}

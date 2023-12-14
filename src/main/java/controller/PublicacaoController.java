package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Lob;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import util.TwitterBean;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped
public class PublicacaoController implements Serializable {

    @Inject
    PublicacaoServiceLocal publicacaoService;

    @Inject
    UsuarioController usuarioController;

    // TODO mudar twitter bean para um twitter service
    @Inject
    TwitterBean twitterBean;

    @Lob
    private String text;

    private String titulo;

    private String tipo;

    private List<Publicacao> publicacoes;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        tipo = context.getExternalContext().getRequestParameterMap().get("tipo");
        if (tipo != null && !tipo.isEmpty()) {
            publicacoes = publicacaoService.getPublicacoesByTipo(Publicacao.TipoPublicacao.valueOf(tipo.toUpperCase()));
        }
        System.out.println(publicacoes);
        System.out.println(tipo);
    }

    public void publicar() throws Exception {
        Publicacao publicacao = new Publicacao();
        LocalDate localdate = LocalDate.now();

        publicacao.setAutor(usuarioController.getCurrentUser().getUsername());
        publicacao.setConteudo(text);
        publicacao.setDataPublicacao(java.sql.Date.valueOf(localdate));
        publicacao.setDataEdicao(null);
        if (publicacoes != null) {
            publicacao.setLinkTwitter(twitterBean.postTwitter("Nova publicação em nosso site Processos Seletivos! Acesse através do link: http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=" + publicacoes.size() + 1));
        } else {
            publicacao.setLinkTwitter(twitterBean.postTwitter("Nova publicação em nosso site Processos Seletivos! Acesse através do link: http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=1"));
        }
        publicacao.setTipo(Publicacao.TipoPublicacao.valueOf(tipo.toUpperCase()));
        publicacao.setTitulo(titulo);
        publicacao.setTrash(false);

        publicacaoService.savePublicacao(publicacao);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void imprimeTexto() {
        System.out.println(this.getText());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void uploadFile() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        String basePath = externalContext.getRealPath("/");

        String uploadDir = basePath + "\\uploads" + File.separator;

        try {
            Part filePart = request.getPart("filepicker");
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
                context.addMessage(null, new FacesMessage("File upload was successful."));
            } catch (IOException e) {
                context.addMessage(null, new FacesMessage("File upload was unsuccessful."));
            }
        } catch (IOException | ServletException e) {
            // Handle the exception
        }
    }
}

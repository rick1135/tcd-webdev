package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import model.usuario.Usuario;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rktds
 */
@Named
@ViewScoped
public class PublicacaoDetailController implements Serializable {

    @Inject
    PublicacaoServiceLocal publicacaoService;

    private Publicacao publicacao;
    private StreamedContent file;

    private Long id;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        id = Long.valueOf(context.getExternalContext().getRequestParameterMap().get("idPublicacao"));
        System.out.println("ID>>" + id);
        if (id != null) {
            publicacao = publicacaoService.findPublicacaoById(id);
        }
        System.out.println("Publicacao>> " + publicacao.getTitulo());
    }

    public void downloadFile() {
        System.out.println("Publicacao@@: " + publicacao);
        System.out.println("Metadata@@: " + publicacao.getFileMetadata());
        try {
            if (publicacao != null && publicacao.getFileMetadata() != null) {
                System.out.println("FilePath@@: " + publicacao.getFileMetadata().getFilePath());
                File fileToDownload = new File(publicacao.getFileMetadata().getFilePath());
                InputStream input = new FileInputStream(fileToDownload);
                file = DefaultStreamedContent.builder()
                        .name(publicacao.getFileMetadata().getFileName())
                        .contentType(publicacao.getFileMetadata().getMimeType())
                        .stream(() -> input)
                        .build();
                System.out.println("File: " + file);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No file available for download."));
                PrimeFaces.current().ajax().addCallbackParam("success", false);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Download failed."));
            PrimeFaces.current().ajax().addCallbackParam("success", false);
        }
    }

    public StreamedContent getFile() {
        if (FacesContext.getCurrentInstance().getRenderResponse()) {
            return new DefaultStreamedContent();
        } else {
            System.out.println("File@@: " + file);
            return file;
        }
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    public void addCandidato(Usuario candidato) {
        List<Usuario> candidatos = this.publicacao.getProcessoSeletivo().getCandidatos();

        if (!candidatos.contains(candidato)) {
            candidatos.add(candidato);
            publicacaoService.savePublicacao(this.publicacao);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Você agora está participando do processo seletivo."));
        }
    }

    public boolean isUserParticipante(Usuario currentUser) {
        return publicacao.getProcessoSeletivo().getCandidatos().contains(currentUser);
    }

}

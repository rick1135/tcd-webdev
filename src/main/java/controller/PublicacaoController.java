package controller;

import java.io.Serializable;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import controller.UsuarioController;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Lob;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;

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

    @Inject
    FacesContext facesContext;

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
    }

    // private String tipo;
    public void publicar() {
        Publicacao publicacao = new Publicacao();
        LocalDate localdate = LocalDate.now();

        publicacao.setAutor(usuarioController.getCurrentUser().getUsername());
        publicacao.setConteudo(text);
        publicacao.setDataPublicacao(java.sql.Date.valueOf(localdate));
        publicacao.setDataEdicao(null);
        // TODO api do twitter
        publicacao.setLinkTwitter(null);
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

//    private ExternalContext getExternalContext() {
//        return facesContext.getExternalContext();
//    }

//    public String getFromURL() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        String viewId = context.getViewRoot().getViewId();
//
//        String noticia = viewId.substring(1, viewId.lastIndexOf('.'));
//
//        return noticia;
//    }
    
//    public String navigateToPublicacao() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        String tipo = context.getExternalContext().getRequestParameterMap().get("tipo");
//
//        return "publicacao.xhtml?faces-redirect=true&tipo=" + tipo;
//    }
}
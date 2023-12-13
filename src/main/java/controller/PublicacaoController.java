package controller;

import java.io.Serializable;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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

    // TODO mudar twitter bean para um twitter service
//    @Inject
//    TwitterBean twitterBean;
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

    public void publicar() throws Exception {
        Publicacao publicacao = new Publicacao();
        LocalDate localdate = LocalDate.now();

        publicacao.setAutor(usuarioController.getCurrentUser().getUsername());
        publicacao.setConteudo(text);
        publicacao.setDataPublicacao(java.sql.Date.valueOf(localdate));
        publicacao.setDataEdicao(null);
//        if(publicacoes != null)
//            publicacao.setLinkTwitter(twitterBean.postTwitter("Nova publicação em nosso site Processos Seletivos! Acesse através do link: http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=" + publicacoes.size() + 1));
//        else
//            publicacao.setLinkTwitter(twitterBean.postTwitter("Nova publicação em nosso site Processos Seletivos! Acesse através do link: http://localhost:8080/publicacaoDetail.xhtml?idPublicacao=1"));
        publicacao.setLinkTwitter("Teste");
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

package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped
public class PublicacaoDetailController implements Serializable {

    @Inject
    PublicacaoServiceLocal publicacaoService;

    private Publicacao publicacao;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = Long.parseLong(context.getExternalContext().getRequestParameterMap().get("idPublicacao"));
        System.out.println("ID>>" + id);
        if (id != null) {
            publicacao = publicacaoService.findPublicacaoById(id);
        }
        System.out.println("Publicacao>> " + publicacao.getTitulo());
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

}

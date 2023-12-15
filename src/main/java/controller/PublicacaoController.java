package controller;

import java.io.Serializable;
import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import javax.inject.Named;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped
public class PublicacaoController implements Serializable {

    @Inject
    PublicacaoServiceLocal publicacaoService;

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

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }
}

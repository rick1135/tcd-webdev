package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.processoseletivo.ProcessoSeletivo;
import model.processoseletivo.ProcessoSeletivoServiceLocal;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import model.usuario.Usuario;
import org.primefaces.model.ResponsiveOption;

/**
 *
 * @author rktds
 */
@Named
@SessionScoped
public class ParticipanteController implements Serializable {

    @Inject
    ProcessoSeletivoServiceLocal processoService;

    @Inject
    PublicacaoServiceLocal publicacaoService;

    @Inject
    UsuarioController usuarioController;

    private List<Publicacao> newsletter;
    private List<ProcessoSeletivo> processosSeletivos;

    private List<ResponsiveOption> responsiveOptions;

    @PostConstruct
    public void init() {

        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));

        Usuario currentUser = usuarioController.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getNewsletter()) {
                newsletter = publicacaoService.getNoticiasUltimosQuinzeDias();
                System.out.println("Newsletter carregada: " + newsletter.get(0));
            }
            processosSeletivos = processoService.findByUsuarioId(currentUser.getId());
        } else {
            processosSeletivos = new ArrayList<>();
        }
    }

    public List<Publicacao> getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(List<Publicacao> newsletter) {
        this.newsletter = newsletter;
    }

    public List<ProcessoSeletivo> getProcessosSeletivos() {
        return processosSeletivos;
    }

    public void setProcessosSeletivos(List<ProcessoSeletivo> processosSeletivos) {
        this.processosSeletivos = processosSeletivos;
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }
}

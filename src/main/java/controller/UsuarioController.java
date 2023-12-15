package controller;

/**
 *
 * @author rktds
 */
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import model.publicacao.Publicacao;
import model.publicacao.PublicacaoServiceLocal;
import model.usuario.Usuario;
import model.usuario.UsuarioServiceLocal;

@Named
@RequestScoped
public class UsuarioController implements Serializable {

    @Inject
    UsuarioServiceLocal dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    @Inject
    PublicacaoServiceLocal publicacaoService;

//    @Inject
//    private MailServiceLocal mailService;
    private Usuario currentUser;

    private List<Publicacao> newsletter;

    @PostConstruct
    public void initialize() {
        if (securityContext.getCallerPrincipal() != null) {
            String email = securityContext.getCallerPrincipal().getName();
            this.currentUser = dataService.getUser(email);
            if (currentUser.getNewsletter()) {
                this.newsletter = publicacaoService.getNoticiasUltimosQuinzeDias();
            }
            if (this.currentUser == null) {
                System.out.println("Usuario null");
            }
        } else {
            this.currentUser = null;
        }
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public List<Publicacao> getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(List<Publicacao> newsletter) {
        this.newsletter = newsletter;
    }

    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    public boolean isAllowedToSeeAdministrador() {
        return securityContext.isCallerInRole("Administrador");
    }

    public boolean isAllowedToSeeCandidato() {
        return securityContext.isCallerInRole("Candidato");
    }

    public boolean isAllowedToSeeEditor() {
        return securityContext.isCallerInRole("Editor");
    }

    public String logout() throws ServletException {
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}

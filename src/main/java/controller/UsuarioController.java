package controller;

/**
 *
 * @author rktds
 */
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import model.usuario.Usuario;
import util.DataServiceLocal;

@Named
@RequestScoped
public class UsuarioController {

    @Inject
    DataServiceLocal dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    private Usuario currentUser;

    @PostConstruct
    public void initialize() {
        if (securityContext.getCallerPrincipal() != null) {
            String username = securityContext.getCallerPrincipal().getName();
            this.currentUser = dataService.getUser(username);
        } else {
            this.currentUser = null;
        }
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    public boolean isAllowedToSeeAdmin() {
        return securityContext.isCallerInRole("admin");
    }
    
    public boolean isAllowedToSeeUser() {
        return securityContext.isCallerInRole("user");
    }

    public String logout() throws ServletException {
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}

package model.usuario;

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
<<<<<<< HEAD
=======
import model.usuario.Usuario;
>>>>>>> caff1bff7139d7cc1b8c05e8f4bbbbf48a29ddad
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
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    public boolean isAllowedToSeeUsers() {
        return securityContext.isCallerInRole("admin");
    }

    public String logout() throws ServletException {
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}
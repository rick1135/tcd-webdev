package controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.usuario.Usuario;
import model.usuario.UsuarioServiceLocal;

/**
 *
 * @author rktds
 */
@Named
@ViewScoped
public class CadastroController implements Serializable {

    @Inject
    UsuarioServiceLocal dataService;

    @Inject
    FacesContext facesContext;
    
    @Inject
    UsuarioController currentUser;
    
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String cadastrar() {
        try {
            dataService.createUser(this.usuario.getUsername(), this.usuario.getEmail(), this.usuario.getPassword(), this.usuario.getGroup(), this.usuario.getNewsletter());
            if(currentUser != null)
                currentUser.logout();
            return "/login?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tente outro usuário ou email", null));
            return null;
        }
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }
    
    public CadastroController() {
        init();
    }
}

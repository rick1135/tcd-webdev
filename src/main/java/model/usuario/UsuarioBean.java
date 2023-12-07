package model.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.processoseletivo.ProcessoSeletivo;

/**
 *
 * @author rktds
 */
@Named
@SessionScoped
public class UsuarioBean implements Serializable {

    @Inject
    UsuarioController userController;

    Usuario usuario;

    private String nomeUsuario;
    private boolean newsLetter;

    @PostConstruct
    public void init() {
        usuario = userController.getCurrentUser();
        this.nomeUsuario = usuario.getUsername();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
    //</editor-fold>
}

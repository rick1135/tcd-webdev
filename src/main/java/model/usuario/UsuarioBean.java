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
    private List<ProcessoSeletivo> processosSeletivosAtivos;
    private List<ProcessoSeletivo> processosSeletivosFinalizados;
    private List<ProcessoSeletivo> processosSeletivosCadastrados;
    private boolean newsLetter;

    @PostConstruct
    public void init() {
        usuario = userController.getCurrentUser();
        this.nomeUsuario = usuario.getUsername();
        this.processosSeletivosCadastrados = usuario.getProcessosSeletivos();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<ProcessoSeletivo> getProcessosSeletivosAtivos() {
        return processosSeletivosAtivos;
    }

    public void setProcessosSeletivosAtivos(List<ProcessoSeletivo> processosSeletivosAtivos) {
        this.processosSeletivosAtivos = processosSeletivosAtivos;
    }

    public List<ProcessoSeletivo> getProcessosSeletivosFinalizados() {
        return processosSeletivosFinalizados;
    }

    public void setProcessosSeletivosFinalizados(List<ProcessoSeletivo> processosSeletivosFinalizados) {
        this.processosSeletivosFinalizados = processosSeletivosFinalizados;
    }

    public List<ProcessoSeletivo> getProcessosSeletivosCadastrados() {
        return processosSeletivosCadastrados;
    }

    public void setProcessosSeletivosCadastrados(List<ProcessoSeletivo> processosSeletivosCadastrados) {
        this.processosSeletivosCadastrados = processosSeletivosCadastrados;
    }

    public boolean isNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
    //</editor-fold>
}

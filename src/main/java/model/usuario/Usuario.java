package model.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.JpaEntity;
import model.processoseletivo.ProcessoSeletivo;

@NamedQueries({
    @NamedQuery(
            name = "Usuario.findByUsername",
            query = "SELECT u FROM Usuario u WHERE u.username = :username"
    ),
    @NamedQuery(
            name = "Usuario.all",
            query = "select us from Usuario us "
            + "order by us.id")
})
@Entity
public class Usuario extends JpaEntity implements Serializable {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_group", nullable = false)
    private String group;

    @Column(name = "newsletter")
    private boolean newsletter = false;

    @Column(name = "processos_seletivos")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessoSeletivo> processosSeletivos;

    public Usuario() {
    }

    public Usuario(String username, String email, String password, String group, List<ProcessoSeletivo> processoSeletivo) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.group = group;
        this.processosSeletivos = processoSeletivo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public List<ProcessoSeletivo> getProcessosSeletivos() {
        return processosSeletivos;
    }

    public void setProcessosSeletivos(List<ProcessoSeletivo> processosSeletivos) {
        this.processosSeletivos = processosSeletivos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "Usuario [id=" + this.getId() + "]";
    }

}

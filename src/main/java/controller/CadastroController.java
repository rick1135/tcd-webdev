package controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;
import util.DataServiceLocal;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped // Talvez tenha que ser outro tipo de escopo, pensar nisso depois
public class CadastroController implements Serializable {

    @Inject
    DataServiceLocal dataService;

    @Inject
    FacesContext facesContext;

    private String nome;
    @Email(message = "O Email não é válido.")
    private String email;
    @Length(min = 8, max = 16, message = "Senha deve conter de 8 a 16 caracteres.")
    private String senha;

    private boolean newsLetter;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(boolean newsLetter) {
        this.newsLetter = newsLetter;
    }

    public String cadastrar() {
        try {
            dataService.createUser(this.nome, this.email, this.senha, "user", this.newsLetter);
            return "/login?faces-redirect=true"; // Redirect on success
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tente outro usuário ou email", null));
            return null; // Stay on the same page
        }
    }

    public CadastroController() {
    }
}

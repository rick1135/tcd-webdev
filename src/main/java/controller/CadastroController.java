package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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

    public void cadastrar() throws IOException {
        dataService.createUser(this.nome, this.email, this.senha, "user");
        getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/login.xhtml");
    }

    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }

    public CadastroController() {
    }
}

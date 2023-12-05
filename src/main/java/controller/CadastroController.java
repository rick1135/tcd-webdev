package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Email;
import model.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import util.DataServiceBeanLocal;

/**
 *
 * @author rktds
 */
@Named
@SessionScoped // Talvez tenha que ser outro tipo de escopo, pensar nisso depois
public class CadastroController implements Serializable{
    
    @Inject
    DataServiceBeanLocal dataService;
    
    @Inject
    FacesContext facesContext;
    
    private Usuario user;
    
    private String nome;
    @Email(message = "O Email não é válido.")
    private String email;
    @Length(min=8, max=16, message = "Senha deve conter de 8 a 16 caracteres.")
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
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public void cadastrar() throws IOException{
        this.setUser(dataService.createUser(this.nome, this.email, this.senha, "user", null));
        getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/login.xhtml");
    }

    public void imprimeUsuario(){
        System.out.println(this.getUser());
        System.out.println(this.getUser().getUsername());
    }
    
    private ExternalContext getExternalContext() {
        return facesContext.getExternalContext();
    }
    
    public CadastroController() {
    }
}

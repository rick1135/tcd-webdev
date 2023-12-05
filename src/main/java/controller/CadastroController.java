package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
    
    public void cadastrar(){
        this.setUser(dataService.createUser(this.nome, this.email, this.senha, "user", null));
    }

    public void imprimeUsuario(){
        System.out.println(this.getUser());
        System.out.println(this.getUser().getUsername());
    }
    
    public CadastroController() {
    }
}

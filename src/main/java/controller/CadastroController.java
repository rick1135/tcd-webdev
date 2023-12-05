package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped // Talvez tenha que ser outro tipo de escopo, pensar nisso depois
public class CadastroController {

    private String nome;
    private String email;
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

    public void printEmail() {
        System.out.println("O email eh: " + this.email);
    }

    public CadastroController() {
    }
}

package model.publicacao;

import java.io.Serializable;
import java.time.LocalDate;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.usuario.UsuarioController;

/**
 *
 * @author rktds
 */
@Named
@RequestScoped
public class PublicacaoController implements Serializable {

    @Inject
    PublicacaoServiceLocal publicacaoService;

    @Inject
    UsuarioController usuarioController;

    private String text;

    private String titulo;
    
    // private String tipo;

    public void publicar() {
        Publicacao publicacao = new Publicacao();
        LocalDate localdate = LocalDate.now();

        publicacao.setAutor(usuarioController.getCurrentUser().getUsername());
        publicacao.setConteudo(text);
        publicacao.setDataPublicacao(java.sql.Date.valueOf(localdate));
        publicacao.setDataEdicao(null);
        // TODO api do twitter
        publicacao.setLinkTwitter(null);
        // TODO tipo deve ser selecioado em uma caixinha de selecao
        publicacao.setTipo(Publicacao.TipoPublicacao.Noticia);
        publicacao.setTitulo(titulo);
        publicacao.setTrash(false);
        
        publicacaoService.savePublicacao(publicacao);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void imprimeTexto() {
        System.out.println(this.getText());
    }
}

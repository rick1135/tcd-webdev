package model.noticia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import model.JpaEntity;

/**
 *
 * @author rktds
 */
@Entity
public class Noticia extends JpaEntity implements Serializable {

    @Column(nullable = false)
    private String titulo;

    @Lob
    @Column(nullable = false)
    private String conteudo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataPublicacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEdicao;

    public Noticia() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Date getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Date dataEdicao) {
        this.dataEdicao = dataEdicao;
    }
}

package model.publicacao;

import model.usuario.Usuario;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import model.JpaEntity;

@Entity
public class Publicacao extends JpaEntity{

    public enum TipoPublicacao {
        Noticia, Edital, Orientacao, Gabarito, Prova;
    }

    private String titulo;
    private String conteudo;
    private Date dataPublicacao;
    private Date dataEdicao;

    @Enumerated(EnumType.STRING)
    private TipoPublicacao tipo;

    // TODO mapear o autor as publicacoes
    private String autor;

    private String linkTwitter;

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

    public TipoPublicacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoPublicacao tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLinkTwitter() {
        return linkTwitter;
    }

    public void setLinkTwitter(String linkTwitter) {
        this.linkTwitter = linkTwitter;
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
        Publicacao other = (Publicacao) obj;
        return Objects.equals(other.getId(), this.getId());
    }

    @Override
    public String toString() {
        return "Publicacao [id=" + this.getId() + "]";
    }

}

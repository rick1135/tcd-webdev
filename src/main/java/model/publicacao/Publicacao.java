package model.publicacao;

import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import model.FileMetadata.FileMetadata;
import model.JpaEntity;
import model.processoseletivo.ProcessoSeletivo;

@Entity
public class Publicacao extends JpaEntity {

    public enum TipoPublicacao {
        NOTICIA, EDITAL, ORIENTACAO, GABARITOEPROVA;
    }

    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private String titulo;
    private String autor;
    private String linkTwitter;

    private Date dataPublicacao;
    private Date dataEdicao;

    @Enumerated(EnumType.STRING)
    private TipoPublicacao tipo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_metadata_id")
    private FileMetadata fileMetadata;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "processo_seletivo_id")
    private ProcessoSeletivo processoSeletivo;

    public Publicacao() {
    }

    public Publicacao(String conteudo, String titulo, String autor, String linkTwitter, Date dataPublicacao, Date dataEdicao, TipoPublicacao tipo, FileMetadata fileMetadata, ProcessoSeletivo processoSeletivo) {
        this.conteudo = conteudo;
        this.titulo = titulo;
        this.autor = autor;
        this.linkTwitter = linkTwitter;
        this.dataPublicacao = dataPublicacao;
        this.dataEdicao = dataEdicao;
        this.tipo = tipo;
        this.fileMetadata = fileMetadata;
        this.processoSeletivo = processoSeletivo;
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

    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }

    public void setFileMetadata(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public ProcessoSeletivo getProcessoSeletivo() {
        return processoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        this.processoSeletivo = processoSeletivo;
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

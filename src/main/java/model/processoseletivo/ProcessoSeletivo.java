package model.processoseletivo;

import java.io.Serializable;
import java.util.Date;
import model.usuario.Usuario;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import model.JpaEntity;

@NamedQueries({
    @NamedQuery(
            name = "processoSeletivo.findAll",
            query = "select distinct p from ProcessoSeletivo p "
            + "order by p.id"
    ),
    @NamedQuery(
            name = "processoSeletivo.findByUserId",
            query = "SELECT DISTINCT p FROM ProcessoSeletivo p JOIN p.candidatos u WHERE u.id = :userId"
    )
})
@Entity
public class ProcessoSeletivo extends JpaEntity implements Serializable {

    @Column(nullable = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Fase fase = Fase.INSCRICAO;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataFim;

    private String linkEdital;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "processoseletivo_usuario",
            joinColumns = @JoinColumn(name = "processo_seletivo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> candidatos;

    public ProcessoSeletivo() {
    }

    public ProcessoSeletivo(String nome, Fase fase, Date dataInicio, Date dataFim, String linkEdital, List<Usuario> candidatos) {
        this.nome = nome;
        this.fase = fase;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.linkEdital = linkEdital;
        this.candidatos = candidatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getLinkEdital() {
        return linkEdital;
    }

    public void setLinkEdital(String linkEdital) {
        this.linkEdital = linkEdital;
    }

    public List<Usuario> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Usuario> candidatos) {
        this.candidatos = candidatos;
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
        ProcessoSeletivo other = (ProcessoSeletivo) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "ProcessoSeletivo [id=" + this.getId() + "]";
    }

}

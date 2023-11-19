package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ProcessoSeletivo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private boolean status;
	private Date dataInicio;
	private Date dataFim;
    private String linkEdital;
    
    @OneToMany(mappedBy = "processoSeletivo")
    private List<Resultado> resultados;
    
    @Enumerated(EnumType.STRING)
    private TipoPerfil tipoperfil;
    
    @ManyToMany(mappedBy = "processosSeletivos")
    private List<Usuario> candidatos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public TipoPerfil getTipoperfil() {
		return tipoperfil;
	}

	public void setTipoperfil(TipoPerfil tipoperfil) {
		this.tipoperfil = tipoperfil;
	}

	public List<Usuario> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Usuario> candidatos) {
		this.candidatos = candidatos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessoSeletivo other = (ProcessoSeletivo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "ProcessoSeletivo [id=" + id + "]";
	}
    
    
}

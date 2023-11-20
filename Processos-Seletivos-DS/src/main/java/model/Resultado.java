package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private ProcessoSeletivo processoSeletivo;
	@ManyToOne
	private Usuario candidato;
	private int totalPontos;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProcessoSeletivo getProcessoSeletivo() {
		return processoSeletivo;
	}
	public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
		this.processoSeletivo = processoSeletivo;
	}
	public Usuario getCandidato() {
		return candidato;
	}
	public void setCandidato(Usuario candidato) {
		this.candidato = candidato;
	}
	public int getTotalPontos() {
		return totalPontos;
	}
	public void setTotalPontos(int totalPontos) {
		this.totalPontos = totalPontos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Resultado other = (Resultado) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Resultado [id=" + id + "]";
	}


}

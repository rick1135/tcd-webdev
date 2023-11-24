package model;

import model.usuario.Usuario;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Newsletter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Usuario candidato;
	private boolean ativa;
	private Date ultimaDataEnvio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getCandidato() {
		return candidato;
	}
	public void setCandidato(Usuario candidato) {
		this.candidato = candidato;
	}
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	public Date getUltimaDataEnvio() {
		return ultimaDataEnvio;
	}
	public void setUltimaDataEnvio(Date ultimaDataEnvio) {
		this.ultimaDataEnvio = ultimaDataEnvio;
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
		Newsletter other = (Newsletter) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Newsletter [id=" + id + "]";
	}


}

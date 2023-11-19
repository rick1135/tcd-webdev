package controller;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TipoPerfil;
import model.Usuario;

@Singleton
@Startup
public class DadosIniciaisBean {

	@PersistenceContext(unitName = "ProcessosSeletivosPU")
	private EntityManager entityManager;

	@PostConstruct
	public void carregarDadosIniciais() {
		Usuario admin = new Usuario("Admin", "admin@example.com", "senha123", TipoPerfil.Administrador);
		entityManager.persist(admin);
	}
}

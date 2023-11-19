package service;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TipoPerfil;
import model.Usuario;

@WebServlet("/PersistenceTests")
public class PersistenceTestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Usuario usuario = new Usuario("Test", "test@test.com", "1234", TipoPerfil.Candidato);
		entityManager.persist(usuario);

		response.getWriter().println("Dados inseridos com sucesso!");
	}

}

package controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TipoPerfil;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
public class PersistenceTestsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.getWriter().println("Ola mundo!");
    }
}

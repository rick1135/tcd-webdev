package model.usuario;

/**
 *
 * @author rktds
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import model.usuario.Usuario;

@Stateless
public class UsuarioService
        implements UsuarioServiceLocal {

    @PersistenceContext(unitName = "ProcessosSeletivosPU")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @Override
    public void createUser(String username, String email, String password, String group, boolean newsletter) {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        Usuario newUser = new Usuario(
                username,
                email,
                passwordHasher.generate(password.toCharArray()),
                group,
                newsletter
        );
        em.persist(newUser);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return em.createNamedQuery("Usuario.all", Usuario.class).getResultList();
    }

    @Override
    public Usuario getUser(String email) {
        try {
            return em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

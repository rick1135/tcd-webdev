/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package util;

import javax.ejb.Singleton;

/**
 *
 * @author rktds
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import model.processoseletivo.ProcessoSeletivo;
import model.usuario.Usuario;

@Stateless
public class DataServiceBean
        implements DataServiceBeanLocal {

    @PersistenceContext(unitName = "ProcessosSeletivosPU")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @Override
    public void createUser(String username, String email, String password, String group, List<ProcessoSeletivo> processosSeletivos) {

        // @see ApplicationConfig
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        Usuario newUser = new Usuario(
                username,
                email,
                passwordHasher.generate(
                        password.toCharArray()),
                group,
                processosSeletivos);
        em.persist(newUser);
//        em.flush();

    }

    @Override
    public List<Usuario> getAllUsers() {
        return em.createNamedQuery("Usuario.all", Usuario.class).getResultList();
    }

    @Override
    public Optional<Usuario> getUser(String username) {
        return em.createNamedQuery("Usuario.findByUsername", Usuario.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst(); // Can be null (Optional)...
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package util;

import java.util.Date;
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
public class DataService
        implements DataServiceLocal {

    @PersistenceContext(unitName = "ProcessosSeletivosPU")
    EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @Override
    public void createUser(String username, String email, String password, String group) {

        // @see ApplicationConfig
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        Usuario newUser = new Usuario(
                username,
                email,
                passwordHasher.generate(password.toCharArray()),
                group
        );
        em.persist(newUser);
//        em.flush();

    }

    @Override
    public List<Usuario> getAllUsers() {
        return em.createNamedQuery("Usuario.all", Usuario.class).getResultList();
    }

    @Override
    public Usuario getUser(String username) {
        return em.createNamedQuery("Usuario.findByUsername", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
                
    }

    public void addUsuarioToProcessoSeletivo(Usuario usuario, long processoSeletivoId) {
        // Buscar o processo seletivo no banco de dados com base no ID fornecido
        ProcessoSeletivo processoSeletivo = em.find(ProcessoSeletivo.class, processoSeletivoId);
        
        if (processoSeletivo != null) {
            // Adicionar o usuário à lista de candidatos do processo seletivo
            processoSeletivo.getCandidatos().add(usuario);

            // Não é necessário chamar persist, pois o processo seletivo já existe no banco de dados
            // Opcional: Se você quiser sincronizar as alterações no banco de dados
            em.merge(processoSeletivo);
        } else {
            // Tratar o caso em que o processo seletivo não foi encontrado
            // Isso pode incluir lançar uma exceção, registrar um aviso, etc.
            System.out.println("Processo seletivo não encontrado com o ID: " + processoSeletivoId);
        }
    }

    public void salvarNovoProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        em.persist(processoSeletivo);
    }
}

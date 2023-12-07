package model.processoseletivo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rktds
 */
@Stateless
public class ProcessoSeletivoService implements ProcessoSeletivoServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to save or update a ProcessoSeletivo
    public void save(ProcessoSeletivo processoSeletivo) {
        if (processoSeletivo.getId() != null && entityManager.contains(processoSeletivo)) {
            entityManager.merge(processoSeletivo);
        } else {
            entityManager.persist(processoSeletivo);
        }
    }

    // Method to find all ProcessoSeletivo
    public List<ProcessoSeletivo> findAll() {
        return entityManager.createQuery("SELECT p FROM ProcessoSeletivo p", ProcessoSeletivo.class)
                .getResultList();
    }

    // Method to find a specific ProcessoSeletivo by ID
    public ProcessoSeletivo findById(Long id) {
        return entityManager.find(ProcessoSeletivo.class, id);
    }

    // Method to find all ProcessoSeletivo where a specific Usuario is a candidate
    public List<ProcessoSeletivo> findByUsuarioId(Long usuarioId) {
        return entityManager.createQuery("SELECT p FROM ProcessoSeletivo p JOIN p.candidatos c WHERE c.id = :usuarioId", ProcessoSeletivo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }
}

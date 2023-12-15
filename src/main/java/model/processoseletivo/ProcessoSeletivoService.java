package model.processoseletivo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@Stateless
public class ProcessoSeletivoService implements ProcessoSeletivoServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ProcessoSeletivo processoSeletivo) {
        if (processoSeletivo.getId() != null && entityManager.contains(processoSeletivo)) {
            entityManager.merge(processoSeletivo);
        } else {
            entityManager.persist(processoSeletivo);
        }
    }

    public List<ProcessoSeletivo> findAll() {
        return entityManager.createQuery("SELECT p FROM ProcessoSeletivo p", ProcessoSeletivo.class)
                .getResultList();
    }

    public ProcessoSeletivo findById(Long id) {
        return entityManager.find(ProcessoSeletivo.class, id);
    }

    public List<ProcessoSeletivo> findByUsuarioId(Long usuarioId) {
        return entityManager.createQuery("SELECT p FROM ProcessoSeletivo p JOIN p.candidatos c WHERE c.id = :usuarioId", ProcessoSeletivo.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    public void addUsuarioToProcessoSeletivo(Usuario usuario, long processoSeletivoId) {
        ProcessoSeletivo processoSeletivo = entityManager.find(ProcessoSeletivo.class, processoSeletivoId);

        if (processoSeletivo != null) {
            processoSeletivo.getCandidatos().add(usuario);

            entityManager.merge(processoSeletivo);
        } else {
            System.out.println("Processo seletivo não encontrado com o ID: " + processoSeletivoId);
        }
    }

    public void salvarNovoProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        entityManager.persist(processoSeletivo);
    }
}

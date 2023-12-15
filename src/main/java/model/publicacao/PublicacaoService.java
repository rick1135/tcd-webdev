package model.publicacao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author rktds
 */
@Stateless
public class PublicacaoService implements PublicacaoServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePublicacao(Publicacao publicacao) {
        if (publicacao.getId() == null) {
            entityManager.persist(publicacao);
        } else {
            entityManager.merge(publicacao);
        }
    }

    public Publicacao findPublicacaoById(Long id) {
        return entityManager.find(Publicacao.class, id);
    }

    public List<Publicacao> getAllPublicacoes() {
        return entityManager.createQuery("SELECT p FROM Publicacao p", Publicacao.class).getResultList();
    }

    public List<Publicacao> getPublicacoesByTipo(Publicacao.TipoPublicacao tipo) {
        return entityManager.createQuery("SELECT p FROM Publicacao p WHERE p.tipo = :tipo", Publicacao.class)
                .setParameter("tipo", tipo)
                .getResultList();
    }

    public List<Publicacao> getNoticiasUltimosQuinzeDias() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -15);
        Date fifteenDaysAgo = calendar.getTime();

        return entityManager.createQuery("SELECT p FROM Publicacao p WHERE p.tipo = :tipo AND p.dataPublicacao >= :data", Publicacao.class)
                .setParameter("tipo", Publicacao.TipoPublicacao.NOTICIA)
                .setParameter("data", fifteenDaysAgo, TemporalType.DATE)
                .getResultList();
    }

    public void removePublicacao(Long id) {
        Publicacao publicacao = findPublicacaoById(id);
        if (publicacao != null) {
            entityManager.remove(publicacao);
        }
    }

}

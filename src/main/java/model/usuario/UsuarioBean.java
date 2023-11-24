package model.usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rktds
 */
@Stateless
public class UsuarioBean implements UsuarioBeanLocal {
    
    @PersistenceContext
    EntityManager em;
    
    @Override
    public void salvar(Usuario pessoa) {
        em.persist(pessoa);
    }
}

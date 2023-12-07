/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package model.publicacao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rktds
 */
@Stateless
public class PublicacaoService implements PublicacaoServiceLocal {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to save a new or update an existing Publicacao
    public void savePublicacao(Publicacao publicacao) {
        if (publicacao.getId() != null && entityManager.contains(publicacao)) {
            entityManager.merge(publicacao);
        } else {
            entityManager.persist(publicacao);
        }
    }

    // Method to find a Publicacao by ID
    public Publicacao findPublicacaoById(Long id) {
        return entityManager.find(Publicacao.class, id);
    }

    // Method to get all Publicacao entities
    public List<Publicacao> getAllPublicacoes() {
        return entityManager.createQuery("SELECT p FROM Publicacao p", Publicacao.class).getResultList();
    }

    // Method to get Publicacao entities by TipoPublicacao
    public List<Publicacao> getPublicacoesByTipo(Publicacao.TipoPublicacao tipo) {
        return entityManager.createQuery("SELECT p FROM Publicacao p WHERE p.tipo = :tipo", Publicacao.class)
                .setParameter("tipo", tipo)
                .getResultList();
    }
}

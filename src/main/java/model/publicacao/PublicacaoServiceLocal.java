/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package model.publicacao;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rktds
 */
@Local
public interface PublicacaoServiceLocal {

    public void savePublicacao(Publicacao publicacao);

    public Publicacao findPublicacaoById(Long id);

    public List<Publicacao> getAllPublicacoes();

    public List<Publicacao> getPublicacoesByTipo(Publicacao.TipoPublicacao tipo);

}

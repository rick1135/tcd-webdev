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

    public List<Publicacao> getNoticiasUltimosQuinzeDias();
}

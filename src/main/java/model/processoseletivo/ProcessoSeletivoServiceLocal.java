package model.processoseletivo;

import java.util.List;
import javax.ejb.Local;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@Local
public interface ProcessoSeletivoServiceLocal {

    public void save(ProcessoSeletivo processoSeletivo);

    public List<ProcessoSeletivo> findAll();

    public ProcessoSeletivo findById(Long id);

    public List<ProcessoSeletivo> findByUsuarioId(Long usuarioId);

    public void addUsuarioToProcessoSeletivo(Usuario usuario, long processoSeletivoId);

    public void salvarNovoProcessoSeletivo(ProcessoSeletivo processoSeletivo);
}

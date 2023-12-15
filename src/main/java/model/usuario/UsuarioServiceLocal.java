package model.usuario;

import java.util.List;
import javax.ejb.Local;
import model.processoseletivo.ProcessoSeletivo;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@Local
public interface UsuarioServiceLocal {

    void createUser(String username, String email, String password, String group, boolean newsletter);

    List<Usuario> getAllUsers();

    Usuario getUser(String email);
}

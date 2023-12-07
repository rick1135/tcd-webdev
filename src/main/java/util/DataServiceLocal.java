/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package util;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import model.processoseletivo.ProcessoSeletivo;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@Local
public interface DataServiceLocal {
    void createUser(String username, String email, String password, String group);
    List<Usuario> getAllUsers();
    Usuario getUser(String username);
    public void salvarNovoProcessoSeletivo(ProcessoSeletivo processoSeletivo);
    public void addUsuarioToProcessoSeletivo(Usuario usuario, long processoSeletivoId);
}

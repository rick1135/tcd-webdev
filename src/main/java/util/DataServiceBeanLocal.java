/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package util;

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
public interface DataServiceBeanLocal {
    void createUser(String username, String email, String password, String group, List<ProcessoSeletivo> processosSeletivos);
    List<Usuario> getAllUsers();
    Optional<Usuario> getUser(String username);
}

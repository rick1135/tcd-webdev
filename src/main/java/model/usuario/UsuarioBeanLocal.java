/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package model.usuario;

import javax.ejb.Local;

/**
 *
 * @author rktds
 */
@Local
public interface UsuarioBeanLocal {
    void salvar(Usuario user);
}

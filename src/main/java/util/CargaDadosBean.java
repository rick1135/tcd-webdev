/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package util;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import model.TipoPerfil;
import model.usuario.Usuario;
import model.usuario.UsuarioBeanLocal;

/**
 *
 * @author rktds
 */
@Singleton
@Startup
public class CargaDadosBean implements CargaDadosBeanLocal {
    
    @Inject
    private UsuarioBeanLocal usuarioBean;

    @Override
    public void cadastrarAdmin() {
        Usuario admin = new Usuario("Admin", "admin@example.com", "senha123", TipoPerfil.Administrador);
        usuarioBean.salvar(admin);
    }

}

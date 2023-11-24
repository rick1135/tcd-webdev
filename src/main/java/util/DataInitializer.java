/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@ApplicationScoped
public class DataInitializer{

    @Inject
    DataServiceBeanLocal dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllUsers().isEmpty()) {
            Usuario guisso = dataService.createUser("Luis Guisso", "guisso", "asdf", "admin", null);
            Usuario azacchi = dataService.createUser("Andrea Zacchi", "azacchi", "asdf", "user", null);
        }
    }

}

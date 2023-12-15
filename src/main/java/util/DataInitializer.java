package util;

import model.usuario.UsuarioServiceLocal;
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
public class DataInitializer {

    @Inject
    UsuarioServiceLocal dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (dataService.getAllUsers().isEmpty()) {
            dataService.createUser("Luis Guisso", "guisso", "asdf", "Administrador", false);
            dataService.createUser("Andrea Zacchi", "azacchi", "asdf", "Candidato", false);
            Usuario guisso = dataService.getUser("Luis Guisso");
        }
    }

}

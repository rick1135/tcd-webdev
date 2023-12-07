/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB31/SingletonEjbClass.java to edit this template
 */
package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import model.processoseletivo.Fase;
import model.processoseletivo.ProcessoSeletivo;
import model.usuario.Usuario;

/**
 *
 * @author rktds
 */
@ApplicationScoped
public class DataInitializer{

    @Inject
    DataServiceLocal dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        ProcessoSeletivo p1 = new ProcessoSeletivo();
        p1.setNome("Processo Seletivo 1");
        p1.setFase(Fase.CONCLUIDO);
        List<Usuario> candidatos = new ArrayList<>();
        p1.setCandidatos(candidatos);
        p1.setDataFim(null);
        p1.setDataInicio(null);
        p1.setLinkEdital(null);
        
        ProcessoSeletivo p2 = new ProcessoSeletivo();
        p2.setNome("Processo Seletivo 2");
        p2.setFase(Fase.EM_ANDAMENTO);
        List<Usuario> candidatos2 = new ArrayList<>();
        p2.setCandidatos(candidatos2);
        p2.setDataFim(null);
        p2.setDataInicio(null);
        p2.setLinkEdital(null);
        
        dataService.salvarNovoProcessoSeletivo(p1);
        dataService.salvarNovoProcessoSeletivo(p2);
        
        if (dataService.getAllUsers().isEmpty()) {
            dataService.createUser("Luis Guisso", "guisso", "asdf", "admin");
            dataService.createUser("Andrea Zacchi", "azacchi", "asdf", "user");
            Usuario guisso = dataService.getUser("Luis Guisso");
            dataService.addUsuarioToProcessoSeletivo(guisso, 1l);
            dataService.addUsuarioToProcessoSeletivo(guisso, 2l);
        }
    }

}

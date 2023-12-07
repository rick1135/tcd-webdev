/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.processoseletivo.ProcessoSeletivo;
import model.processoseletivo.ProcessoSeletivoService;
import model.processoseletivo.ProcessoSeletivoServiceLocal;
import model.usuario.Usuario;
import model.usuario.UsuarioController;
import org.primefaces.model.ResponsiveOption;
import util.DataService;
/**
 *
 * @author rktds
 */
@Named
@SessionScoped
public class ParticipanteController implements Serializable{
    
    @Inject
    ProcessoSeletivoServiceLocal processoService;

    @Inject
    UsuarioController usuarioController;  // Injecting UsuarioController

    private List<ProcessoSeletivo> processosSeletivos;

    private List<ResponsiveOption> responsiveOptions;
    
    @PostConstruct
    public void init() {
        
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
        
        Usuario currentUser = usuarioController.getCurrentUser();
        if (currentUser != null) {
            processosSeletivos = processoService.findByUsuarioId(currentUser.getId());
        } else {
            // Handle the case where no user is logged in
            processosSeletivos = new ArrayList<>();
        }
    }
    
    public List<ProcessoSeletivo> getProcessosSeletivos() {
        return processosSeletivos;
    }

    public void setProcessosSeletivos(List<ProcessoSeletivo> processosSeletivos) {
        this.processosSeletivos = processosSeletivos;
    }
    
    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }
}

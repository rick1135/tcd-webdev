/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package model.processoseletivo;

import java.util.List;
import javax.ejb.Local;

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
}

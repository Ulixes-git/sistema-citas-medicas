/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/**
 *
 * @author Ulises
 */
public class Cita {
    
    private int id;
    private LocalDateTime fechaHora;
    private Paciente paciente;
    private Medico medico;
    private String estado;
    private String motivo;
    
    public Cita(int id, LocalDateTime fechaHora, Paciente paciente, Medico medico, String motivo){
        
        //Validaciones
        Objects.requireNonNull(paciente, "El paciente no puede ser nulo");
        Objects.requireNonNull(medico, "El médico no puede ser nulo");
        
        if (fechaHora.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("La fecha no puede ser en el pasado");
        }
        
        if (motivo == null || motivo.trim().isEmpty()){
            throw new IllegalArgumentException("El motivo no puede estar vacío");
        }
        
        this.id = id;
        this.fechaHora = fechaHora;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = "programada";
        this.motivo = motivo;
    }
    
    public boolean medicoDisponible(){
        DayOfWeek diaCita = fechaHora.getDayOfWeek();
        if (!medico.trabajaElDia(diaCita)){
            return false;
        }
        return true;
    }
    
    public void validarAntesDeAgendar(){
        if(!medicoDisponible()){
            throw new IllegalStateException("El médico no está disponible en ese horario.");
        }
        if(!estado.equals("programada")){
            throw new IllegalStateException("Solo se pueden modificar citas programadas.");
        }
    }
    
    public void cancelar(){
        validarAntesDeAgendar();
        this.estado = "cancelada";
    }
    
    public void completar(){
        if(fechaHora.isAfter(LocalDateTime.now())){
            throw new IllegalStateException("No se puede completar una cita futura.");
        }
        this.estado = "completada";
    }
    
    public int getId(){ 
        return id; 
    }
     
    public LocalDateTime getFechaHora(){
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora){
        if (fechaHora.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("La fecha no puede ser pasada");
        }
        this.fechaHora = fechaHora;
    }
    
    public Paciente getPaciente(){
        return paciente;
    }
    
    public Medico getMedico(){
        return medico;
    }           
    
    public String getEstado(){
        return estado;
    }
    
    public String getMotivo(){
        return motivo;
    }
    
    public void setMotivo(String motivo){
        if (motivo == null || motivo.trim().isEmpty()){
            throw new IllegalArgumentException("El motivo no puede estar vacío");
        }
        this.motivo = motivo;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return String.format("Cita #%d - %s\nPaciente: %s\nMédico: %s (%s)\nMotivo: %s",
                id,
                fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                paciente.getNombre(),
                medico.getNombre(),
                medico.getEspecialidad(),
                motivo
        );
    }
}

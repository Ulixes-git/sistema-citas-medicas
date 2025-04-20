/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;  

/**
 *
 * @author Ulises
 */
public class Medico extends Usuario{
    
    private String especialidad;
    private String cedulaProfesional;
    private Set<DayOfWeek> diasLaborales;
    private String horarioAtencion;
    private boolean activo = true;
    private String telefono;
    private String consultorio;

    
    public Medico(int id, String nombre, String apellido, String email, String contraseña, String especialidad,
            String cedulaProfesional, String telefono, String consultorio){
        super(id, nombre, apellido, email, contraseña, "medico");
        this.especialidad = especialidad;
        this.setCedulaProfesional(cedulaProfesional);
        this.diasLaborales = EnumSet.noneOf(DayOfWeek.class);
        this.horarioAtencion = "9:00-18:00";
        this.setTelefono(telefono);
        this.setConsultorio(consultorio);
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        if(especialidad == null || especialidad.trim().isEmpty()){
            throw new IllegalArgumentException("La especialidad no puede estar vacía");
        }
        this.especialidad = especialidad;
    }
    
    public String getCedulaProfesional(){
        return cedulaProfesional;
    }
    
    public void setCedulaProfesional(String cedula){
        if (!cedula.matches("^[A-Za-z0-9-]{5,20}$")){
            throw new IllegalArgumentException("Cédula profesional inválida");
        }
        this.cedulaProfesional = cedula;
    }
    
        public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String telefono){
        if(telefono == null || !telefono.matches("^[0-9]{10,15}$")){
            throw new IllegalArgumentException("Debe contener solo números (10-15 dígitos)");
        }
        this.telefono = telefono;
    }
    
    public String getConsultorio(){
        return consultorio;
    }
    
    public void setConsultorio(String consultorio){
        if(consultorio == null || consultorio.trim().isEmpty()){
            throw new IllegalArgumentException("Consultorio no puede estar vacío");
        }
        this.consultorio = consultorio;
    }
    
    public void agregarDiaLaboral(DayOfWeek dia) {
        this.diasLaborales.add(dia);
    }
    
    public void removerDiaLaboral(DayOfWeek dia){
        this.diasLaborales.remove(dia);
    }
    
    public boolean trabajaElDia(DayOfWeek dia){
        return this.diasLaborales.contains(dia);
    }
    
    public String getHorarioAtencion(){
        return horarioAtencion;
    }
    
    public void setHorarioAtencion(String horario){
        if (!horario.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]$")){
            throw new IllegalArgumentException("Formato de horario inválido. Use HH:MM-HH:MM");
        }
        this.horarioAtencion = horario;
    }
    
    public String getDiasLaboralesFormateados(){
        return diasLaborales.stream()
                .map(DayOfWeek::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("No hay días asignados");
    }
    
    public boolean isActivo(){
        return activo;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    
    @Override
    public String toString(){
        return String.format(
                "Médico: %s (%s)\nEspecialidad: %s\nCédula: %s\nTeléfono: %s\nConsultorio: %s\nHorario: %s\nDías laborales: %s",
                getNombre(), getEmail(), especialidad, cedulaProfesional, 
                telefono, consultorio, horarioAtencion, getDiasLaboralesFormateados()
        );
    }
    
}

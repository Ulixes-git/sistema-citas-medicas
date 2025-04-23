/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import entidades.Genero;
import entidades.TipoAlergia;

/**
 *
 * @author Ulises
 */
public class Paciente extends Usuario {
    
    private LocalDate fechaNacimiento;
    private static final DateTimeFormatter FORMATO_MEXICANO = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String tipoSangre;
    private TipoAlergia alergia;
    private List<String> historialMedico;
    private Genero genero;
    private String telefono;
    private String direccion;
    private Boolean validado;
    
    public Paciente(int id, String nombre, String apellido, String email, String contraseña, String fechaNacimientoStr, 
            String tipoSangre, TipoAlergia alergia, Genero genero, String telefono, String direccion){
        super(id, nombre, apellido, email, contraseña, "paciente");
        this.setFechaNacimiento(fechaNacimientoStr);
        this.setTipoSangre(tipoSangre);
        this.setAlergia(alergia);
        this.historialMedico = new ArrayList<>();
        this.setGenero(genero);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
    }
    
    public void setFechaNacimiento(String fechaStr){
        try {
            LocalDate fecha = LocalDate.parse(fechaStr, FORMATO_MEXICANO);
            if (fecha.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("La fecha no puede ser futura");
            }
            this.fechaNacimiento = fecha;
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Fecha inválida. Use formato dd/MM/yyyy");
        }
    }
    
    public int getEdad(){
        return java.time.Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
    public String getFechaNacimientoFormateada(){
        return fechaNacimiento.format(FORMATO_MEXICANO);
    }
    
    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    public String getTipoSangre(){
        return tipoSangre;
    }
    
    public void setTipoSangre(String tipoSangre){
        if(!tipoSangre.matches("^(A|B|AB|O)[+-]$")){
            throw new IllegalArgumentException ("Tipo de sangre inválido. Ejemplos válidos: A+, B-, O+");
        }
        this.tipoSangre = tipoSangre;
    }
    
    public TipoAlergia getAlergia(){
        return alergia;
    }
    
    public void setAlergia(TipoAlergia alergia){
        this.alergia = alergia != null ? alergia : TipoAlergia.NINGUNA;
    }
    
    public Genero getGenero(){
        return genero;
    }
    
    public void setGenero(Genero genero) {
        this.genero = genero != null ? genero : Genero.NO_ESPECIFICADO;
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
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        if(direccion == null || direccion.trim().isEmpty()){
            throw new IllegalArgumentException("Dirección no puede estar vacía");
        }
        this.direccion = direccion;
    }
    
    public void agregarNotaHistorial(String nota){
        String notaConFecha = java.time.LocalDate.now() + ": " + nota;
        this.historialMedico.add(notaConFecha);
    }
    
    public List<String> getHistorialMedico(){
        return new ArrayList<>(historialMedico);
    }
    
    public void setValidado(boolean validado){
        this.validado = validado;
    }
    
    public Boolean getValidado(){
        return this.validado;
    }
  
}


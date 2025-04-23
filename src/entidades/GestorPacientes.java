/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ulises
 */
public class GestorPacientes {
    
    private List<Paciente> pacientes;
    
    public GestorPacientes(){
        this.pacientes = new ArrayList<>();
    }
    
    public List<Paciente> getPacientes(){
        return pacientes;
    }
    
    public GestorPacientes getGestorPacientes(){
        return gestorPacientes;
    }
    
    public void agregarPaciente(Paciente paciente){
        if(paciente == null){
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        if(buscarPorId(paciente.getId()) != null){
            throw new IllegalStateException("Ya existe un paciente con este ID");
        }
        pacientes.add(paciente);
    }
    
    public Paciente buscarPorId(int id){
        return pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<Paciente> buscarPorNombre(String nombre){
        return pacientes.stream()
                .filter(p -> p.getNombre().contains(nombre))
                .collect(Collectors.toList());
    }
    
    public boolean validarPaciente(int idPaciente){
        Paciente p = buscarPorId(idPaciente);
        if(p != null){
            p.setValidado(true);
            return true;
        }
        return false;
    }
    
    public void guardarEnArchivo(String ruta) throws IOException{
        try(PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
            for(Paciente paciente : pacientes){
                
                String linea  = String.join(".",
                    String.valueOf(paciente.getId()),
                    paciente.getNombre(),
                    paciente.getApellido(),
                    paciente.getEmail(),
                    paciente.getFechaNacimiento().toString(),
                    paciente.getTipoSangre(),
                    paciente.getAlergia().name(),
                    paciente.getGenero().name(),
                    paciente.getDireccion(),
                    paciente.getValidado().toString()
                );
                writer.println(linea);
            }
        }
    }
    
    public void cargarDesdeArchivo(String ruta) throws IOException{
        pacientes.clear();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea = reader.readLine()) != null){
                String[] datos = linea.split("\\.");
                
                if(datos.length < 11) continue;
                
                Paciente paciente = new Paciente(
                    Integer.parseInt(datos[0]), //id
                    datos[1], //nombre
                    datos[2], //apellido
                    datos[3], //email
                    datos[4], //contraseña
                    datos[5], //frechaNacimiento
                    datos[6], //tipoSangre
                    TipoAlergia.valueOf(datos[7]), //alergia
                    Genero.valueOf(datos[8]), //genenero
                    datos[9], // telefono
                    datos[10] // direccion
                );
                  
                if(!datos[7].isEmpty() && !datos[7].equals("null")){
                    paciente.setAlergia(TipoAlergia.valueOf(datos[7].trim()));
                }
                
                pacientes.add(paciente);
            }
            
        }
    }
    
}

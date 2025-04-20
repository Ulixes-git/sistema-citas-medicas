/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ulises
 */
public class GestorCitas {
    
    private List<Cita> citas;
    
    public GestorCitas(){
        this.citas = new ArrayList<>();
    } 
    
    public boolean estaDisponible(Medico medico, LocalDateTime fechaHora){
        if(medico == null || fechaHora == null){
            throw new IllegalArgumentException("Parámetros no pueden ser nulos");
        }
        
        if(!medico.trabajaElDia(fechaHora.getDayOfWeek())){
            return false;
        }
        
        return citas.stream().noneMatch(c ->
            c.getMedico().equals(medico)&&
            c.getFechaHora().equals(fechaHora)
        );
    }
    
    public void guardaEnArchivo(String ruta) throws IOException{
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            
            for(Cita cita : citas){
                String linea = String.join(".",
                    String.valueOf(cita.getId()),
                    cita.getFechaHora().format(formatter),
                    String.valueOf(cita.getPaciente().getId()),
                    String.valueOf(cita.getMedico().getId()),
                    cita.getEstado(),
                    cita.getMotivo()
                );
                writer.println(linea);
            }
        }
    }
    
    public void cargarDesdeArchivo(String ruta,
                                GestorPacientes gestorPacientes,
                                GestorMedicos gestorMedicos) throws IOException{
        citas.clear();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            
            while((linea = reader.readLine()) != null){
                String[] datos = linea.split("\\.");
                
                if(datos.length != 6) continue;
                
                Cita cita = new Cita(
                Integer.parseInt(datos[0]),
                LocalDateTime.parse(datos[1], formatter),
                gestorPacientes.buscarPorId(Integer.parseInt(datos[2])),
                gestorMedicos.buscarPorId(Integer.parseInt(datos[3])),
                datos[5]
                );
                cita.setEstado(datos[4]);
                citas.add(cita);
            }
        }
    }
    
    public void agregarCita(Cita nuevaCita){
        if(nuevaCita == null){
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        for(Cita citaExistente : citas){
            if(citaExistente.getMedico().equals(nuevaCita.getMedico())&&
                    citaExistente.getFechaHora().equals(nuevaCita.getFechaHora())){
                throw new IllegalArgumentException("El médico ya tiene una cita programada en ese horario");
            }
        }
        
        if(!nuevaCita.medicoDisponible()){
            throw new IllegalArgumentException("El médico no trabaja en el día seleccionado");
        }
        
        citas.add(nuevaCita);
    }
    
    public List<Cita> obtenerTodasCitas(){
        return new ArrayList<>(citas);
    }
    
    public List<Cita> getCitasPorMedico(Medico medico){
        List<Cita> resultado = new ArrayList<>();
        for(Cita cita : citas){
            if(cita.getMedico().equals(medico)){
                resultado.add(cita);
            }
        }
        return resultado;
    }
    
    public List<Cita> getCitasPorPaciente(Paciente paciente){
        List<Cita> resultado = new ArrayList<>();
        for(Cita cita : citas){
            if(cita.getPaciente().equals(paciente)){
                resultado.add(cita);
            }
        }
        return resultado;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.*;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ulises
 */
public class GestorMedicos {
    
    private List<Medico> medicos;
    
    public GestorMedicos(){
        this.medicos = new ArrayList<>();
    }
    
    public List<Medico> getMedicos(){
        return medicos;
    }
    
    
    public void agregarMedico(Medico medico){
        if (medico == null){
            throw new IllegalArgumentException("El médico no puede ser nulo");
        }
        if (buscarPorId(medico.getId()) != null){
            throw new IllegalArgumentException("Ya existe un médico con este ID");
        }
        medicos.add(medico);
    }
    
    public Medico buscarPorId(int id){
        return medicos.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<Medico> buscarPorEspecialidad(String especialidad){
        return medicos.stream()
                .filter(m -> m.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }
    
    public void guardarEnArchivo(String ruta) throws IOException{
        try(PrintWriter writer = new PrintWriter(new FileWriter(ruta))){
            for(Medico medico : medicos){
                String linea = String.join(".",
                    String.valueOf(medico.getId()),
                    medico.getNombre(),
                    medico.getApellido(),
                    medico.getEspecialidad(),
                    medico.getConsultorio(),
                    medico.getDiasLaboralesFormateados().replace(", ", ";"),
                    medico.getHorarioAtencion().toString(),
                    String.valueOf(medico.isActivo())
                );
                writer.println(linea);
            }
        }
    }
    
    public void cargarDesdeArchivo(String ruta) throws IOException{
        medicos.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            int numeroLinea = 0;
            
            while ((linea = reader.readLine()) != null){
                numeroLinea++;
                try{
                String[] datos = linea.split(".");
                
                if (datos.length < 8){
                    System.err.println("Línea " + numeroLinea + ": Formato imcorrecto. Campos insuficientes");
                    continue;
                }
                
                int id;
                try{
                    id = Integer.parseInt(datos[0]);
                } catch(NumberFormatException e){
                    System.err.println("Línea " + numeroLinea + ": ID inválido. Debe ser número");
                    continue;
                }
                
                LocalTime horario;
                try{
                    horario = LocalTime.parse(datos[6]);
                } catch(Exception e){
                    System.err.println("Línea " + numeroLinea + ": Formato de horario inválido");
                    continue;
                }
                
                if(datos[1].isEmpty() || datos[2].isEmpty() || datos[3].isEmpty()){
                    System.err.println("Línea " + numeroLinea + ": Campos nombre, apellido o especialidad vacíos");
                    continue;
                }
                
                Medico medico = new Medico(id, datos[1], datos[2], datos[3]);
                medico.setConsultorio(datos[4]);
                
                if (!datos[].isEmpty()){
                    for(String dia : datos[5].split(";")){
                        try{
                            medico.agregarDiaLaboral(DayOfWeek.valueOf(dia.trim().toUpperCase()));
                        }catch (IllegalArgumentException e){
                            System.err.println("Día no válido: " + dia);
                        }
                    }
                }   
                
                medico.setHorarioAtencion(horario);
                
                medico.setActivo(Boolean.parseBoolean(datos[7]));
                
                medicos.add(medico);
            } catch(Exception e){
                System.err.println("Error procesando línea " +numeroLinea + ": " + e.getMessage());
                }
            }
        }
    }
    
    public List<Medico> obtenerTodosMedicos(){
        return new ArrayList<>(medicos);
    }
    
    public List<Medico> obtenerMedicosActivos(){
        return medicos.stream()
                .filter(Medico::isActivo)
                .collect(Collectors.toList());
    }
    
    public boolean eliminarMedico(int id, GestorCitas gestorCitas){
        Medico medico = buscarPorId(id);
        if(medico == null){
            return false;
        }
        
        List<Cita> citasMedico = gestorCitas.getCitasPorMedico(medico).stream()
                .filter(c -> c.getFechaHora().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
        if(!citasMedico.isEmpty()){
            throw new IllegalStateException("No se puede eliminar el médico. Tiene" + 
                    citasMedico.size() + " citas programadas.");
        }
        return medicos.removeIf(m -> m.getId() == id);
    }
    
    public boolean desactivarMedico(int id){
        Medico medico = buscarPorId(id);
        if(medico != null){
            medico.setActivo(false);
            return true;
        }
        return false;
    }
    
    public boolean actualizarMedico(Medico medicoActualizado){
        for(int i = 0; i <medicos.size(); i++){
            if(medicos.get(i).getId() == medicoActualizado.getId()){
                medicos.set(i, medicoActualizado);
                return true;
            }
        }
        return false;
    }
}

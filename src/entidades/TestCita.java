/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import entidades.*;
import java.time.DayOfWeek;
import java.io.IOException;

/**
 *
 * @author Ulises
 */
public class TestCita {
    
    public static void main(String[] args) {
        // 1. Prueba de creación de pacientes
        System.out.println("=== PRUEBA DE PACIENTES ===");
        Paciente paciente1 = new Paciente(
            1, 
            "Juan", 
            "Pérez", 
            "juan@example.com", 
            "Pass123", 
            "15/05/1980", 
            "A+", 
            TipoAlergia.MEDICAMENTOS, 
            Genero.MASCULINO, 
            "5512345678", 
            "Calle Falsa 123"
        );
        
        System.out.println("Paciente creado:");
        System.out.println("Nombre: " + paciente1.getNombre() + " " + paciente1.getApellido());
        System.out.println("Edad: " + paciente1.getEdad() + " años");
        System.out.println("Alergia: " + paciente1.getAlergia().getDescripcion());
        System.out.println("Validado: " + paciente1.getValidado());
        
        // 2. Prueba de historial médico
        paciente1.agregarNotaHistorial("Primera consulta - chequeo general");
        paciente1.agregarNotaHistorial("Resultados de laboratorio recibidos");
        System.out.println("\nHistorial médico:");
        paciente1.getHistorialMedico().forEach(System.out::println);
        
        // 3. Prueba de GestorPacientes
        System.out.println("\n=== PRUEBA DE GESTOR PACIENTES ===");
        GestorPacientes gestor = new GestorPacientes();
        gestor.agregarPaciente(paciente1);
        
        // Agregar otro paciente
        gestor.agregarPaciente(new Paciente(
            2, 
            "María", 
            "Gómez", 
            "maria@example.com", 
            "Pass456", 
            "22/10/1995", 
            "O-", 
            TipoAlergia.ALIMENTOS, 
            Genero.FEMENINO, 
            "5511223344", 
            "Avenida Siempreviva 742"
        ));
        
        // Buscar paciente
        System.out.println("\nBuscando paciente con ID 1:");
        Paciente encontrado = gestor.buscarPorId(1);
        System.out.println(encontrado != null ? encontrado.getNombre() : "No encontrado");
        
        // Validar paciente
        gestor.validarPaciente(1);
        System.out.println("Paciente validado: " + gestor.buscarPorId(1).getValidado());
        
        // 4. Prueba de Médicos
        System.out.println("\n=== PRUEBA DE MÉDICOS ===");
        Medico medico1 = new Medico(
            101, 
            "Dr. Carlos", 
            "López", 
            "carlos@clinica.com", 
            "DocSecure1", 
            "Cardiología", 
            "CARD-12345", 
            "5555667788", 
            "Consultorio 5"
        );
        
        // Agregar días laborales
        medico1.agregarDiaLaboral(DayOfWeek.MONDAY);
        medico1.agregarDiaLaboral(DayOfWeek.WEDNESDAY);
        medico1.agregarDiaLaboral(DayOfWeek.FRIDAY);
        
        System.out.println("Médico creado:");
        System.out.println(medico1.toString());
        System.out.println("¿Atiende los miércoles? " + medico1.trabajaElDia(DayOfWeek.WEDNESDAY));
        
        // 5. Prueba de persistencia (archivos)
        System.out.println("\n=== PRUEBA DE PERSISTENCIA ===");
        try {
            String archivoPacientes = "pacientes_test.txt";
            gestor.guardarEnArchivo(archivoPacientes);
            System.out.println("Pacientes guardados en archivo correctamente");
            
            // Limpiar gestor y cargar desde archivo
            gestor = new GestorPacientes();
            gestor.cargarDesdeArchivo(archivoPacientes);
            System.out.println("Pacientes cargados desde archivo: " + gestor.obtenerTodosPacientes().size());
            
        } catch (IOException e) {
            System.err.println("Error en archivos: " + e.getMessage());
        }
    }
}

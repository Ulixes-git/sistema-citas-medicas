/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
import java.time.DayOfWeek; 

/**
 *
 * @author Ulises
 */
public class TestGestorCitas {
    
    public static void main(String[] args) {
        // 1. Crear instancias necesarias
        GestorCitas gestor = new GestorCitas();
        
        Medico medico = new Medico(
            1, "Dra. Ana López", "ana@clinica.com", "pass123",
            "Cardiología", "CARD-123", "5511223344", "Consultorio 5"
        );
        medico.agregarDiaLaboral(DayOfWeek.MONDAY);
        
        Paciente paciente = new Paciente(
            1, "Juan Pérez", "juan@email.com", "pass456",
            "15/05/1990", "O+", TipoAlergia.NINGUNA, Genero.MASCULINO,
            "5512345678", "Calle Falsa 123"
        );
        
        // 2. Crear citas de prueba
        LocalDateTime fechaValida = LocalDateTime.now()
            .withHour(10).withMinute(0).withSecond(0).withNano(0)
            .plusDays(1); // Mañana a las 10:00
        
        Cita cita1 = new Cita(1, fechaValida, paciente, medico, "Control anual");
        
        // 3. Probar agregar cita
        try {
            gestor.agregarCita(cita1);
            System.out.println("✅ Cita agregada correctamente");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        
        // 4. Probar conflicto de horarios
        try {
            Cita cita2 = new Cita(2, fechaValida, paciente, medico, "Urgencia");
            gestor.agregarCita(cita2); // Debe fallar
        } catch (Exception e) {
            System.out.println("✅ Correcto: Se detectó conflicto - " + e.getMessage());
        }
    }
    
}

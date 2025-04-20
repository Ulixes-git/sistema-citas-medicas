/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.DayOfWeek;

/**
 *
 * @author Ulises
 */
public class TestMedico {
    public static void main(String[] args) {
       try {
            Medico medico = new Medico(
                1, 
                "Dra. Ana López", 
                "ana@clinica.com", 
                "pass123", 
                "Cardiología", 
                "CARD-87654",
                "5511223344",
                "Consultorio 3B"
            );
            
            medico.agregarDiaLaboral(DayOfWeek.MONDAY);
            medico.agregarDiaLaboral(DayOfWeek.WEDNESDAY);
            medico.setHorarioAtencion("08:00-16:00");
            
            System.out.println(medico);
            System.out.println("Atiende los miércoles? " + medico.trabajaElDia(DayOfWeek.WEDNESDAY));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

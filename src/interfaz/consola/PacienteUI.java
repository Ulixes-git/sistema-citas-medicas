/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.consola;

import entidades.*;
import java.util.Scanner;

/**
 *
 * @author Ulises
 */
public class PacienteUI {
    
    private static void mostrarMenu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n=== PANEL PACIENTE ===");
            System.out.println("1. Agendar nueva cita");
            System.out.println("2. Reagendar cita");
            System.out.println("3. Cancelar cita");
            System.out.println("4. Ver mis citas programadas");
            System.out.println("5. Cerrar sesión");
            System.out.println("Seleccione una opción");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion){
                case 1:
                    System.out.println("\n--- Nueva Cita ---");
                    break;
                case 2:
                    System.out.println("\n--- Reagendar cita ---");
                    break;
                case 3:
                    System.out.println("\n--- Cancelaciones ---");
                    break;
                case 4:
                    System.out.println("\n--- Mis Citas ---");
                    break;
                case 5:
                    Sesion.getInstance().cerrarSesion();
                    System.out.println("Sesión cerrada correctamente");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 5);
    }
    
}

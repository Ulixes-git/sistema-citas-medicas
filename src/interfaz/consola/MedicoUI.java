/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.consola;

import seguridad.Sesion;
import java.util.Scanner;

/**
 *
 * @author Ulises
 */
public class MedicoUI {
    
    private static void mostrarMenu(){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do{
            System.out.println("\n=== PANEL MÉDICO ===");
            System.out.println("1. Ver mis citas programadas");
            System.out.println("2. Constultar historial médico");
            System.out.println("3. Actualizar disponibilidad");
            System.out.println("4. Cerrar sesión");
            System.out.println("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcion){
                case 1:
                    System.out.println("\n--- Mis Citas ---");
                    break;
                case 2:
                    System.out.println("\n--- Historiales Médicos ---");
                    break;
                case 3:
                    System.out.println("\n--- Disponibilidad ---");
                    break;
                case 4:
                    Sesion.getInstance().cerrarSesion();
                    System.out.println("Sesión cerrada correctamente");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 4);
    }
}

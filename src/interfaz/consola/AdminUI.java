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
public class AdminUI {
    
    public static void mostrarMenu(GestorPacientes gp, GestorMedicos gm, GestorCitas gc){
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do{
            System.out.println("\n=== MENÚ ADMIN ===");
            System.out.println("1. Gestionar médicos");
            System.out.println("2. Validar pacientes");
            System.out.println("3. Cerrar sesión");
            System.out.println("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcion){
                case 1:
                    System.out.println("\n--- Gestión de médicos ---");
                    break;
                case 2:
                    System.out.println("\n--- Validación de pacientes");
                    break;
                case 3:
                    Sesion.getInstance().cerrarSesion();
                    System.out.println("Sesión cerrada correctamente");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 3);
    }
}
    

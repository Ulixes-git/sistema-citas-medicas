/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.consola;

import entidades.Usuario;
import seguridad.*;
import java.util.Scanner;

/**
 *
 * @author Ulises
 */
public class LoginUI {
    public static Usuario mostrarLogin(Autenticador autenticador) throws CredencialesInvalidasException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("====================================");
        System.out.println("   SISTEMA DE CITAS MÉDICAS");
        System.out.println("====================================");
        System.out.println("Registrar nuevo paciente...");

        if (!yaRegistrado) {
            System.out.println("Registrar nuevo paciente...");
            RegistroUI.registrarPaciente(autenticador.getGestorPacientes());
            yaRegistrado = true;
        }
        
        System.out.println("Por favor inicie sesión");
        
        while(true){
            try{
                System.out.print("\nEmail: ");
                String email = scanner.nextLine();
                
                System.out.println("Contraseña: ");
                String contraseña = scanner.nextLine();
                
                Usuario usuario = autenticador.autenticar(email, contraseña);
                Sesion.getInstance().iniciarSesion(usuario);
                
                System.out.println("¡BIENVENIDA/O, " + usuario.getNombre() + " " + usuario.getApellido() + "!");
                System.out.println("Rol: " + usuario.getRol().toUpperCase());
                System.out.println("Fecha acceso: " + java.time.LocalDate.now());
                
                return usuario;
            }catch(CredencialesInvalidasException e){
                System.out.println("\nERROR: " + e.getMessage());
                System.out.println("Por favor, intente nuevamente.");
            }
        }
    }  
    
}

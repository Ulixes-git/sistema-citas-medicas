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
public class RegistroUI {
    
    public static void registrarPaciente(GestorPacientes gestorPacientes) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Registro de Paciente ===");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Tipo de sangre: ");
        String tipoSangre = scanner.nextLine();

        System.out.print("Alergia (NINGUNA, POLEN, MEDICAMENTOS, ALIMENTOS): ");
        TipoAlergia alergia = TipoAlergia.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Género (MASCULINO, FEMENINO, OTRO): ");
        Genero genero = Genero.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        int id = gestorPacientes.generarNuevoId();
        Paciente nuevo = new Paciente(id, nombre, apellido, email, contraseña, fechaNacimiento, tipoSangre, alergia, genero, telefono, direccion);

        gestorPacientes.agregarPaciente(nuevo);

        System.out.println("\nPaciente registrado con éxito.");
    }
    
}

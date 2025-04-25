/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemacitasmedicas;

import interfaz.consola.*;
import entidades.*;
import seguridad.Autenticador;
import java.io.IOException;
import interfaz.grafica.LoginVentana;

/**
 *
 * @author Ulises
 */
public class SistemaCitasMedicas {
    
    private static GestorPacientes gestorPacientes;
    private static GestorMedicos gestorMedicos;
    private static GestorCitas gestorCitas;

    public static void main(String[] args) {
    // Crear el gestor de autenticación
        Autenticador autenticador = new Autenticador();     //Fix: Autenticador constructor sin parámetros

    // Mostrar la ventana de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginVentana(autenticador).setVisible(true);
            }   
        });
    }

    private static void inicializarGestores() {
        gestorPacientes = new GestorPacientes();
        gestorMedicos = new GestorMedicos();
        gestorCitas = new GestorCitas();
        
        try {
            gestorPacientes.cargarDesdeArchivo("data/pacientes.dat");
            gestorMedicos.cargarDesdeArchivo("src/datos/medicos.txt");
            gestorCitas.cargarDesdeArchivo("data/citas.dat", gestorPacientes, gestorMedicos);
        } catch (IOException e) {
            System.out.println("Cargando datos iniciales...");
        }
    }

    private static void ejecutarCicloPrincipal(Autenticador autenticador) {
        while(true) {
            try {
                Usuario usuario = LoginUI.mostrarLogin(autenticador);
                mostrarMenuSegunRol(usuario);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void mostrarMenuSegunRol(Usuario usuario) {
        switch(usuario.getRol().toLowerCase()) {
            case "admin":
                AdminUI.mostrarMenu(gestorPacientes, gestorMedicos, gestorCitas);
                break;
            case "medico":
                MedicoUI.mostrarMenu(gestorCitas, gestorPacientes, (Medico)usuario);
                break;
            case "paciente":
                PacienteUI.mostrarMenu(gestorCitas, (Paciente)usuario);
                break;
            default:
                System.out.println("Rol no reconocido");
        }
    }
    
    public static void guardarDatos() {
        try {
            gestorPacientes.guardarEnArchivo("data/pacientes.dat");
            gestorMedicos.guardarEnArchivo("data/medicos.dat");
            gestorCitas.guardaEnArchivo("data/citas.dat");
        } catch (IOException e) {
            System.out.println("Error al guardar datos");
        }
    }
}
    
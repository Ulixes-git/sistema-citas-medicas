/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import entidades.Usuario;
import java.util.HashMap;
import java.util.Map;
import entidades.GestorMedicos;
import entidades.GestorPacientes;
/**
 *
 * @author Ulises
 */
public class Autenticador {
    
    private GestorPacientes gestorPacientes;
    private GestorMedicos gestorMedicos;
    
    public Autenticador(GestorPacientes gestorPacientes, GestorMedicos gestorMedicos){
        this.gestorPacientes = gestorPacientes;
        this.gestorMedicos = gestorMedicos;
    }
    
    public GestorPacientes getGestorPacientes() {
        return gestorPacientes;
    }
    
    public boolean autenticar(String usuario, String contraseña) {
        for (Paciente p : gestorPacientes.getPacientes()) {
            if (p.getUsuario().equals(usuario) && p.getContraseña().equals(contraseña)) {
                return true;
            }
        }

        for (Medico m : gestorMedicos.getMedicos()) {
            if (m.getUsuario().equals(usuario) && m.getContraseña().equals(contraseña)) {
                return true;
            }
        }

        return false;
    }
    
    public Usuario autenticarYObtenerUsuario(String usuario, String contraseña) throws CredencialesInvalidasException {
        for (Paciente p : gestorPacientes.getPacientes()) {
            if (p.getUsuario().equals(usuario) && p.getContraseña().equals(contraseña)) {
                return p;
            }
        }

        for (Medico m : gestorMedicos.getMedicos()) {
            if (m.getUsuario().equals(usuario) && m.getContraseña().equals(contraseña)) {
                return m;
            }
        }

        throw new CredencialesInvalidasException("Usuario o contraseña incorrectos.");
    }
}


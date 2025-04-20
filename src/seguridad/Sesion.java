/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import entidades.Usuario;

/**
 *
 * @author Ulises
 */
public class Sesion {
    
    private static Sesion instancia;
    private Usuario usuarioActual;
    
    private Sesion(){}
    
    public static Sesion getInstance(){
        if(instancia == null){
            instancia = new Sesion();
        }
        return instancia;
    }
    
    public void iniciarSesion(Usuario usuario){
        if(usuario == null){
            throw new IllegalArgumentException("Usuario no puede ser nulo");
        }
        this.usuarioActual = usuario;
    }
    
    public void cerrarSesion(){
        this.usuarioActual = null;
    }
    
    public Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    public boolean estaIniciandoSesion(){
        return usuarioActual != null;
    }
    
    public boolean esAdmin(){
        return estaIniciandoSesion() && "admin".equalsIgnoreCase(usuarioActual.getRol());
    }
    
    public boolean esMedico(){
        return estaIniciandoSesion() && "medico".equalsIgnoreCase(usuarioActual.getRol());
    }
    
    public boolean esPaciente(){
        return estaIniciandoSesion() && "paciente".equalsIgnoreCase(usuarioActual.getRol());
    }
}

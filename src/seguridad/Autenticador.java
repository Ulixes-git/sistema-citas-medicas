/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import entidades.Usuario;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ulises
 */
public class Autenticador {
    
    private final Map<String, Usuario> usuariosRegistrados;
    
    public Autenticador(){
        this.usuariosRegistrados = new HashMap<>();
        cargarUsuariosIniciales();
    }
    
    private void cargarUsuariosIniciales(){
        
        agregarUsuario(new Usuario(1, "Admin", "Sistema", "admin@test.com", "admin123", "admin"));
        
        agregarUsuario(new Usuario(2, "María", "Gómez", "dra@test.com", "medico123", "medico"));
        
        agregarUsuario(new Usuario(3, "Juan", "Pérez", "paciente@mail.com", "paciente123", "paciente"));
    }
    
    public void agregarUsuario(Usuario usuario){
        if(usuario == null || usuario.getEmail() == null){
            throw new IllegalArgumentException("Usuario o email no pueden ser nulos");
        }
        usuariosRegistrados.put(usuario.getEmail().toLowerCase(), usuario);
    }
    
    public Usuario autenticar(String email, String password) throws CredencialesInvalidasException{
        if(email == null || password == null){
            throw new IllegalArgumentException("Credenciales no pueden ser nulas");
        }
        Usuario usuario = usuariosRegistrados.get(email.toLowerCase());
        if (usuario == null || !usuario.verificarContraseña(password)){
            throw new CredencialesInvalidasException(); 
        }
        return usuario;
    }
}

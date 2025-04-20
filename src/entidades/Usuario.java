/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ulises
 */
public class Usuario {
    //Atributos que se guadaran en cada usuario
    private final int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseñaHash;
    private String rol; //paciente, médico o admin
    
    //Constructor (para crear un usuario)
    public Usuario(int id, String nombre, String apellido, String email, String contraseña, String rol){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.setEmail(email);
        this.setContraseña(contraseña);
        this.rol = rol;
    }
    
    public int getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getRol(){
        return rol;
    }
    
    public void setNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("Nombre no válido");
        }
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido){
        if (apellido == null || apellido.trim().isEmpty()){
            throw new IllegalArgumentException("Apellido no válido");
        }
        this.apellido = apellido;
    }
    
    public void setEmail(String email){
        if (email == null || !email.contains("@") || email.trim().isEmpty()){
            throw new IllegalArgumentException("email no puede estar vacío y debe contener @");
        }
        this.email = email.toLowerCase();
    }
    
    public void setContraseña(String contraseña){
        this.contraseñaHash = encriptarContraseña(contraseña);
    }
    
    public boolean verificarContraseña(String input){
        return BCrypt.checkpw(input, this.contraseñaHash);
    }
    
    private String encriptarContraseña(String contraseña){
        return BCrypt.hashpw(contraseña, BCrypt.gensalt());
    }
    
}

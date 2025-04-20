/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

/**
 *
 * @author Ulises
 */
public class CredencialesInvalidasException extends Exception {
    public CredencialesInvalidasException(){
        super("Email o contraseña incorrectos");
    }
    
}

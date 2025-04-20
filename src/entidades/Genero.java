/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Ulises
 */
public enum Genero {
    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTRO("Otro"),
    NO_ESPECIFICADO("Prefiero no decir");
    
    private final String descripcion;
    
    Genero(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}

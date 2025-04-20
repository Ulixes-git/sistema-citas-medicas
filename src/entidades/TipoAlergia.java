/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author Ulises
 */
public enum TipoAlergia {
    NINGUNA("Ninguna"),
    ALIMENTOS("Alimentos"),
    MEDICAMENTOS("Medicamentos"),
    AMBIENTALES("Ambientales"),
    MULTIPLES("Multiples alergias");
    
    private final String descripcion;
    
    TipoAlergia(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }    
}

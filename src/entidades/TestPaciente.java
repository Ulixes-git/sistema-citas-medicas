/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ulises
 */
public class TestPaciente {
    public static void main(String[] args) {
GestorPacientes gestor = new GestorPacientes();
        
        // 2. Crear un paciente de prueba
        Paciente paciente = new Paciente(
            1, 
            "Juan", 
            "Pérez", 
            "juan@mail.com", 
            "clave123", 
            "01/01/1990",  // fechaNacimientoStr
            "O+",          // tipoSangre
            TipoAlergia.NINGUNA, 
            Genero.MASCULINO, 
            "5551234567", 
            "Calle 123"
        );
        
        // 3. Agregar paciente al gestor
        gestor.agregarPaciente(paciente);
        
        // 4. Buscar el paciente
        Paciente encontrado = gestor.buscarPorId(1);
        System.out.println("Paciente encontrado: " + encontrado.getNombre());
    }
}        

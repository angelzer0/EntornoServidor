/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboral;

/**
 * Esta clase representa una persona
 *
 * @author angel
 *
 */
public class Persona {

    public String nombre;
    public String dni;
    public char sexo;

    /**
     * Constructor
     *
     * @param nombre El nombre de la persona.
     * @param dni El DNI de la persona.
     * @param sexo El sexo de la persona.
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor
     *
     * @param nombre El nombre de la persona.
     * @param sexo El sexo de la persona.
     */
    public Persona(String nombre, char sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni El nuevo DNI de la persona.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Imprime el nombre y el DNI de la persona.
     */
    public void imprime() {
        System.out.println(nombre);
        System.out.println(dni);
    }
}

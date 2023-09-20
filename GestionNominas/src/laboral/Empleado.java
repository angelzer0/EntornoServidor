/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * La clase Empleado representa a una persona que trabaja en una empresa y hereda de la clase Persona.
 *
 * @author angel
 *
 */
package laboral;

public class Empleado extends Persona {

    private int categoria = 1;
    public double anyos = 0;

    /**
     * Constructor
     *
     * @param nombre El nombre del empleado.
     * @param dni El número de DNI del empleado.
     * @param sexo El sexo del empleado.
     */
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
    }

    /**
     * Constructor
     *
     * @param nombre El nombre del empleado.
     * @param sexo El sexo del empleado M(Masculino) o F(Femenino).
     * @param dni El número de DNI del empleado.
     * @param categoria La categoría del empleado (de 1 a 10).
     * @param anyos Los años de servicio del empleado (debe ser mayor o igual a
     * 0).
     * @throws DatosNoCorrectosException Se lanza si los datos proporcionados no
     * son correctos.
     */
    public Empleado(String nombre, char sexo, String dni, int categoria, double anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if (categoria < 1 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
        this.anyos = anyos;
    }

    /**
     * Obtiene la categoría del empleado.
     *
     * @return La categoría del empleado.
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del empleado.
     *
     * @param categoria La nueva categoría del empleado.
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    /**
     * Incrementa en uno el número de años de servicio del empleado.
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     * Imprime los datos del empleado.
     */
    @Override
    public void imprime() {
        System.out.println("Nombre: " + nombre + ", DNI: " + dni + ", Sexo: " + sexo + ", Categoria: " + categoria + ", Anyos: " + anyos);
    }
}

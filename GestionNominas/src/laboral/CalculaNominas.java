/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * La clase CalculaNominas se encarga de calcular los sueldos de los empleados
 * y mostrar sus datos.
 *
 * @author angel
 *
 */
package laboral;

public class CalculaNominas {

    /**
     * El método main crea objetos de la clase Empleado, realiza cálculos de
     * sueldos y muestra los resultados.
     *
     */
    public static void main(String[] args) {
        try {
            // Crear objetos Empleado
            Empleado empleado = new Empleado("James Cosling", 'M', "32000032G", 4, 7);
            Empleado empleado2 = new Empleado("Ada Lovelace", "32000031R", 'F');

            // Mostrar datos y sueldos 
            escribe(empleado, empleado2);

            // Modificar datos de empleado2 y recalcular su sueldo
            empleado2.incrAnyo();
            empleado.setCategoria(9);

            // Mostrar datos y sueldos actualizados
            escribe(empleado, empleado2);
        } catch (DatosNoCorrectosException ex) {
            System.out.println("Datos no correctos");
        }
    }

    /**
     * El método escribe muestra los datos y el sueldo de dos empleados.
     *
     * @param empleado El primer empleado.
     * @param empleado2 El segundo empleado.
     */
    private static void escribe(Empleado empleado, Empleado empleado2) {
        System.out.println("EMPLEADO 1 DATOS:");
        empleado.imprime();
        System.out.println("SUELDO: " + Nomina.sueldo(empleado));
        System.out.println("EMPLEADO 2 DATOS:");
        empleado2.imprime();
        System.out.println("SUELDO: " + Nomina.sueldo(empleado2));
    }
}

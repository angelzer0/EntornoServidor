/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * La clase Nomina se encarga de calcular el sueldo de un empleado en función de su categoría y años de servicio.
 *
 * @author angel
 *
 */
package laboral;

public class Nomina {

    //Sueldo Base
    private static final double SUELDO_BASE[]
            = {50000, 70000, 90000, 110000, 130000,
                150000, 170000, 190000, 210000, 230000};

    /**
     * Calcula el sueldo de un empleado en función de su categoría y años de
     * servicio.
     *
     * @param empleado El objeto Empleado para el cual se calcula el sueldo.
     * @return El sueldo calculado para el empleado.
     */
    public static double sueldo(Empleado empleado) {

        // La categoría se utiliza como índice para acceder al array de sueldos base.
        // Se resta 1 de la categoría del empleado porque los índices de array comienzan en 0.
        double sueldoBase = SUELDO_BASE[empleado.getCategoria() - 1];

        double sueldo = sueldoBase + 5000 * empleado.anyos;

        return sueldo;
    }
}

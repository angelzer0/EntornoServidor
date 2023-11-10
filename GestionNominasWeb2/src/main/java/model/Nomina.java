package model;

/**
 * Clase que representa una nómina de un empleado.
 */
public class Nomina {

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Calcula el sueldo de un empleado a partir de su categoría y años trabajados.
	 *
	 * @param empleado Objeto de la clase Empleado.
	 * @return El sueldo calculado para el empleado.
	 */
	public Double nomina(Empleado empleado) {
		double sueldoBase = 0;

		switch (empleado.getCategoria()) {
		case 1:
			sueldoBase = SUELDO_BASE[0];
			break;
		case 2:
			sueldoBase = SUELDO_BASE[1];
			break;
		case 3:
			sueldoBase = SUELDO_BASE[2];
			break;
		case 4:
			sueldoBase = SUELDO_BASE[3];
			break;
		case 5:
			sueldoBase = SUELDO_BASE[4];
			break;
		case 6:
			sueldoBase = SUELDO_BASE[5];
			break;
		case 7:
			sueldoBase = SUELDO_BASE[6];
			break;
		case 8:
			sueldoBase = SUELDO_BASE[7];
			break;
		case 9:
			sueldoBase = SUELDO_BASE[8];
			break;
		case 10:
			sueldoBase = SUELDO_BASE[9];
			break;
		}

		double sueldo = sueldoBase + (5000 * empleado.getAnyos());
		return sueldo;
	}
}

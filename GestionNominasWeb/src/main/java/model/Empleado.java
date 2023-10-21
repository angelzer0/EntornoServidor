package model;

import exceptions.DatosNoCorrectosException;

/**
 * Clase que representa a un empleado, que extiende de Persona.
 */
public class Empleado {
	public String nombre;
	public String dni;
	public String sexo;
	private int categoria;
	double anyos;

	public Empleado() {
		super();
	}

	/**
	 * Constructor para crear un empleado con datos específicos.
	 *
	 * @param nombre          El nombre del empleado.
	 * @param dni             El número de DNI del empleado.
	 * @param sexo            El género del empleado.
	 * @param categoria       La categoría del empleado.
	 * @param anyosTrabajados Los años trabajados del empleado.
	 * @throws DatosNoCorrectosException Si la categoría o los años trabajados son
	 *                                   incorrectos.
	 */
	public Empleado(String nombre, String dni, String sexo, int categoria, double anyos)
			throws DatosNoCorrectosException {
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("La categoría debe ser un número entre 1 y 10");
		} else {
			this.categoria = categoria;
		}

		if (anyos < 0) {
			throw new DatosNoCorrectosException("Los años deben ser un número positivo");
		} else {
			this.anyos = anyos;
		}
	}

	/**
	 * Constructor para crear un empleado con datos básicos.
	 *
	 * @param nombre El nombre del empleado.
	 * @param dni    El número de DNI del empleado.
	 * @param sexo   El género del empleado.
	 * @throws DatosNoCorrectosException Si la categoría o los años trabajados son
	 *                                   incorrectos.
	 */
	public Empleado(String nombre, String dni, String sexo) throws DatosNoCorrectosException {
		categoria = 1;
		anyos = 0;
	}

	/**
	 * Obtiene la categoría del empleado.
	 *
	 * @return La categoría del empleado.
	 */
	public int getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getAnyos() {
		return anyos;
	}

	public void setAnyosTrabajados(double anyos) {
		this.anyos = anyos;
	}

	/**
	 * Establece la categoría del empleado.
	 *
	 * @param categoria La nueva categoría del empleado.
	 * @throws DatosNoCorrectosException Si la categoría es incorrecta.
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("La categoría debe ser un número entre 1 y 10");
		} else {
			this.categoria = categoria;
		}
	}

	/**
	 * Incrementa en uno los años trabajados del empleado.
	 */
	public void IncrAnyo() {
		anyos++;
	}

	/**
	 * Imprime la información del empleado por pantalla.
	 */
	public void Imprime() {
		System.out.println("El nombre de la persona es: " + nombre + ", y su DNI es: " + dni + ", el sexo es: " + sexo
				+ ", La categoría es: " + categoria + ", y los años trabajados son: " + anyos);
	}
}

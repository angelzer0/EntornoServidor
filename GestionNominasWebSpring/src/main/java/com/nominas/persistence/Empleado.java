package com.nominas.persistence;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * Clase que representa a un empleado en el sistema.
 */
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

    /** Número de serie para la serialización. */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dni", unique = true, nullable = false)
    private String dni; // Clave primaria

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "categoria", nullable = false)
    private int categoria;

    @Column(name = "años", nullable = false)
    private double anyos;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Nomina nomina;

    /**
     * Constructor por defecto de la clase Empleado.
     */
    public Empleado() {

    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return El nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre El nuevo nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el DNI del empleado.
     *
     * @return El DNI del empleado.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del empleado.
     *
     * @param dni El nuevo DNI del empleado.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el sexo del empleado.
     *
     * @return El sexo del empleado.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del empleado.
     *
     * @param sexo El nuevo sexo del empleado.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
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
     * Obtiene los años de antigüedad del empleado.
     *
     * @return Los años de antigüedad del empleado.
     */
    public double getAnyos() {
        return anyos;
    }

    /**
     * Establece los años de antigüedad del empleado.
     *
     * @param anyos Los nuevos años de antigüedad del empleado.
     */
    public void setAnyos(double anyos) {
        this.anyos = anyos;
    }

    /**
     * Obtiene la nómina asociada al empleado.
     *
     * @return La nómina del empleado.
     */
    public Nomina getNomina() {
        return nomina;
    }

    /**
     * Establece la nómina del empleado.
     *
     * @param nomina La nueva nómina del empleado.
     */
    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }
}

package com.nominas.persistence;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "nomina") 
public class Nomina implements Serializable{
	
	/** Serial number */
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nominas", nullable = false)
    private Long id;
    
    @Column(name = "sueldo", nullable = false)
    private Double sueldoCalculado;

    @OneToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni") // fk
    private Empleado empleado;

    // Constructores, getters y setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSueldoCalculado() {
        return sueldoCalculado;
    }

    public void setSueldoCalculado(Double sueldoCalculado) {
        this.sueldoCalculado = sueldoCalculado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    private static final int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };

    /**
     * Calcula el sueldo de un empleado a partir de su categoría y años trabajados.
     *
     * @param empleado Objeto de la clase Empleado.
     * @return El sueldo calculado para el empleado.
     */
    public Double nomina(Empleado empleado) {
        int categoria = empleado.getCategoria();

        // Validar la categoría para asegurarse de que está dentro del rango
        if (categoria < 1 || categoria > SUELDO_BASE.length) {
            throw new IllegalArgumentException("Categoría de empleado no válida");
        }

        double sueldoBase = SUELDO_BASE[categoria - 1]; // Restar 1 porque los índices comienzan desde 0
        double sueldo = sueldoBase + (5000 * empleado.getAnyos());

        return sueldo;
    }
}

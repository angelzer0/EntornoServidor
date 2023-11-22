package com.nominas.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que define métodos de acceso a datos para la entidad Empleado.
 */
public interface EmpleadoRepositoryI extends JpaRepository<Empleado, String> {

    /**
     * Recupera todos los empleados.
     *
     * @return Lista de todos los empleados en la base de datos.
     */
    List<Empleado> findAll();

    /**
     * Recupera un empleado por su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return El empleado con el DNI proporcionado, o null si no se encuentra.
     */
    Empleado findByDni(String dni);

    /**
     * Recupera empleados por su categoría.
     *
     * @param categoria La categoría de los empleados a buscar.
     * @return Lista de empleados con la categoría proporcionada.
     */
    List<Empleado> findByCategoria(int categoria);

    /**
     * Elimina empleados por su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     */
    void deleteByDni(String dni);
}

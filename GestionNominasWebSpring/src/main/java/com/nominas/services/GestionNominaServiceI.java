package com.nominas.services;

import com.nominas.persistence.Empleado;

import java.util.List;

/**
 * Interfaz que define los servicios de gestión de nóminas para los empleados.
 */
public interface GestionNominaServiceI {

    /**
     * Obtiene todos los empleados.
     *
     * @return Lista de todos los empleados en el sistema.
     */
    List<Empleado> obtenerTodosLosEmpleados();

    /**
     * Obtiene un empleado por su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return El empleado con el DNI proporcionado, o null si no se encuentra.
     */
    Empleado obtenerEmpleadoPorDni(String dni);

    /**
     * Obtiene empleados por categoría.
     *
     * @param categoria Categoría de los empleados a buscar.
     * @return Lista de empleados con la categoría proporcionada.
     */
    List<Empleado> obtenerEmpleadosPorCategoria(int categoria);

    /**
     * Elimina un empleado por su DNI.
     *
     * @param dni DNI del empleado a eliminar.
     */
    void eliminarEmpleadoPorDni(String dni);

    /**
     * Modifica la información de un empleado.
     *
     * @param dni             DNI del empleado a modificar.
     * @param nuevaCategoria Nueva categoría del empleado.
     * @param nuevosAnyos    Nuevos años de antigüedad del empleado.
     * @param nuevoNombre    Nuevo nombre del empleado.
     * @param nuevoSexo      Nuevo sexo del empleado.
     */
    void modificarEmpleado(String dni, int nuevaCategoria, double nuevosAnyos, String nuevoNombre, String nuevoSexo);

    /**
     * Crea un nuevo empleado.
     *
     * @param nuevoDni        DNI del nuevo empleado.
     * @param nuevoNombre     Nombre del nuevo empleado.
     * @param nuevoSexo       Sexo del nuevo empleado.
     * @param nuevaCategoria  Categoría del nuevo empleado.
     * @param nuevosAnyos     Años de antigüedad del nuevo empleado.
     */
    void crearEmpleado(String nuevoDni, String nuevoNombre, String nuevoSexo, int nuevaCategoria, double nuevosAnyos);

    /**
     * Calcula la nómina de un empleado.
     *
     * @param empleado El empleado para el cual se calculará la nómina.
     * @return El monto de la nómina del empleado.
     */
    Double calcularNomina(Empleado empleado);
}

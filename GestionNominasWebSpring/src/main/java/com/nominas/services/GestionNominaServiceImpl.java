package com.nominas.services;

import com.nominas.persistence.Nomina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nominas.persistence.Empleado;
import com.nominas.persistence.EmpleadoRepositoryI;

import java.util.List;

/**
 * Implementación de la interfaz GestionNominaServiceI que proporciona servicios relacionados con la gestión de nóminas.
 */
@Service
public class GestionNominaServiceImpl implements GestionNominaServiceI {

    @Autowired
    private EmpleadoRepositoryI empleadoRepository;

    /**
     * Obtiene todos los empleados.
     *
     * @return Lista de todos los empleados en el sistema.
     */
    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    /**
     * Obtiene un empleado por su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return El empleado con el DNI proporcionado, o null si no se encuentra.
     */
    @Override
    public Empleado obtenerEmpleadoPorDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    /**
     * Obtiene empleados por categoría.
     *
     * @param categoria Categoría de los empleados a buscar.
     * @return Lista de empleados con la categoría proporcionada.
     */
    @Override
    public List<Empleado> obtenerEmpleadosPorCategoria(int categoria) {
        return empleadoRepository.findByCategoria(categoria);
    }

    /**
     * Elimina un empleado por su DNI.
     *
     * @param dni DNI del empleado a eliminar.
     */
    @Override
    public void eliminarEmpleadoPorDni(String dni) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado != null) {
            empleadoRepository.delete(empleado);
        }
    }

    /**
     * Modifica la información de un empleado.
     *
     * @param dni             DNI del empleado a modificar.
     * @param nuevaCategoria Nueva categoría del empleado.
     * @param nuevosAnyos    Nuevos años de antigüedad del empleado.
     * @param nuevoNombre    Nuevo nombre del empleado.
     * @param nuevoSexo      Nuevo sexo del empleado.
     */
    @Override
    public void modificarEmpleado(String dni, int nuevaCategoria, double nuevosAnyos, String nuevoNombre, String nuevoSexo) {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado != null) {
            empleado.setCategoria(nuevaCategoria);
            empleado.setAnyos(nuevosAnyos);
            empleado.setNombre(nuevoNombre);
            empleado.setSexo(nuevoSexo);
            empleadoRepository.save(empleado);
        }
    }

    /**
     * Crea un nuevo empleado.
     *
     * @param nuevoDni        DNI del nuevo empleado.
     * @param nuevoNombre     Nombre del nuevo empleado.
     * @param nuevoSexo       Sexo del nuevo empleado.
     * @param nuevaCategoria  Categoría del nuevo empleado.
     * @param nuevosAnyos     Años de antigüedad del nuevo empleado.
     */
    @Override
    public void crearEmpleado(String nuevoDni, String nuevoNombre, String nuevoSexo, int nuevaCategoria, double nuevosAnyos) {
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setDni(nuevoDni);
        nuevoEmpleado.setNombre(nuevoNombre);
        nuevoEmpleado.setSexo(nuevoSexo);
        nuevoEmpleado.setCategoria(nuevaCategoria);
        nuevoEmpleado.setAnyos(nuevosAnyos);

        empleadoRepository.save(nuevoEmpleado);
    }

    /**
     * Calcula la nómina de un empleado.
     *
     * @param empleado El empleado para el cual se calculará la nómina.
     * @return El monto de la nómina del empleado.
     */
    @Override
    public Double calcularNomina(Empleado empleado) {
        Nomina nominaCalculator = new Nomina();
        return nominaCalculator.nomina(empleado);
    }
}


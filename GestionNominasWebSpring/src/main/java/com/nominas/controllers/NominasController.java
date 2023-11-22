package com.nominas.controllers;

import com.nominas.persistence.Empleado;
import com.nominas.services.GestionNominaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con las nóminas y los empleados.
 */
@Controller
public class NominasController {

    private final GestionNominaServiceI gestionNominaService;

    /**
     * Constructor que inyecta la dependencia del servicio de gestión de nóminas.
     *
     * @param gestionNominaService Servicio de gestión de nóminas.
     */
    @Autowired
    public NominasController(GestionNominaServiceI gestionNominaService) {
        this.gestionNominaService = gestionNominaService;
    }

    /**
     * Muestra la página de bienvenida.
     *
     * @return Nombre de la vista "index".
     */
    @RequestMapping("/")
    public String bienvenido() {
        return "index";
    }

    /**
     * Muestra el encabezado de la página.
     *
     * @return Nombre de la vista "header".
     */
    @GetMapping("/header")
    public String mostrarHeader() {
        return "header";
    }

    /**
     * Muestra el pie de página de la página.
     *
     * @return Nombre de la vista "footer".
     */
    @GetMapping("/footer")
    public String mostrarFooter() {
        return "footer";
    }

    /**
     * Muestra la página que lista todos los empleados.
     *
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "muestraEmpleados".
     */
    @GetMapping("/muestraEmpleados")
    public String muestraEmpleados(Model model) {
        List<Empleado> empleados = gestionNominaService.obtenerTodosLosEmpleados();
        model.addAttribute("empleados", empleados);
        return "muestraEmpleados";
    }

    /**
     * Muestra el formulario para buscar un empleado por DNI.
     *
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "formulario".
     */
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mensaje", null);
        return "formulario";
    }

    /**
     * Busca un empleado por DNI y muestra su información salarial.
     *
     * @param dni   DNI del empleado a buscar.
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "muestraSalario" si se encuentra el empleado, "formulario" de lo contrario.
     */
    @PostMapping("/buscar")
    public String buscarEmpleado(@RequestParam String dni, Model model) {
        Empleado empleado = gestionNominaService.obtenerEmpleadoPorDni(dni);
        if (empleado == null) {
            model.addAttribute("mensaje", "El empleado con ese DNI no fue encontrado");
            return "formulario";
        } else {
            double sueldo = gestionNominaService.calcularNomina(empleado);

            model.addAttribute("empleadoNombre", empleado.getNombre());
            model.addAttribute("empleadoDni", empleado.getDni());
            model.addAttribute("sueldo", sueldo);

            model.addAttribute("mensaje", "Empleado recuperado correctamente");
            return "muestraSalario";
        }
    }

    /**
     * Muestra el formulario para modificar la información de un empleado.
     *
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "modificaEmpleado".
     */
    @GetMapping("/modificaEmpleado")
    public String mostrarFormularioModificacion(Model model) {
        model.addAttribute("mensaje", null);
        return "modificaEmpleado";
    }

    /**
     * Modifica la información de un empleado.
     *
     * @param dni             DNI del empleado a modificar.
     * @param nuevaCategoria Nueva categoría del empleado.
     * @param nuevosAnyos    Nuevos años de antigüedad del empleado.
     * @param nuevoNombre    Nuevo nombre del empleado.
     * @param nuevoSexo      Nuevo sexo del empleado.
     * @param model          Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "modificaEmpleado".
     */
    @PostMapping("/modificaEmpleado")
    public String modificarEmpleado(@RequestParam String dni, @RequestParam int nuevaCategoria,
                                    @RequestParam double nuevosAnyos, @RequestParam String nuevoNombre,
                                    @RequestParam String nuevoSexo, Model model) {
        gestionNominaService.modificarEmpleado(dni, nuevaCategoria, nuevosAnyos, nuevoNombre, nuevoSexo);
        model.addAttribute("mensaje", "Empleado modificado correctamente");
        return "modificaEmpleado";
    }

    /**
     * Muestra el formulario para crear un nuevo usuario.
     *
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "crearUsuario".
     */
    @GetMapping("/crearUsuario")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("mensaje", null);
        return "crearUsuario";
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param nuevoDni             DNI del nuevo empleado.
     * @param nuevoNombreCrear     Nombre del nuevo empleado.
     * @param nuevoSexoCrear       Sexo del nuevo empleado.
     * @param nuevaCategoriaCrear  Categoría del nuevo empleado.
     * @param nuevosAnyosCrear     Años de antigüedad del nuevo empleado.
     * @param model                Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "crearUsuario".
     */
    @PostMapping("/crearUsuario")
    public String crearUsuario(@RequestParam String nuevoDni, @RequestParam String nuevoNombreCrear,
                               @RequestParam String nuevoSexoCrear, @RequestParam int nuevaCategoriaCrear,
                               @RequestParam double nuevosAnyosCrear, Model model) {
        gestionNominaService.crearEmpleado(nuevoDni, nuevoNombreCrear, nuevoSexoCrear, nuevaCategoriaCrear,
                nuevosAnyosCrear);
        model.addAttribute("mensaje", "Usuario creado correctamente");
        return "crearUsuario";
    }

    /**
     * Muestra el formulario para eliminar un usuario.
     *
     * @param model Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "eliminarUsuario".
     */
    @GetMapping("/eliminarUsuario")
    public String mostrarFormularioEliminacion(Model model) {
        model.addAttribute("mensaje", null);
        return "eliminarUsuario";
    }

    /**
     * Elimina un usuario por su DNI.
     *
     * @param dniEliminar DNI del usuario a eliminar.
     * @param model      Modelo utilizado para pasar datos a la vista.
     * @return Nombre de la vista "eliminarUsuario".
     */
    @PostMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestParam String dniEliminar, Model model) {
        gestionNominaService.eliminarEmpleadoPorDni(dniEliminar);
        model.addAttribute("mensaje", "Usuario eliminado correctamente");
        return "eliminarUsuario";
    }
}

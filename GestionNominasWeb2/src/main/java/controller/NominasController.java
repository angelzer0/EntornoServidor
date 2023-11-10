package controller;

import dao.EmpleadoDao;
import exceptions.DatosNoCorrectosException;
import model.Empleado;
import model.Nomina;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/NominasController")
public class NominasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        String contentPage = null;

        if (opcion != null) {
            switch (opcion) {
                case "muestraEmpleados":
                    try {
                        ArrayList<Empleado> empleados = EmpleadoDao.obtenerEmpleados();
                        request.setAttribute("empleados", empleados);
                    } catch (SQLException | DatosNoCorrectosException e) {
                        System.out.println(e.getMessage());
                    }
                    contentPage = "views/muestraEmpleados.jsp";
                    break;
                case "muestraSalario":
                    contentPage = "views/formulario.jsp";
                    break;
                case "modificaEmpleado":
                    contentPage = "views/modificaEmpleado.jsp";
                    break;
                case "crearUsuario":
                    contentPage = "views/crearUsuario.jsp";
                    break;
                case "eliminarUsuario":
                    contentPage = "views/eliminarUsuario.jsp";
                    break;
                default:
                    contentPage = "views/bienvenido.jsp";
                    break;
            }
        }

        request.setAttribute("content", contentPage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if (opcion != null) {
            switch (opcion) {
                case "buscar":
                    String dni = request.getParameter("dni");
                    try {
                        Empleado empleado = EmpleadoDao.obtenerEmpleadoPorDNI(dni);
                        if (empleado == null) {
                            request.setAttribute("mensaje", "El empleado con ese DNI no fue encontrado");
                            request.setAttribute("content", "views/formulario.jsp");
                        } else {
                            Nomina nomina = new Nomina();
                            double sueldo = nomina.nomina(empleado);

                            request.setAttribute("empleadoNombre", empleado.getNombre());
                            request.setAttribute("empleadoDni", empleado.getDni());
                            request.setAttribute("sueldo", sueldo);

                            request.setAttribute("mensaje", "Empleado recuperado correctamente");
                            request.setAttribute("content", "views/muestraSalario.jsp");
                        }
                    } catch (SQLException | DatosNoCorrectosException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "modificarEmpleado":
                    String dniModificar = request.getParameter("dni");
                    int nuevaCategoria = Integer.parseInt(request.getParameter("nuevaCategoria"));
                    double nuevosAnyos = Double.parseDouble(request.getParameter("nuevosAnyos"));
                    String nuevoNombre = request.getParameter("nuevoNombre");
                    String nuevoSexo = request.getParameter("nuevoSexo");

                    try {
                        EmpleadoDao.modificarEmpleado(dniModificar, nuevaCategoria, nuevosAnyos, nuevoNombre, nuevoSexo);
                        request.setAttribute("mensaje", "Empleado modificado correctamente");
                        request.setAttribute("content", "views/modificaEmpleado.jsp");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "crearUsuario":
                    String nuevoDni = request.getParameter("nuevoDni");
                    String nuevoNombreCrear = request.getParameter("nuevoNombre");
                    String nuevoSexoCrear = request.getParameter("nuevoSexo");
                    int nuevaCategoriaCrear = Integer.parseInt(request.getParameter("nuevaCategoria"));
                    double nuevosAnyosCrear = Double.parseDouble(request.getParameter("nuevosAnyos"));

                    try {
                        EmpleadoDao.crearEmpleado(nuevoDni, nuevoNombreCrear, nuevoSexoCrear, nuevaCategoriaCrear, nuevosAnyosCrear);
                        request.setAttribute("mensaje", "Usuario creado correctamente");
                        request.setAttribute("content", "views/crearUsuario.jsp");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "eliminarUsuario":
                    String dniEliminar = request.getParameter("dniEliminar");

                    try {
                        EmpleadoDao.eliminarEmpleado(dniEliminar);
                        request.setAttribute("mensaje", "Usuario eliminado correctamente");
                        request.setAttribute("content", "views/eliminarUsuario.jsp");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                default:
                    // Lógica predeterminada si no se proporciona una opción válida
                    request.setAttribute("content", "views/bienvenido.jsp");
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}

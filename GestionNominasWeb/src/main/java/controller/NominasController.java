package controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Nomina;
import conexion.DBUtils;
import exceptions.DatosNoCorrectosException;
import model.Empleado;

/**
 * Controlador de servlet para la gestión de nóminas de empleados. Este servlet
 * maneja las solicitudes relacionadas con la visualización de empleados,
 * cálculo de salarios y modificación de datos de empleados.
 */

@WebServlet("/NominasController")
public class NominasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto del servlet.
	 */

	public NominasController() {
		super();
	}

	/**
	 * Método GET para manejar las solicitudes GET enviadas al servlet. Utilizado
	 * para mostrar información de empleados, mostrar salario de empleado y
	 * redirigir a la página de modificación de empleado.
	 *
	 * @param request  La solicitud HTTP.
	 * @param response La respuesta HTTP.
	 * @throws ServletException Si ocurre un error en el servlet.
	 * @throws IOException      Si ocurre un error de E/S.
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("muestraEmpleados")) {
			ArrayList<Empleado> empleados = new ArrayList<>();
			Empleado empleado = null;
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;

			try {
				con = DBUtils.getConnection();
				st = con.createStatement();
				// Consulta todos los empleados
				rs = st.executeQuery("Select * from empleados");
				while (rs.next()) {
					String nombre = rs.getString(1);
					String dni = rs.getString(2);
					String sexo = rs.getString(3);
					int categoria = rs.getInt(4);
					double anyos = rs.getDouble(5);

					empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
					empleados.add(empleado);
				}
				request.setAttribute("empleados", empleados);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/muestraEmpleados.jsp");
				requestDispatcher.forward(request, response);
				rs.close();
				st.close();
				con.close();
			} catch (SQLException | DatosNoCorrectosException e) {
				System.out.println(e.getMessage());
			}
		} else if (opcion.equals("muestraSalario")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/formulario.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("modificaEmpleado")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/modificaEmpleado.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * Método POST para manejar las solicitudes POST enviadas al servlet. Utilizado
	 * para buscar empleados por DNI, mostrar su salario y modificar los datos de un
	 * empleado.
	 *
	 * @param request  La solicitud HTTP.
	 * @param response La respuesta HTTP.
	 * @throws ServletException Si ocurre un error en el servlet.
	 * @throws IOException      Si ocurre un error de E/S.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("buscar")) {
			Empleado empleado = new Empleado();
			empleado.setDni(request.getParameter("dni"));
			if (empleado.getDni().isEmpty()) {
				request.setAttribute("mensaje", "El empleado con ese DNI no fue encontrado");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/formulario.jsp");
				requestDispatcher.forward(request, response);
			} else {
				Empleado empleado2 = null;
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;

				try {
					String dniEmpleado = request.getParameter("dni");
					con = DBUtils.getConnection();
					st = con.createStatement();
					// Consulta por dni empleado
					rs = st.executeQuery("Select * from empleados where dni = '" + dniEmpleado + "'");
					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						String sexo = rs.getString(3);
						int categoria = rs.getInt(4);
						double anyos = rs.getDouble(5);

						empleado2 = new Empleado(nombre, dni, sexo, categoria, anyos);
					}
					Nomina nomina = new Nomina();
					double sueldo = nomina.nomina(empleado2);
					System.out.println(empleado2.toString());

					request.setAttribute("empleadoNombre", empleado2.getNombre());
					request.setAttribute("empleadoDni", empleado2.getDni());
					request.setAttribute("sueldo", sueldo);
				} catch (SQLException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				request.setAttribute("mensaje", "Empleado recuperado correctamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/muestraSalario.jsp");
				requestDispatcher.forward(request, response);
			}
		} else if (opcion.equals("modificarEmpleado")) {
			// Lógica para modificar al empleado
			String dniEmpleado = request.getParameter("dni");
			int nuevaCategoria = Integer.parseInt(request.getParameter("nuevaCategoria"));
			double nuevosAnyos = Double.parseDouble(request.getParameter("nuevosAnyos"));

			Connection con = null;
			Statement st = null;

			try {
				con = DBUtils.getConnection();
				st = con.createStatement();

				// Actualizar empleados
				String sql = "UPDATE empleados SET categoria = " + nuevaCategoria + ", anyos = " + nuevosAnyos
						+ " WHERE dni = '" + dniEmpleado + "'";
				st.executeUpdate(sql);

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

			request.setAttribute("mensaje", "Empleado modificado correctamente");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}

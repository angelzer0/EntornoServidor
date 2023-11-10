package dao;

import conexion.DBUtils;
import exceptions.DatosNoCorrectosException;
import model.Empleado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDao {

    public static ArrayList<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {
        ArrayList<Empleado> empleados = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * from empleados");
            while (rs.next()) {
                String dni = rs.getString(1);
                String nombre = rs.getString(2);
                String sexo = rs.getString(3);
                int categoria = rs.getInt(4);
                double anyos = rs.getDouble(5);

                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
                empleados.add(empleado);
            }
        } finally { 
           	rs.close();
            st.close();
            con.close();
        }

        return empleados;
    }

    public static Empleado obtenerEmpleadoPorDNI(String dni) throws SQLException, DatosNoCorrectosException {
        Empleado empleado = null;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * from empleados where dni = '" + dni + "'");
            if (rs.next()) {
                String nombre = rs.getString(2);
                String sexo = rs.getString(3);
                int categoria = rs.getInt(4);
                double anyos = rs.getDouble(5);

                empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
            }
        } finally {
        	rs.close();
            st.close();
            con.close();
        }

        return empleado;
    }

    public static void modificarEmpleado(String dni, int nuevaCategoria, double nuevosAnyos, String nuevoNombre, String nuevoSexo) throws SQLException {
        Connection con = null;
        Statement st = null;

        try {
            con = DBUtils.getConnection();
            st = con.createStatement();

            String sql = "UPDATE empleados SET categoria = " + nuevaCategoria + ", anyos = " + nuevosAnyos
                    + ", nombre = '" + nuevoNombre + "', sexo = '" + nuevoSexo + "' WHERE dni = '" + dni + "'";
            st.executeUpdate(sql);
        } finally {
        	 st.close();
             con.close();
        }
    }

    public static void crearEmpleado(String nuevoDni, String nuevoNombre, String nuevoSexo, int nuevaCategoria, double nuevosAnyos) throws SQLException {
        Connection con = null;
        Statement st = null;

        try {
            con = DBUtils.getConnection();
            st = con.createStatement();

            String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) " +
                    "VALUES ('" + nuevoDni + "', '" + nuevoNombre + "', '" + nuevoSexo + "', " + nuevaCategoria + ", " + nuevosAnyos + ")";
            st.executeUpdate(sql);
        } finally {
        	 st.close();
             con.close();
        }
    }

    public static void eliminarEmpleado(String dniEliminar) throws SQLException {
        Connection con = null;
        Statement st = null;

        try {
            con = DBUtils.getConnection();
            st = con.createStatement();

            String sql = "DELETE FROM empleados WHERE dni = '" + dniEliminar + "'";
            st.executeUpdate(sql);
        } finally {
            st.close();
            con.close();
        }
    }

    // Puedes agregar más métodos aquí para otras operaciones de base de datos relacionadas con empleados.
}

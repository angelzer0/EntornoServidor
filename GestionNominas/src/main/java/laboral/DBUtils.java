/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboral;

/**
 *
 * @author angel
 */
import java.sql.Connection;

/**
 * La clase DBUtils proporciona métodos para obtener y cerrar conexiones de la
 * base de datos.
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
      public static Connection getConnection() throws SQLException {
        final String USER = "root";
        final String PASS = "123456";
        final String DB_NAME = "gestion_nominas";
        final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
        Connection conn = null;

        conn = DriverManager.getConnection(CONN_URL, USER, PASS);
        return conn;

    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void close(Statement st) throws SQLException {
        if (st != null) {
            st.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }
}
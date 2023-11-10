package conexion;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Clase para gestionar la conexión con la base de datos.
 */
public class DBUtils {
	 private static BasicDataSource dataSource = null;
	
	/**
	 * Obtiene el DataSource para la base de datos.
	 * @return El DataSource configurado para la base de datos.
	 */
	 private static DataSource getDataSource() {
	   if (dataSource == null) {
	    dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUsername("root");
	    dataSource.setPassword("123456"); 
	    dataSource.setUrl("jdbc:mysql://localhost:3306/gestion_nominas");
	   }
	   return dataSource;
	  }
	
	/**
	 * Obtiene una conexión a la base de datos.
	 * @return Una conexión a la base de datos.
	 * @throws SQLException Si ocurre un error al establecer la conexión.
	 */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	/**
	 * Cierra la conexión a la base de datos.
	 * @param conn La conexión que se desea cerrar.
	 * @throws SQLException Si ocurre un error al cerrar la conexión.
	 */
	public void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
	/**
	 * Cierra un Statement utilizado para ejecutar consultas SQL.
	 * @param st El Statement que se desea cerrar.
	 * @throws SQLException Si ocurre un error al cerrar el Statement.
	 */
	public void close(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}
 
	/**
	 * Cierra un ResultSet que almacena los resultados de una consulta SQL.
	 * @param rs El ResultSet que se desea cerrar.
	 * @throws SQLException Si ocurre un error al cerrar el ResultSet.
	 */
	public void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}

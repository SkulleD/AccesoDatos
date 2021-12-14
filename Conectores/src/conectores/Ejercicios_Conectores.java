package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicios_Conectores {
	private Connection conexion;

	public void abrirConexion(String bd, String servidor, String usuario, String password) {
		try {
			String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
			this.conexion = DriverManager.getConnection(url, usuario, password); // Establecemos la conexión con la BD
			if (this.conexion != null)
				System.out.println("Conectado a la base de datos " + bd + " en " + servidor);
			else
				System.out.println("No se ha conectado a la base de datos " + bd + " en " + servidor);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getLocalizedMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("Código error: " + e.getErrorCode());
		}
	}
	
	public void cerrarConexion (){
			this.conexion.close();
			 } catch (SQLException e) {
		 System.out.println("Error al cerrar la conexión: "+e.getLocalizedMessage());
		 }
		}
	
	public void ejercicio1(String cadena) throws SQLException {
		String query = "SELECT * FROM alumnos WHERE nombre = " + cadena;
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			System.out.println(rs.getInt(1)) + "\t" + rs.getString("nombre");
		}
		
		stmt.close();
		cerrarConexion();
	}
	
	public static void main(String[] args) {

	}
}

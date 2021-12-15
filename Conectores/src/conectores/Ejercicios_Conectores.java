package conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicios_Conectores {
	private Connection conexion;
	private static Ejercicios_Conectores ej;

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

	public void cerrarConexion() {
		try {
			this.conexion.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
		}
	}

	public void ejercicio1(String cadena) throws SQLException { // Consultar alumnos con determinada cadena de caracteres en nombre
		String query = "SELECT * FROM alumnos WHERE nombre LIKE \"%" + cadena + "%\"";
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int cont = 0;

		while (rs.next()) {
			cont++;
			System.out.println(rs.getString("nombre"));
		}
		
		System.out.println("Número resultados: " + cont);
		
		stmt.close();
		cerrarConexion();
	}
	
	public void ejercicio2(int IDalumno, String nombre, String apellido, int altura, int aula, int CODasignatura, String nombreAsignatura) throws SQLException { // Dar de alta alumnos y asignaturas
		String query = "INSERT INTO alumnos VALUES (" + IDalumno + ", \"" + nombre + "\", \"" + apellido + "\", " + altura + ", " + aula + ");"
				+ "\nINSERT INTO asignaturas VALUES (" + CODasignatura + ", \"" + nombreAsignatura + "\")";
		System.out.println(query);
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();

		int insertAlumnos = stmt.executeUpdate(query);
		System.out.println("Filas insertadas: " + insertAlumnos);
		cerrarConexion();
	}
	
	public void ejercicio3() {
		
	}

	public static void main(String[] args) {
		 ej = new Ejercicios_Conectores();
		 
		try {
			ej.ejercicio2(11, "Rovaalvaro", "Vila", 182, 20, 11, "nUEVA 2");
		} catch (SQLException e) {

		}
	}
}
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
	
	public void ejercicio2(int IDalumno, String nombre, String apellido, int altura, int aula, int CODasignatura, String nombreAsignatura) throws SQLException { // Dar de alta un alumno y una asignatura
		String query = "INSERT INTO alumnos VALUES (" + IDalumno + ", \"" + nombre + "\", \"" + apellido + "\", " + altura + ", " + aula + ");";
		String query2 = "\nINSERT INTO asignaturas VALUES (" + CODasignatura + ", \"" + nombreAsignatura + "\");";
		System.out.print(query + query2 + "\n");
		
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();

		int insert = stmt.executeUpdate(query);
		int insert2 = stmt.executeUpdate(query2);
		System.out.println("Filas insertadas: " + (insert + insert2));
		cerrarConexion();
	}
	
	public void ejercicio3(String nombreAlumno, String nombreAsig) throws SQLException { // Eliminar un alumno y una asignatura
		String query = "DELETE FROM alumnos WHERE nombre = '" + nombreAlumno +"';";
		String query2 = "DELETE FROM asignaturas WHERE nombre = '" + nombreAsig +"';";
		System.out.print(query + "\n" + query2 + "\n");
		
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();
		
		int remove = stmt.executeUpdate(query);
		int remove2 = stmt.executeUpdate(query2);
		System.out.println("Filas eliminadas: " + (remove + remove2));
		cerrarConexion();
	}
	
	public void ejercicio4() throws SQLException { // Modificar un alumno y una asignatura
		String query = "";
		String query2 = "";
		System.out.print(query + "\n" + query2 + "\n");
		
		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();
		
		int modify = stmt.executeUpdate(query);
		int modify2 = stmt.executeUpdate(query2);
		System.out.println("Filas modificadas: " + (modify + modify2));
		cerrarConexion();
	}

	public static void main(String[] args) {
		 ej = new Ejercicios_Conectores();
		 
		try {
			//ej.ejercicio2(14, "Rovaaytjyyhlvaro", "Byla", 182, 20, 12, "nUEVA 2");
			//ej.ejercicio3("Frank", "Programacion");
		} catch (SQLException e) {

		}
	}
}
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

	public void ejercicio1(String cadena) throws SQLException { // Consultar alumnos con determinada cadena de
																// caracteres en nombre
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

	public int ejecuto(String query) {
		abrirConexion("add", "localhost", "root", "");
		Statement stmt;
		int insert = -1;

		try {
			stmt = this.conexion.createStatement();
			insert = stmt.executeUpdate(query);
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			cerrarConexion();
		}
		return insert;
	}

	public String imprimoAulas(String query) { // Imprime las aulas que tengan ALUMNOS
		abrirConexion("add", "localhost", "root", "");

		ResultSet rs;
		String aulas = "";

		try (Statement stmt = this.conexion.createStatement();) {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				aulas += "\n" + rs.getString("nombreAula");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cerrarConexion();

		return aulas;
	}

	public String imprimoAprobados(String query) { // Imprime los alumnos, asignaturas y notas de los alumnos que HAN
													// APROBADO
		abrirConexion("add", "localhost", "root", "");
		Statement stmt;
		ResultSet rs;
		String aulas = "";

		try {
			stmt = this.conexion.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				aulas += "\n" + rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getInt(3);
			}
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			cerrarConexion();
		}
		return aulas;
	}

	public String imprimoAsignaturas(String query) { // Imprime las asignaturas SIN ALUMNOS
		abrirConexion("add", "localhost", "root", "");
		Statement stmt;
		ResultSet rs;
		String aulas = "";

		try {
			stmt = this.conexion.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				aulas += "\n" + rs.getString("nombreAula");
			}
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			cerrarConexion();
		}
		return aulas;
	}

	public int ejercicio2a(int IDalumno, String nombre, String apellido, int altura, int aula, int CODasignatura,
			String nombreAsignatura) throws SQLException { // Dar de alta un alumno
		String query = "INSERT INTO alumnos VALUES (" + IDalumno + ", '" + nombre + "', \"" + apellido + "\", " + altura
				+ ", " + aula + ");";
		return ejecuto(query);
	}

	public int ejercicio2b(int IDalumno, String nombre, String apellido, int altura, int aula, int CODasignatura,
			String nombreAsignatura) throws SQLException { // Dar de alta una asignatura
		String query = "INSERT INTO asignaturas VALUES (" + CODasignatura + ", \"" + nombreAsignatura + "\");";
		return ejecuto(query);
	}

	public int ejercicio3a(int IDnombre, int IDasign) throws SQLException { // Eliminar un alumno
		String query = "DELETE FROM alumnos WHERE nombre = '" + IDnombre + "';";
		return ejecuto(query);
	}

	public int ejercicio3b(int IDnombre, int IDasign) throws SQLException { // Eliminar una asignatura
		String query = "DELETE FROM asignaturas WHERE nombre = '" + IDasign + "';";
		return ejecuto(query);
	}

	public int ejercicio4a(int IDnombre, String nombre, String apellidos, int altura, int aula, int IDasign,
			String nombreAsign) throws SQLException { // Modificar un alumno
		String query = "UPDATE alumnos" + "SET nombre = '" + nombre + "', apellidos = '" + apellidos + "', altura = "
				+ altura + ", aula = " + aula + " " + "WHERE codigo = " + IDnombre + ";";
		return ejecuto(query);
	}

	public int ejercicio4b(int IDnombre, String nombre, String apellidos, int altura, int aula, int IDasign,
			String nombreAsign) throws SQLException { // Modificar una asignatura
		String query = "UPDATE asignaturas" + "SET NOMBRE = '" + nombreAsign + "' " + "WHERE cod = " + IDasign + ";";
		return ejecuto(query);
	}

	public String ejercicio5a() { // 1: Nombre de aulas con alumnos
		String query = "SELECT DISTINCT aulas.nombreAula FROM aulas INNER JOIN alumnos ON aulas.numero = alumnos.aula WHERE nombreAula IS NOT NULL";
		return imprimoAulas(query);
	}

	public String ejercicio5b() { // 2: Nombres de alumnos, de asignaturas y notas de los que han aprobado alguna
		String query = "SELECT DISTINCT alumnos.nombre, asignaturas.NOMBRE, notas.NOTA\r\n"
				+ "FROM alumnos, asignaturas, notas WHERE notas.NOTA >= 5\r\n"
				+ "AND alumnos.codigo = notas.alumno AND asignaturas.COD = notas.asignatura";
		return imprimoAprobados(query);
	}

	public String ejercicio5c() { // 3: Asignaturas sin alumnos
		String query = "";
		return imprimoAsignaturas(query);
	}

	public static void main(String[] args) throws SQLException {
		ej = new Ejercicios_Conectores();

		// ej.ejercicio1("a");
		// ej.ejercicio2(14, "Rovaro", "Byla", 182, 20, 12, "nUEVA 2");
		// ej.ejercicio3(7, 9);
		// ej.ejercicio4(14, "newNombre", "newApellidos", 179, 20, 12, "NUEVA ASIGN 3");
		System.out.print(ej.ejercicio5b());
	}
}
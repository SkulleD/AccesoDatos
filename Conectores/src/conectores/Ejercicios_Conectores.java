package conectores;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
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

		// abrirConexion("add", "localhost", "root", "");
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
		} catch (SQLException e) {
			e.printStackTrace();

		}

		cerrarConexion();
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
		String alumnos = "";

		try {
			stmt = this.conexion.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				alumnos += "\n" + rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cerrarConexion();
		return alumnos;
	}

	public String imprimoAsignaturas(String query) { // Imprime las asignaturas SIN ALUMNOS
		abrirConexion("add", "localhost", "root", "");
		Statement stmt;
		ResultSet rs;
		String asignaturas = "";

		try {
			stmt = this.conexion.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				asignaturas += "\n" + rs.getString("NOMBRE");
			}
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			cerrarConexion();
		}
		return asignaturas;
	}

	public int ejercicio2a(int IDalumno, String nombre, String apellido, int altura, int aula) throws SQLException { // Dar
																														// de
																														// alta
																														// un
																														// alumno
		String query = "INSERT INTO alumnos VALUES (" + IDalumno + ", '" + nombre + "', \"" + apellido + "\", " + altura
				+ ", " + aula + ");";
		return ejecuto(query);
	}

	public int ejercicio2b(int CODasignatura, String nombreAsignatura) throws SQLException { // Dar de alta una
																								// asignatura
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

	public int ejercicio4a(int IDnombre, String nombre, String apellidos, int altura, int aula) throws SQLException { // Modificar
																														// un
																														// alumno
		String query = "UPDATE alumnos" + "SET nombre = '" + nombre + "', apellidos = '" + apellidos + "', altura = "
				+ altura + ", aula = " + aula + " " + "WHERE codigo = " + IDnombre + ";";
		return ejecuto(query);
	}

	public int ejercicio4b(int IDasign, String nombreAsign) throws SQLException { // Modificar una asignatura
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
		String query = "SELECT asignaturas.NOMBRE FROM asignaturas WHERE asignaturas.COD NOT IN (SELECT DISTINCT asignatura FROM notas)";
		return imprimoAsignaturas(query);
	}

	public int ejercicio6a(String patron, int altura) throws SQLException { // Consultar alumnos cuyo nombre contiene
																			// cierto patrón y su altura es mayor que
																			// cierto número
		String query = "SELECT nombre, altura FROM alumnos WHERE nombre LIKE '%" + patron + "%' AND altura > " + altura
				+ ";";
		// SELECT nombre, altura FROM alumnos WHERE nombre LIKE '%ar%' AND altura > 175;

		abrirConexion("add", "localhost", "root", "");
		Statement stmt = this.conexion.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int cont = 0;

		while (rs.next()) {
			cont++;
			System.out.println(rs.getString("nombre") + " " + rs.getInt("altura"));
		}

		stmt.close();
		cerrarConexion();
		return ejecuto(query);
	}

	PreparedStatement ps = null;

	public void ejercicio6b(String patron, int altura) { // Consultar alumnos cuyo nombre contiene
															// cierto patrón y su altura es mayor que
															// cierto número con SENTENCIA PREPARADA
		String query = "SELECT nombre, altura FROM alumnos WHERE nombre LIKE ? AND altura > ?";

		try {
			conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/add?useServerPrepStmts=true", "root",
					"");

			if (ps == null) {
				ps = conexion.prepareStatement(query);
			}

			ps.setString(1, patron);
			ps.setInt(2, altura);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				System.out.println(resultado.getString("nombre") + "" + resultado.getInt("altura"));
			}
			cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ejercicio7() throws SQLException { // Ejecutar métodos anteriores calculando tiempo de ejecución dentro
													// de un bucle

		abrirConexion("add", "localhost", "root", "");
		long inicio = System.currentTimeMillis();

		try {
			for (long i = 0; i < 8000; i++) {
				ej.ejercicio1("a");
				System.out.println(i);
			}
		} catch (SQLInvalidAuthorizationSpecException e) {

		}

		// long fin = System.currentTimeMillis();
		// double tiempo = (double) ((fin - inicio) / 1000);
		// System.out.printf("Tiempo total: %.2f segundos", tiempo);
		inicio = (System.currentTimeMillis() - inicio);
		double time = (double) inicio / 1000;
		// System.out.printf("Time: %.3f",time);
	}

	public int ejercicio8(String nombreTabla, String nombreCol1, String nombreCol2) {
//	CREATE OR REPLACE TABLE nuevatabla2(
//		columna1 VARCHAR(9),
//		columna2 INT(15)
//	)
		String query = "CREATE OR REPLACE TABLE " + nombreTabla + "(" + nombreCol1 + " VARCHAR(9), " + nombreCol2
				+ " INT(15))";
		System.out.println(query);
		return ejecuto(query);
	}

	public void ejercicio9a(String database) { // Apartado A
		DatabaseMetaData dbmt;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();

			System.out.println("Driver name: " + dbmt.getDriverName());
			System.out.println("Driver version: " + dbmt.getDriverVersion());
			System.out.println("Connection URL: " + dbmt.getURL());
			System.out.println("User: " + dbmt.getUserName());
			System.out.println("SBGD name: " + dbmt.getDatabaseProductName());
			System.out.println("SGBD version: " + dbmt.getDatabaseProductVersion());
			System.out.println("Reserved SGBD words: " + dbmt.getSQLKeywords());

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public void ejercicio9b(String database) { // Apartado B
		DatabaseMetaData dbmt;
		ResultSet rs;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();
			rs = dbmt.getCatalogs();

			while (rs.next()) {
				System.out.println(rs.getString("TABLE_CAT"));
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public void ejercicio9c(String database) { // Apartado C
		DatabaseMetaData dbmt;
		ResultSet rs;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();
			rs = dbmt.getTables(database, null, null, null);

			while (rs.next()) {
				System.out.println(
						"Table name: " + rs.getString("TABLE_NAME") + "\nTable type:" + rs.getString("TABLE_TYPE"));
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public void ejercicio9d(String database) { // Apartado D
		DatabaseMetaData dbmt;
		ResultSet rs = null;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();
			rs = dbmt.getTables(database, null, null, null);

			while (rs.next()) {
				if (rs.getString("TABLE_TYPE").equals("VIEW")) {
					System.out.println(
							"Table name: " + rs.getString("TABLE_NAME") + "\nTable type:" + rs.getString("TABLE_TYPE"));
				}
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public void ejercicio9e(String database) { // Apartado E
		DatabaseMetaData dbmt;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();
			rs1 = dbmt.getTables(database, null, null, null);
			rs2 = dbmt.getCatalogs();

			while (rs2.next()) {
				System.out.println(rs2.getString("TABLE_CAT"));
			}
			
			System.out.println("---------");

			while (rs1.next()) {
				System.out.println(
						"Table name: " + rs1.getString("TABLE_NAME") + "\nTable type:" + rs1.getString("TABLE_TYPE"));
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public void ejercicio9f(String database) { // Apartado F
		DatabaseMetaData dbmt;
		ResultSet rs = null;
		abrirConexion("add", "localhost", "root", "");

		try {
			dbmt = this.conexion.getMetaData();
			rs = dbmt.getTables(database, null, null, null);

		} catch (SQLException e) {
			System.out.println("Error al obtener datos" + e.getLocalizedMessage());
		}

		cerrarConexion();
	}

	public static void main(String[] args) throws SQLException {
		ej = new Ejercicios_Conectores();

		// ej.ejercicio1("a");
		// ej.ejercicio2(14, "Rovaro", "Byla", 182, 20, 12, "nUEVA 2");
		// ej.ejercicio3(7, 9);
		// ej.ejercicio4(14, "newNombre", "newApellidos", 179, 20, 12, "NUEVA ASIGN 3");
		// System.out.println(ej.ejercicio5c());
		// ej.ejercicio6b("%ar%", 178);

//		try {
//			ej.ejercicio7();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		// ej.ejercicio8("NUEVA3JAVA", "java1", "kava2");
		ej.ejercicio9e("add");
	}
}
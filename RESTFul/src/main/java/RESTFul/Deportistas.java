package RESTFul;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/deportista")
public class Deportistas {
	private Connection conexion;
	private static Deportistas deportistas;
	private ArrayList<Deportista> deportiLista = new ArrayList<>();

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

	// EJERCICIO 1
	@GET
	@Path("todos")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response Todos() {
		String query = "Select * from deportistas";
		try (Statement st = conexion.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			Deportista deportista;
			while (rs.next()) {
				System.out.println(rs.getString("nombre"));
			}
			return Response.ok(this.deportistas).build();
		} catch (SQLException e) {

		}
		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).build();
	}

	// EJERCICIO 2
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response buscaJugador(@PathParam("id") int id) throws SQLException {
		try (Statement stmt = this.conexion.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM deportistas WHERE id = " + id);
			Deportista deportista;

			while (rs.next()) {
				deportista = new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getString("deporte"),
						rs.getBoolean("activo"), rs.getString("genero"));
				deportiLista.add(deportista);

				System.out.println(deportista.getNombre());
				return Response.ok(deportiLista).build();
			}
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	// EJERCICIO 3
	@GET
	@Path("/deporte/{nombreDeporte}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response porDeporte(@PathParam("nombreDeporte") String nombreDeporte) throws SQLException {
		try (Statement stmt = this.conexion.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM deportistas WHERE deporte = '" + nombreDeporte + "'");
			Deportista deportista;

			while (rs.next()) {
				deportista = new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getString("deporte"),
						rs.getBoolean("activo"), rs.getString("genero"));
				deportiLista.add(deportista);
				
				System.out.println(deportista.getNombre());
			}
			return Response.ok(deportiLista).build();
		}
	}
	// EJERCICIO 4

	// EJERCICIO 5

	// EJERCICIO 6

	// EJERCICIO 7

	// EJERCICIO 8

	// EJERCICIO 9

	// EJERCICIO 10

	// EJERCICIO 11

	// EJERCICIO 12

	// EJERCICIO 13

	// EJERCICIO 14

	// EJERCICIO 15

	// EJERCICIO 16

	// EJERCICIO 17

	// EJERCICIO 19

	// EJERCICIO 20

	// EJERCICIO 21

	// EJERCICIO 22

	// EJERCICIO 23

	// EJERCICIO 24

	// EJERCICIO 25

	public static void main(String[] args) {
		deportistas = new Deportistas();
		deportistas.abrirConexion("ad_tema6", "localhost", "root", "");

		try {
			// deportistas.Todos("SELECT * FROM deportistas;");
			// deportistas.buscaJugador(5);
			deportistas.porDeporte("Baloncesto");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		deportistas.cerrarConexion();
	}
}

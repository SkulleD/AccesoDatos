import java.io.Console;
import java.sql.*;

public class JDBC_ejers {
    Connection conexion;

    public void abreConexion(String bd, String server, String user, String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", server, bd);

            this.conexion = DriverManager.getConnection(url, user, password);
            if (this.conexion != null) {
                System.out.println("Conectado a la base de datos " + bd + " en " + server);
            } else {
                System.out.println("Conexión a la base de datos " + bd + " en " + " FALLIDA");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public void cierraConexion() {
        try {
            this.conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public void ej1(String nombre, double valor) {
        String query = "select " + nombre + " from naves where " + nombre + " = " + valor;

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println(nombre);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(nombre));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej1(String nombre, String valor) {
        String query = "select " + nombre + " from naves where " + nombre + " = '" + valor + "' ";

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println(nombre);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(nombre));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej2(int valor) {
        String query = "delete from naves where " + valor + " = id";
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            filas = statement.executeUpdate(query);
            System.out.println("Filas eliminadas: " + filas);
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej3(int valor, String nombre, String pais, String fabricante) {
        String query = "update naves set nombre = '" + nombre + "', pais = '" + pais + "', fabricante = '" + fabricante
                + "' where id = " + valor;
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            filas = statement.executeUpdate(query);
            System.out.println("Filas actualizadas: " + filas);
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej4(String nombre, String pais, String fabricante, String lanzamiento) {
        String query = "insert into naves (nombre, pais, fabricante, SistemaLanzamiento) values ('" + nombre + "', '"
                + pais + "', '" + fabricante + "', '" + lanzamiento + "')";
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            filas = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej5() {
        String query = "";

        try (Statement statement = this.conexion.createStatement()) {

        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        JDBC_ejers jdbc = new JDBC_ejers();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fallo al buscar el driver: " + e.getLocalizedMessage());
        }

        jdbc.abreConexion("add", "localhost", "root", "");
        //jdbc.ej1("pais", "Japón");
        jdbc.ej4("Nuegbrdtyue2332", "Nuevthrs3244", "Nuevohcnte234", "NuENTO23423");
        jdbc.cierraConexion();
    }
}
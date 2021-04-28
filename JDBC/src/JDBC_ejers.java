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

    public void ej5a(String pais) {
        String query = "SELECT pais, COUNT(pais) AS \"Nº Naves\" FROM naves WHERE pais = '" + pais + "'";
        // SELECT pais, COUNT(pais) AS "Nº Naves" FROM naves WHERE pais = "USA"
        System.out.println(query);
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej5b(String pais) {
        String query = "SELECT pais, estado, COUNT(estado) AS \"Nº Naves\" FROM naves WHERE pais = '" + pais + "' GROUP BY estado";
        // SELECT pais, estado, COUNT(estado) AS "Nº Naves" FROM naves WHERE pais = "USA" GROUP BY estado;
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej5c() {
        String query = "SELECT pais, estado FROM naves WHERE estado IS NULL";
        // SELECT pais, estado FROM naves WHERE estado IS NULL
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej5d() {
        String query = "SELECT nombre, volumenUtil FROM naves ORDER BY volumenUtil DESC LIMIT 1 OFFSET 1";
        String query2 = "SELECT nombre, volumenUtil FROM naves ORDER BY volumenUtil DESC LIMIT 1 OFFSET 2";
        // SELECT nombre, MAX(volumenUtil) FROM naves LIMIT 1;
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            ResultSet resultSet2 = statement.executeQuery(query2);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getDouble(2));
            }
            while (resultSet2.next()) {
                System.out.println(resultSet2.getString(1) + "\t" + resultSet2.getDouble(2));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_1a(String nombre, double valor) {
        String query = "";

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

    public void ej6_1b(String nombre, String valor) {
        String query = "";

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

    public static void main(String[] args) {
        JDBC_ejers jdbc = new JDBC_ejers();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fallo al buscar el driver. No se ha encontrado. " + e.getLocalizedMessage());
        }

        jdbc.abreConexion("add", "localhost", "root", "");
        //jdbc.ej1("pais", "Japón");
        //jdbc.ej4("Nuegbrdtyue2332", "Nuevthrs3244", "Nuevohcnte234", "NuENTO23423");
        //jdbc.ej5a("USA");
        jdbc.ej5d();
        jdbc.cierraConexion();
    }
}
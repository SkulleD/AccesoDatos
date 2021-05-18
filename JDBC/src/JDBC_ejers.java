import java.io.Console;
import java.sql.*;

public class JDBC_ejers {
    Connection conexion;
    PreparedStatement ps = null;

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
        // select pais from naves where pais = "usa";
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
        String query = "SELECT pais, estado, COUNT(estado) AS \"Nº Naves\" FROM naves WHERE pais = '" + pais
                + "' GROUP BY estado";
        // SELECT pais, estado, COUNT(estado) AS "Nº Naves" FROM naves WHERE pais =
        // "USA" GROUP BY estado;
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

        String query = "select " + nombre + " from naves where " + nombre + " = ?";

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet;

            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setDouble(1, valor);
                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(nombre));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_1b(String nombre, String valor) {

        String query = "select " + nombre + " from naves where " + nombre + " = ?";

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet;

            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setString(1, valor);
                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(nombre));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_2(int valor) {
        String query = "delete from naves where id = ?";
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setInt(1, valor);
                filas = ps.executeUpdate();

                System.out.println("Filas eliminadas: " + filas);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_3(String nombre, String pais, String fabricante, int id) {
        String query = "update naves set nombre = ?, pais = ?, fabricante = ? where id = ?";
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setString(1, nombre);
                ps.setString(2, pais);
                ps.setString(3, fabricante);
                ps.setInt(4, id);
                filas = ps.executeUpdate();

                System.out.println("Filas actualizadas: " + filas);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_4(String nombre, String pais, String fabricante, String lanzamiento) {
        String query = "insert into naves (nombre, pais, fabricante, SistemaLanzamiento) values (?, ?, ?, ?)";
        int filas = 0;

        try (Statement statement = this.conexion.createStatement()) {
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setString(1, nombre);
                ps.setString(2, pais);
                ps.setString(3, fabricante);
                ps.setString(4, lanzamiento);
                filas = ps.executeUpdate();

                System.out.println("Filas actualizadas: " + filas);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_5a(String pais) {
        String query = "SELECT pais, COUNT(pais) AS \"Nº Naves\" FROM naves WHERE pais = ?";
        // SELECT pais, COUNT(pais) AS "Nº Naves" FROM naves WHERE pais = "USA"
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet;
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setString(1, pais);
                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_5b(String pais) {
        String query = "SELECT pais, estado, COUNT(estado) AS \"Nº Naves\" FROM naves WHERE pais = ? GROUP BY estado";
        // SELECT pais, estado, COUNT(estado) AS "Nº Naves" FROM naves WHERE pais =
        // "USA" GROUP BY estado;
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet;
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ps.setString(1, pais);

                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_5c() {
        String query = "SELECT pais, estado FROM naves WHERE estado IS NULL";
        // SELECT pais, estado FROM naves WHERE estado IS NULL
        try (Statement statement = this.conexion.createStatement()) {
            ResultSet resultSet;
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej6_5d() {
        String query = "SELECT nombre, volumenUtil FROM naves ORDER BY volumenUtil DESC LIMIT 1 OFFSET 1";
        String query2 = "SELECT nombre, volumenUtil FROM naves ORDER BY volumenUtil DESC LIMIT 1 OFFSET 2";
        // SELECT nombre, MAX(volumenUtil) FROM naves LIMIT 1;
        try (Statement statement = this.conexion.createStatement()) {
            if (this.ps == null) {
                this.ps = this.conexion.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery(query);
                ResultSet resultSet2 = statement.executeQuery(query2);
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t" + resultSet.getDouble(2));
                }
                while (resultSet2.next()) {
                    System.out.println(resultSet2.getString(1) + "\t" + resultSet2.getDouble(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej7() {
        String query = "CALL paises";

        try (CallableStatement cs = this.conexion.prepareCall(query)) {
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej8a() {
        ResultSet resultSet;
        DatabaseMetaData metadata;

        try {
            metadata = conexion.getMetaData();
            resultSet = metadata.getCatalogs();

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej8b(String database) {
        ResultSet resultSet;
        DatabaseMetaData metadata;

        try {
            metadata = conexion.getMetaData();
            resultSet = metadata.getTables(database, null, null, null);

            while (resultSet.next()) {
                System.out.println(
                        String.format("%s %s", resultSet.getString("TABLE_NAME"), resultSet.getString("TABLE_TYPE")));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej8c(String database, String tabla) {
        ResultSet tablas, columnas;
        DatabaseMetaData metadata;

        try {
            metadata = conexion.getMetaData();
            tablas = metadata.getTables(database, null, tabla, null);

            while (tablas.next()) {
                System.out.println(
                        String.format("%s %s", tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
                columnas = metadata.getColumns(database, null, tabla, null); // Se puede tabla o
                                                                             // tablas.getString("TABLE_NAME")

                while (columnas.next()) {
                    System.out.println(String.format("%s %s %s", columnas.getString("COLUMN_NAME"),
                            columnas.getString("TYPE_NAME"), columnas.getString("IS_NULLABLE")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej8d(String database, String tabla) {
        ResultSet tablas, columnas, clavesPrimarias;
        DatabaseMetaData metadata;

        try {
            metadata = conexion.getMetaData();
            tablas = metadata.getTables(database, null, tabla, null);

            while (tablas.next()) {
                System.out.println(
                        String.format("%s %s", tablas.getString("TABLE_NAME"), tablas.getString("TABLE_TYPE")));
                columnas = metadata.getColumns(database, null, tablas.getString("TABLE_NAME"), null); // Se puede tabla
                                                                                                      // o
                                                                                                      // tablas.getString("TABLE_NAME")
                clavesPrimarias = metadata.getPrimaryKeys(database, null, tabla);

                System.out.println("---");
                while (clavesPrimarias.next()) {
                    System.out.println(String.format("%s %s", clavesPrimarias.getString("TABLE_NAME"),
                            clavesPrimarias.getString("COLUMN_NAME")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej9a() {
        String query = "SELECT * FROM naves";
        // SELECT * FROM naves;

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet filas = statement.executeQuery(query);
            ResultSetMetaData resMetaData = filas.getMetaData();

            System.out.println("Nombre\t\t\tAlias");
            for (int i = 1; i <= resMetaData.getColumnCount(); i++) {
                System.out.println(
                        String.format("%s\t\t\t%s", resMetaData.getColumnName(i), resMetaData.getColumnLabel(i)));
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    public void ej9b() {
        String query = "SELECT * FROM naves";
        // SELECT * FROM naves;

        try (Statement statement = this.conexion.createStatement()) {
            ResultSet filas = statement.executeQuery(query);
            ResultSetMetaData resMetaData = filas.getMetaData();

            System.out.println("Tipos de Datos");
            for (int i = 1; i <= resMetaData.getColumnCount(); i++) {
                System.out.println(String.format("%d\t%s", i, resMetaData.getColumnTypeName(i)));
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
        // jdbc.ej1("pais", "Japón");
        // jdbc.ej3(8, "DAERFSHON", "Neuiv pais", "sespacidsdkf");
        // jdbc.ej4("Nuegbrdtyue2332", "Nuevthrs3244", "Nuevohcnte234",
        // "NrtthNTO23423");
        // jdbc.ej5b("USA");
        // jdbc.ej5c();
        // jdbc.ej6_1b("pais", "usa");
        // jdbc.ej6_3("FunciOnoooo", "Nh6tyh4whs", "st6yht6dkf", 6);
        // jdbc.ej6_4("Nuegbrdtyue2332", "Nuevthrs3244", "Nuevohcnte234",
        // "NuENTO23423");
        // jdbc.ej6_5a("USA");
        // jdbc.ej6_5b("USA");
        // jdbc.ej6_5d();
        // jdbc.ej7();
        // jdbc.ej8a();
        // jdbc.ej8b("add");
        // jdbc.ej8c("add", "naves");
        // jdbc.ej8d("add", "notas");
        // jdbc.ej9a();
        // jdbc.ej9b();
        jdbc.cierraConexion();
    }
}
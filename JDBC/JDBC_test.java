import java.sql.*;

public class JDBC_test {
    Connection conexion;
    Statement statement;

    public static void main(String[] args) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Fallo al buscar el driver. " + e.getLocalizedMessage());
        }
    }

    public void abreConexion(String bd, String server, String user, String password) {
        try {
            String url = String.format("jdbc:mariadb:///%s:3306/%s", server, bd);

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

    public void consulta(String bd) throws SQLException {
        String query = "select * from naves";
        abreConexion("add", "localhost", "root", "");
        statement = this.conexion.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.println(
                    resultSet.getInt(1) + "\t" + resultSet.getString("nombre") + "\t" + resultSet.getString("pais") + "\t" + resultSet.getDouble("longitud"));
        }
        statement.close();
        cierraConexion();
    }

    public void insertaFila() {
        try {
            Statement state = this.conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error al intentar insertar fila: " + e.getLocalizedMessage());
        }
    }
}
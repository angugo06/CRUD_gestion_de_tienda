import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class ConexionBD {

    private static Connection conexion;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try (InputStream input = ConexionBD.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties props = new Properties();
                props.load(input);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexión exitosa.");
            } catch (Exception e) {
                System.out.println("Error al conectar: " + e.getMessage());
            }
        }
        return conexion;
    }
}

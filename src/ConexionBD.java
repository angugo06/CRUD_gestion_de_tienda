import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class ConexionBD {

    public static Connection obtenerConexion() {
        try (InputStream input = ConexionBD.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}

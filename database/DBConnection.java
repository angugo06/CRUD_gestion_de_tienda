import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.FileInputStream;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Could not load config.properties");
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}

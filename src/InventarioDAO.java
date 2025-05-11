import java.sql.*;

public class InventarioDAO {

    public void agregarArticulo(String nombre, String descripcion, double precio, int cantidad) {
        String insertArticulo = "INSERT INTO articulos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        String insertLog = "INSERT INTO registro_sesiones (id_accion, id_articulo, observaciones) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion()) {
            conn.setAutoCommit(false); // iniciar transacción

            // Insertar artículo
            PreparedStatement psArticulo = conn.prepareStatement(insertArticulo, Statement.RETURN_GENERATED_KEYS);
            psArticulo.setString(1, nombre);
            psArticulo.setString(2, descripcion);
            psArticulo.setDouble(3, precio);
            psArticulo.setInt(4, cantidad);
            psArticulo.executeUpdate();

            ResultSet rs = psArticulo.getGeneratedKeys();
            int idArticulo = -1;
            if (rs.next()) {
                idArticulo = rs.getInt(1);
            }

            // Insertar registro de sesión (CREAR = id_accion 1)
            PreparedStatement psLog = conn.prepareStatement(insertLog);
            psLog.setInt(1, 1); // CREAR
            psLog.setInt(2, idArticulo);
            psLog.setString(3, "Artículo agregado desde la app");
            psLog.executeUpdate();

            conn.commit();
            System.out.println("Artículo y log insertados correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void listarArticulos() {
    String sql = "SELECT * FROM articulos";

    try (Connection conn = ConexionBD.obtenerConexion();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("id_articulo");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            double precio = rs.getDouble("precio");
            int cantidad = rs.getInt("cantidad");

            System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio + ", Cantidad: " + cantidad);
        }

    } catch (Exception e) {
        System.out.println("Error al leer artículos: " + e.getMessage());
    }
}
}


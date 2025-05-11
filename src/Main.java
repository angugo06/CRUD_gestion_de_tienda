public class Main {
    public static void main(String[] args) {
        InventarioDAO dao = new InventarioDAO();
        dao.agregarArticulo("Papel", "Paquete de 500 hojas", 89.99, 10);
    }
}

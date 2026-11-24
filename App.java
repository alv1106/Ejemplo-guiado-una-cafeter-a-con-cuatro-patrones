import java.util.List;

// App.java (paso 5: version final con los cuatro patrones)
public class App {

    public static void main(String[] args) {

        // --- ENSAMBLAJE: aqui se deciden las piezas concretas que usara el programa ---
        Cafeteria cafeteria = new Cafeteria(new DescuentoEstudiante());   // Strategy
        TotalVentas ventas = new TotalVentas();
        cafeteria.suscribir(new RegistroPedidos());                       // Observer 1
        cafeteria.suscribir(ventas);                                      // Observer 2

        // --- USO ---
        System.out.println("=== Pedidos ===");
        cafeteria.confirmar("cafe", List.of("leche", "shot"));
        cafeteria.confirmar("te", List.of());
        cafeteria.confirmar("cafe", List.of());

        System.out.println();
        System.out.println("=== Errores (no generan pedido ni aviso) ===");
        intentar(cafeteria, "pizza", List.of());
        intentar(cafeteria, "cafe", List.of("chocolate"));

        System.out.println();
        System.out.println("=== Ventas acumuladas ===");
        System.out.println(ventas.getPedidos() + " pedidos, total $" + ventas.getTotal());
    }

    private static void intentar(Cafeteria cafeteria, String tipo, List<String> extras) {
        try {
            cafeteria.confirmar(tipo, extras);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
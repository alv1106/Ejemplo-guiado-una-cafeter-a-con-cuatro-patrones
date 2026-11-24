// Ingenuo.java
// PRIMER INTENTO, sin patrones: todo vive en un solo metodo.
// Funciona... pero mira cuantas responsabilidades mezcla.
// Ejecutar con:  java Ingenuo.java
public class Ingenuo {

    // Estadisticas "sueltas" (estado global mezclado con la logica)
    static int pedidos = 0;
    static int totalVentas = 0;

    // Calcula el total de un pedido. Recibe un booleano por cada extra y el tipo de cliente.
    static int calcular(String tipo, boolean leche, boolean shot, String cliente) {

        // 1) Elegir la bebida: un if por cada bebida del menu
        int precio;
        String descripcion;
        if (tipo.equals("cafe")) {
            precio = 5000;
            descripcion = "Cafe";
        } else if (tipo.equals("te")) {
            precio = 4000;
            descripcion = "Te";
        } else {
            throw new IllegalArgumentException("Bebida no existe: " + tipo);
        }

        // 2) Agregar extras: un if por cada extra
        if (leche) {
            precio += 1000;
            descripcion += " con leche";
        }
        if (shot) {
            precio += 2000;
            descripcion += " con shot extra";
        }

        // 3) Aplicar el descuento: un if por cada tipo de cliente
        int subtotal = precio;
        if (cliente.equals("estudiante")) {
            precio = precio * 90 / 100;
        }

        // 4) Registrar el pedido y las ventas (mezclado con todo lo anterior)
        pedidos++;
        totalVentas += precio;
        System.out.println("[Registro] Pedido #" + pedidos + ": " + descripcion
                + " | subtotal $" + subtotal + " | total $" + precio);

        return precio;
    }

    public static void main(String[] args) {
        calcular("cafe", true, true, "estudiante");
        calcular("te", false, false, "general");
        System.out.println("Ventas: " + pedidos + " pedidos, $" + totalVentas);
    }
}
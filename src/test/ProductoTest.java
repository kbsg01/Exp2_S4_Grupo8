package test;

import model.Producto;

public class ProductoTest {

    private static int exitosas = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {
        System.out.println("Iniciando pruebas unitarias de Producto");

        testCrearProducto();
        testSettersPrecioYStock();

        System.out.println("\nResultados Producto:");
        System.out.println("Éxitos: " + exitosas);
        System.out.println("Fallos: " + fallidas);
    }

    private static void testCrearProducto() {
        System.out.println("\n>> testCrearProducto");
        Producto p = new Producto("C1", "Nombre", "Descripción", 99.99, 7);
        verificar("Código correcto", "C1".equals(p.getCodigo()));
        verificar("Nombre correcto", "Nombre".equals(p.getNombre()));
        verificar("Descripción correcta", "Descripción".equals(p.getDescripcion()));
        verificar("Precio correcto", Math.abs(p.getPrecio() - 99.99) < 1e-6);
        verificar("Stock correcto", p.getStock() == 7);
    }

    private static void testSettersPrecioYStock() {
        System.out.println("\n>> testSettersPrecioYStock");
        Producto p = new Producto("C2", "Prod", "Desc", 10.0, 5);
        p.setPrecio(15.5);
        verificar("Precio actualizado correctamente", Math.abs(p.getPrecio() - 15.5) < 1e-6);
        p.setStock(12);
        verificar("Stock actualizado correctamente", p.getStock() == 12);
    }

    private static void verificar(String mensaje, boolean condicion) {
        if (condicion) {
            System.out.println("✅ " + mensaje);
            exitosas++;
        } else {
            System.out.println("❌ " + mensaje);
            fallidas++;
        }
    }
}

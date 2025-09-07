package test;

import model.Inventario;
import model.Producto;

import java.util.List;
import java.util.stream.Collectors;

public class InventarioTest {
    private static int exitosas = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {
        System.out.println("Iniciando pruebas unitarias de Inventario");

        testAgregarProducto();
        testAgregarProductoNulo();
        testEliminarProducto();
        testEliminarProductoInexistente();
        testBuscarPorNombreCoincidencias();
        testBuscarPorNombreNoExiste();
        testListarTodosProductos();

        System.out.println("\nResultados Inventario:");
        System.out.println("Éxitos: " + exitosas);
        System.out.println("Fallos: " + fallidas);
    }

    private static void testAgregarProducto() {
        System.out.println("\n>> testAgregarProducto");
        Inventario inv = new Inventario();
        Producto p = new Producto("P1", "Laptop", "Portátil", 1200.0, 10);
        inv.addProducto(p);
        verificar("Producto agregado y encontrado por código", inv.searchByCode("P1") != null);
        verificar("Total productos == 1", inv.totalProductos() == 1);
    }

    private static void testAgregarProductoNulo() {
        System.out.println("\n>> testAgregarProductoNulo");
        Inventario inv = new Inventario();
        int antes = inv.totalProductos();
        boolean lanzada = false;
        try {
            inv.addProducto(null);
        } catch (Exception e) {
            lanzada = true;
        }
        if (lanzada) {
            verificar("Agregar null lanza excepción (comportamiento aceptable)", true);
        } else {
            // Si no lanza, verificar que inventario no cambió
            verificar("Agregar null no altera inventario", inv.totalProductos() == antes);
        }
    }

    private static void testEliminarProducto() {
        System.out.println("\n>> testEliminarProducto");
        Inventario inv = new Inventario();
        Producto p = new Producto("P2", "Mouse", "Inalámbrico", 25.0, 50);
        inv.addProducto(p);
        verificar("Producto existe antes de eliminar", inv.searchByCode("P2") != null);
        inv.deleteProducto("P2");
        verificar("Producto ya no existe después de eliminar", inv.searchByCode("P2") == null);
        verificar("Total productos == 0", inv.totalProductos() == 0);
    }

    private static void testEliminarProductoInexistente() {
        System.out.println("\n>> testEliminarProductoInexistente");
        Inventario inv = new Inventario();
        Producto p = new Producto("P3", "Teclado", "Mecánico", 80.0, 30);
        inv.addProducto(p);
        inv.deleteProducto("NO_EXISTE");
        verificar("Producto original sigue existiendo tras intento de eliminar inexistente", inv.searchByCode("P3") != null);
        verificar("Total productos sigue siendo 1", inv.totalProductos() == 1);
    }

    private static void testBuscarPorNombreCoincidencias() {
        System.out.println("\n>> testBuscarPorNombreCoincidencias");
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("A1", "Laptop HP", "Oficina", 1000.0, 5));
        inv.addProducto(new Producto("A2", "Laptop Dell", "Gaming", 1500.0, 3));
        inv.addProducto(new Producto("B1", "Monitor", "Gaming 24\"", 200.0, 8));

        List<Producto> resultados = null;
        try {
            resultados = inv.searchByName("Laptop");
        } catch (Exception e) {
            // ignorar, se comprobará abajo
        }
        verificar("Búsqueda por nombre no devuelve null", resultados != null);
        if (resultados != null) {
            verificar("Se encontraron 2 coincidencias", resultados.size() == 2);
            List<String> codigos = resultados.stream().map(Producto::getCodigo).collect(Collectors.toList());
            verificar("Contiene A1", codigos.contains("A1"));
            verificar("Contiene A2", codigos.contains("A2"));
        }
    }

    private static void testBuscarPorNombreNoExiste() {
        System.out.println("\n>> testBuscarPorNombreNoExiste");
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("X1", "Producto", "Desc", 10.0, 1));
        List<Producto> resultados = null;
        try {
            resultados = inv.searchByName("NoExiste");
        } catch (Exception e) {
            // si lanza excepción, considerar comportamiento aceptable
            verificar("Buscar nombre inexistente lanzó excepción (aceptable)", true);
            return;
        }
        // Si no lanzó, aceptar tanto lista vacía como null según implementación
        if (resultados == null) {
            verificar("Buscar nombre inexistente devuelve null (aceptable)", true);
        } else {
            verificar("Buscar nombre inexistente devuelve lista vacía", resultados.isEmpty());
        }
    }

    private static void testListarTodosProductos() {
        System.out.println("\n>> testListarTodosProductos");
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("L1", "P1", "D1", 1.0, 1));
        inv.addProducto(new Producto("L2", "P2", "D2", 2.0, 2));
        inv.addProducto(new Producto("L3", "P3", "D3", 3.0, 3));
        verificar("Total productos == 3", inv.totalProductos() == 3);
        verificar("Buscar L1 devuelve producto", inv.searchByCode("L1") != null);
        verificar("Buscar L2 devuelve producto", inv.searchByCode("L2") != null);
        verificar("Buscar L3 devuelve producto", inv.searchByCode("L3") != null);
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

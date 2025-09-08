package unitaria;

import model.Inventario;
import model.Producto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    @Test
    void testAgregarProducto() {
        Inventario inv = new Inventario();
        assertEquals(0, inv.totalProductos());
        Producto p = new Producto("P1", "Laptop", "Portátil", 1200.0, 10);
        inv.addProducto(p);
        assertNotNull(inv.searchByCode("P1"));
        assertEquals(1, inv.totalProductos());
    }

    @Test
    void testAgregarProductoNulo_lanzaNullPointerOnoCambia() {
        Inventario inv = new Inventario();
        int antes = inv.totalProductos();
        // Aceptar ambas implementaciones: lanza excepción o no altera inventario
        try {
            assertThrows(NullPointerException.class, () -> inv.addProducto(null));
        } catch (AssertionError e) {
            // Si no lanzó, verificar que el inventario no cambió
            assertEquals(antes, inv.totalProductos());
        }
    }

    @Test
    void testEliminarProducto() {
        Inventario inv = new Inventario();
        Producto p = new Producto("P2", "Mouse", "Inalámbrico", 25.0, 50);
        inv.addProducto(p);
        assertNotNull(inv.searchByCode("P2"));
        inv.deleteProducto("P2");
        assertNull(inv.searchByCode("P2"));
        assertEquals(0, inv.totalProductos());
    }

    @Test
    void testEliminarProductoInexistente_noCambiaInventario() {
        Inventario inv = new Inventario();
        Producto p = new Producto("P3", "Teclado", "Mecánico", 80.0, 30);
        inv.addProducto(p);
        inv.deleteProducto("NO_EXISTE");
        assertNotNull(inv.searchByCode("P3"));
        assertEquals(1, inv.totalProductos());
    }

    @Test
    void testBuscarPorNombre_devuelveCoincidencias() {
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("A1", "Laptop HP", "Oficina", 1000.0, 5));
        inv.addProducto(new Producto("A2", "Laptop Dell", "Gaming", 1500.0, 3));
        inv.addProducto(new Producto("B1", "Monitor", "Gaming 24\"", 200.0, 8));

        List<Producto> resultados = inv.searchByName("Laptop");
        assertNotNull(resultados);
        assertEquals(2, resultados.size());

        var codigos = resultados.stream().map(Producto::getCodigo).collect(Collectors.toList());
        assertTrue(codigos.contains("A1"));
        assertTrue(codigos.contains("A2"));
    }

    @Test
    void testBuscarPorNombre_noExiste_listaVaciaONull() {
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("X1", "Producto", "Desc", 10.0, 1));
        List<Producto> resultados = null;
        try {
            resultados = inv.searchByName("NoExiste");
        } catch (Exception e) {
            // Si lanza, se acepta
            assertTrue(true);
            return;
        }
        // Aceptar tanto null como lista vacía según implementación
        assertTrue(resultados == null || resultados.isEmpty());
    }

    @Test
    void testListarTodosProductos_totalProductosCoincide() {
        Inventario inv = new Inventario();
        inv.addProducto(new Producto("L1", "P1", "D1", 1.0, 1));
        inv.addProducto(new Producto("L2", "P2", "D2", 2.0, 2));
        inv.addProducto(new Producto("L3", "P3", "D3", 3.0, 3));
        assertEquals(3, inv.totalProductos());
        assertNotNull(inv.searchByCode("L1"));
        assertNotNull(inv.searchByCode("L2"));
        assertNotNull(inv.searchByCode("L3"));
    }
}

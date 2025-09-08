package integracion;

import model.Inventario;
import model.Producto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoInventarioTest {

    @Test
    void testAgregarYBuscarPorCodigo() {
        Inventario inventario = new Inventario();
        Producto p = new Producto("P001", "Laptop", "Laptop gaming", 1200.0, 10);
        inventario.addProducto(p);
        Producto encontrado = inventario.searchByCode("P001");
        assertNotNull(encontrado);
        assertEquals("Laptop", encontrado.getNombre());
        assertEquals(10, encontrado.getStock());
    }

    @Test
    void testBuscarPorNombreYDescripcion() {
        Inventario inventario = new Inventario();
        inventario.addProducto(new Producto("P001", "Laptop HP", "Portátil para oficina", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Laptop Dell", "Portátil para gaming", 1500.0, 8));
        inventario.addProducto(new Producto("P003", "Monitor", "Monitor 24 pulgadas gaming", 200.0, 15));
        List<Producto> porNombre = inventario.searchByName("Laptop");
        assertNotNull(porNombre);
        assertEquals(2, porNombre.size());

        List<Producto> porDescripcion = inventario.searchByDescription("gaming");
        assertNotNull(porDescripcion);
        assertTrue(porDescripcion.size() >= 2);
    }

    @Test
    void testActualizarUsandoSetters() {
        Inventario inventario = new Inventario();
        Producto original = new Producto("P010", "Tablet", "Tablet básica", 300.0, 20);
        inventario.addProducto(original);

        Producto encontrado = inventario.searchByCode("P010");
        assertNotNull(encontrado);
        encontrado.setNombre("Tablet Pro");
        encontrado.setDescripcion("Tablet avanzada");
        encontrado.setPrecio(450.0);
        encontrado.setStock(15);

        Producto actualizado = inventario.searchByCode("P010");
        assertEquals("Tablet Pro", actualizado.getNombre());
        assertEquals("Tablet avanzada", actualizado.getDescripcion());
        assertEquals(450.0, actualizado.getPrecio(), 1e-6);
        assertEquals(15, actualizado.getStock());
    }

    @Test
    void testEliminarYCoherencia() {
        Inventario inventario = new Inventario();
        inventario.addProducto(new Producto("P001", "Laptop", "Portátil", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Mouse", "Inalámbrico", 25.0, 50));
        inventario.addProducto(new Producto("P003", "Teclado", "Mecánico", 80.0, 30));

        inventario.deleteProducto("P002");
        assertNull(inventario.searchByCode("P002"));

        Producto p1 = inventario.searchByCode("P001");
        assertNotNull(p1);
        assertEquals("Laptop", p1.getNombre());
        assertEquals(10, p1.getStock());

        Producto p3 = inventario.searchByCode("P003");
        assertNotNull(p3);
        assertEquals(30, p3.getStock());
    }
}
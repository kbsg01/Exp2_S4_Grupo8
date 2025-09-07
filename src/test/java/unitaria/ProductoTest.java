package unitaria;

import model.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void testCrearProducto() {
        Producto p = new Producto("C1", "Nombre", "Descripción", 99.99, 7);
        assertEquals("C1", p.getCodigo());
        assertEquals("Nombre", p.getNombre());
        assertEquals("Descripción", p.getDescripcion());
        assertEquals(99.99, p.getPrecio(), 1e-6);
        assertEquals(7, p.getStock());
    }

    @Test
    void testSettersPrecioYStock() {
        Producto p = new Producto("C2", "Prod", "Desc", 10.0, 5);
        p.setPrecio(15.5);
        assertEquals(15.5, p.getPrecio(), 1e-6);
        p.setStock(12);
        assertEquals(12, p.getStock());
    }
}

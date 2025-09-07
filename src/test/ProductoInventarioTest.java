package test;

import model.Inventario;
import model.Producto;
import java.util.List;

/**
 * Clase de pruebas de integración entre Producto e Inventario sin usar frameworks de testing
 */
public class ProductoInventarioTest {

    private static Inventario inventario;
    private static int pruebasExitosas = 0;
    private static int pruebasFallidas = 0;

    public static void main(String[] args) {
        System.out.println("Iniciando pruebas de integración Producto-Inventario");

        // Ejecutar todas las pruebas
        probarAgregarProducto();
        probarBuscarPorCodigo();
        probarBuscarPorNombreYDescripcion();
        probarActualizarProducto();
        probarEliminarProducto();
        probarCoherenciaInventario();

        // Mostrar resultados finales
        System.out.println("\n===== RESULTADOS DE LAS PRUEBAS =====");
        System.out.println("Pruebas exitosas: " + pruebasExitosas);
        System.out.println("Pruebas fallidas: " + pruebasFallidas);
    }

    private static void probarAgregarProducto() {
        System.out.println("\n>> Prueba: Agregar producto");
        inventario = new Inventario(); // Inicializar inventario

        // Crear y agregar un producto
        Producto producto = new Producto("P001", "Laptop", "Laptop gaming", 1200.0, 10);
        inventario.addProducto(producto);

        // Verificar que se agregó correctamente
        Producto encontrado = inventario.searchByCode("P001");
        if (encontrado != null) {
            verificar("El producto fue encontrado", true);
            verificar("Nombre correcto", "Laptop".equals(encontrado.getNombre()));
            verificar("Stock correcto", 10 == encontrado.getStock());
        } else {
            verificar("El producto debería estar en el inventario", false);
        }
    }

    private static void probarBuscarPorCodigo() {
        System.out.println("\n>> Prueba: Buscar producto por código");
        inventario = new Inventario();

        // Agregar productos
        inventario.addProducto(new Producto("P001", "Laptop", "Portátil", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Mouse", "Inalámbrico", 25.0, 50));

        // Buscar producto existente
        Producto encontrado = inventario.searchByCode("P002");
        verificar("Producto existente encontrado", encontrado != null);
        if (encontrado != null) {
            verificar("Nombre correcto del producto encontrado", "Mouse".equals(encontrado.getNombre()));
        }

        // Buscar producto inexistente
        Producto noEncontrado = inventario.searchByCode("P999");
        verificar("Producto inexistente no encontrado", noEncontrado == null);
    }

    private static void probarBuscarPorNombreYDescripcion() {
        System.out.println("\n>> Prueba: Buscar producto por nombre y descripción");
        inventario = new Inventario();

        // Preparar productos con nombres y descripciones similares
        inventario.addProducto(new Producto("P001", "Laptop HP", "Portátil para oficina", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Laptop Dell", "Portátil para gaming", 1500.0, 8));
        inventario.addProducto(new Producto("P003", "Monitor", "Monitor 24 pulgadas gaming", 200.0, 15));
        inventario.addProducto(new Producto("P004", "Teclado", "Mecánico", 80.0, 30));

        // Buscar por nombre (se espera que el método retorne List<Producto>)
        try {
            List<Producto> porNombre = inventario.searchByName("Laptop");
            verificar("Búsqueda por nombre devuelve resultados", porNombre != null);
            if (porNombre != null) {
                verificar("Se encuentran 2 laptops", porNombre.size() == 2);
            }
        } catch (Exception e) {
            verificar("Búsqueda por nombre lanza excepción", false);
        }

        // Buscar por descripción
        try {
            List<Producto> porDescripcion = inventario.searchByDescription("gaming");
            verificar("Búsqueda por descripción devuelve resultados", porDescripcion != null);
            if (porDescripcion != null) {
                // Esperamos al menos 2 resultados que contengan 'gaming' en la descripción
                verificar("Se encuentran al menos 2 productos con 'gaming' en la descripción", porDescripcion.size() >= 2);
            }
        } catch (Exception e) {
            verificar("Búsqueda por descripción lanza excepción", false);
        }
    }

    private static void probarActualizarProducto() {
        System.out.println("\n>> Prueba: Actualizar producto (usando setters)");
        inventario = new Inventario();

        // Agregar producto original
        Producto original = new Producto("P010", "Tablet", "Tablet básica", 300.0, 20);
        inventario.addProducto(original);

        // Obtener referencia al producto almacenado y actualizar mediante setters
        Producto encontrado = inventario.searchByCode("P010");
        if (encontrado != null) {
            // Actualizar campos usando setters (no se requiere updateProducto en Inventario)
            encontrado.setNombre("Tablet Pro");
            encontrado.setDescripcion("Tablet avanzada");
            encontrado.setPrecio(450.0);
            encontrado.setStock(15);

            // Verificaciones
            verificar("Producto existe después de actualizar", true);
            verificar("Nombre actualizado", "Tablet Pro".equals(encontrado.getNombre()));
            verificar("Descripción actualizada", "Tablet avanzada".equals(encontrado.getDescripcion()));
            verificar("Precio actualizado", 450.0 == encontrado.getPrecio());
            verificar("Stock actualizado", 15 == encontrado.getStock());
        } else {
            verificar("Producto debería existir antes de actualizar", false);
        }
    }

    private static void probarEliminarProducto() {
        System.out.println("\n>> Prueba: Eliminar producto");
        inventario = new Inventario();

        // Agregar productos
        inventario.addProducto(new Producto("P001", "Laptop", "Portátil", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Mouse", "Inalámbrico", 25.0, 50));

        // Verificar que existe antes de eliminar
        verificar("Producto existe antes de eliminar", inventario.searchByCode("P001") != null);

        // Eliminar el producto
        inventario.deleteProducto("P001");

        // Verificar que fue eliminado
        verificar("Producto fue eliminado", inventario.searchByCode("P001") == null);
        verificar("Otro producto sigue existiendo", inventario.searchByCode("P002") != null);
    }

    private static void probarCoherenciaInventario() {
        System.out.println("\n>> Prueba: Coherencia del inventario tras múltiples operaciones");
        inventario = new Inventario();

        // Agregar productos
        inventario.addProducto(new Producto("P001", "Laptop", "Portátil", 1200.0, 10));
        inventario.addProducto(new Producto("P002", "Mouse", "Inalámbrico", 25.0, 50));
        inventario.addProducto(new Producto("P003", "Teclado", "Mecánico", 80.0, 30));

        // Realizar operaciones (solo eliminación; la actualización no es requisito)
        inventario.deleteProducto("P002");

        // Verificar estado final
        verificar("P002 eliminado correctamente", inventario.searchByCode("P002") == null);

        Producto p1 = inventario.searchByCode("P001");
        if (p1 != null) {
            verificar("Nombre de P001 no modificado", "Laptop".equals(p1.getNombre()));
            verificar("Stock de P001 no modificado", 10 == p1.getStock());
        } else {
            verificar("P001 debería existir", false);
        }

        Producto p3 = inventario.searchByCode("P003");
        if (p3 != null) {
            verificar("Stock de P003 no modificado", 30 == p3.getStock());
        } else {
            verificar("P003 debería existir", false);
        }
    }

    private static void verificar(String mensaje, boolean condicion) {
        if (condicion) {
            System.out.println("✅ ÉXITO: " + mensaje);
            pruebasExitosas++;
        } else {
            System.out.println("❌ ERROR: " + mensaje);
            pruebasFallidas++;
        }
    }
}
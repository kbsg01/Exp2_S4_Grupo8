package demo;

import controller.MenuPrincipalController;
import model.Inventario;
import model.Producto;
import view.InventarioView;
import view.MenuPrincipalView;
import view.ProductoView;

/**
 * Punto de entrada de la aplicación de gestión de inventario.
 */
public class GestionInventario {
    public static void main(String[] args) {
    MenuPrincipalView menuView = new MenuPrincipalView();
    ProductoView productoView = new ProductoView();
    Inventario inventario = new Inventario();
    // Productos iniciales añadidos al inicio de la aplicación
    inventario.addProducto(new Producto("154", "Polera manga corta", "Polera manga corta de algodón", 10000.0, 30));
    inventario.addProducto(new Producto("123", "Patalon", "Pantalón de jeans", 20000.0, 10));
    inventario.addProducto(new Producto("134", "Polerón", "Polerón canguro", 14000.0, 4));
    inventario.addProducto(new Producto("245", "Chaqueta", "Chaqueta de invierno", 30000.0, 2));
    InventarioView inventarioView = new InventarioView(inventario);
    MenuPrincipalController controller = new MenuPrincipalController(menuView, productoView, inventarioView);
        controller.run();
    }
}
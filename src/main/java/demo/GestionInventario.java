package demo;

import controller.MenuPrincipalController;
import model.Inventario;
import model.Producto;
import view.InventarioView;
import view.MenuPrincipalView;
import view.ProductoView;

public class GestionInventario {

    public static void main(String[] args) {
        MenuPrincipalView menuView = new MenuPrincipalView();
        ProductoView productoView = new ProductoView();
        Inventario inventario = new Inventario();
        
        //Stack de datos de prueba
        inventario.addProducto(new Producto("P001", "Laptop HP", "Portátil para oficina", 429990.0, 10));
        inventario.addProducto(new Producto("P002", "Laptop Dell", "Portátil para gaming", 1200000.0, 8));
        inventario.addProducto(new Producto("P003", "Audifonos Razer", "Audifonos para gaming", 55000, 4));
        inventario.addProducto(new Producto("P004", "Audifonos Sony", "Audifonos con cancelación de ruido", 155000, 2));
        inventario.addProducto(new Producto("P005", "Teclado Redragon", "Teclado mecanico", 35000, 6));
        inventario.addProducto(new Producto("P006", "Teclado HP", "Teclado de oficina", 25000, 10));
        
        InventarioView inventarioView = new InventarioView(inventario);
        MenuPrincipalController controller = new MenuPrincipalController(menuView, productoView, inventarioView);
        controller.run();
    }
}

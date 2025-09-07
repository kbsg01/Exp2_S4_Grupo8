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
        InventarioView inventarioView = new InventarioView(inventario);
        MenuPrincipalController controller = new MenuPrincipalController(menuView, productoView, inventarioView);
        controller.run();
    }
}

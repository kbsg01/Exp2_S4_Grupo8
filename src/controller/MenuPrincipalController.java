package controller;

import model.Inventario;
import model.Producto;
import view.IView;

public class MenuPrincipalController implements IController {
    private final Inventario inventario;
    private final IView view;

    public MenuPrincipalController(Inventario inventario, IView view) {
        this.inventario = inventario;
        this.view = view;
    }

    @Override
    public void run() {
        int opcion;
        do {
            view.displayMenu();
            opcion = view.getUserChoice();
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> eliminarProducto();
                case 3 -> buscarProducto();
                case 4 -> listarTodosLosProductos();
                case 5 -> inventario.getInventoryReport();
                case 0 -> view.displayMessage("Saliendo...");
                default -> view.displayMessage("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        String codigo = view.getInput("Código: ");
        String nombre = view.getInput("Nombre: ");
        String descripcion = view.getInput("Descripción: ");
        double precio;
        while (true) {
            try {
                precio = Double.parseDouble(view.getInput("Precio: "));
                break;
            } catch (NumberFormatException e) {
                view.displayMessage("Precio inválido. Intente nuevamente.");
            }
        }
        int stock;
        while (true) {
            try {
                stock = Integer.parseInt(view.getInput("Stock: "));
                break;
            } catch (NumberFormatException e) {
                view.displayMessage("Stock inválido. Intente nuevamente.");
            }
        }
        Producto producto = new Producto(codigo, nombre, descripcion, precio, stock);
        inventario.addProducto(producto);
        view.displayMessage("Producto agregado correctamente.");
    }

    private void eliminarProducto() {
        String codigo = view.getInput("Código del producto a eliminar: ");
        if (inventario.searchByCode(codigo) != null) {
            inventario.deleteProducto(codigo);
            view.displayMessage("Producto eliminado.");
        } else {
            view.displayMessage("No se encontró un producto con ese código.");
        }
    }

    private void buscarProducto() {
        String codigo = view.getInput("Código del producto: ");
        Producto p = inventario.searchByCode(codigo);
        if (p != null) {
            p.fullDescription();
        } else {
            view.displayMessage("No se encontró un producto con ese código.");
        }
    }

    private void listarTodosLosProductos() {
        System.out.println("Listando todos los productos.");
        System.out.println("| Código | Stock | Nombre | Precio | Descripción |");
        inventario.listAllProductos();
    }
}
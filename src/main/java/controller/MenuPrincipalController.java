package controller;
import view.InventarioView;
import view.MenuPrincipalView;
import view.ProductoView;

public class MenuPrincipalController {
    private final MenuPrincipalView menuView;
    private final ProductoView productoView;
    private final InventarioView inventarioView;

    public MenuPrincipalController( MenuPrincipalView menuView, ProductoView productoView, InventarioView inventarioView) {
        this.menuView = menuView;
        this.productoView = productoView;
        this.inventarioView = inventarioView;
    }

    public void run() {
        int opcion;
        do {
            menuView.displayMenu();
            opcion = menuView.getUserChoice();
            switch (opcion) {
                case 1 -> mostrarMenuModificacion();
                case 2 -> mostrarMenuBusquedaYListado();
                case 3 -> inventarioView.generarReporteInventario();
                case 0 -> menuView.displayMessage("Saliendo...");
                default -> menuView.displayMessage("Opción no válida.");
            }
        } while (opcion != 0);
    }

    // Métodos para manejar cada opción del menú
    private void mostrarMenuModificacion() {
        int opcion;
        do {
            productoView.displayProductoMenu();
            opcion = productoView.getUserChoice();
            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> eliminarProducto();
                case 3 -> actualizarProducto();
                case 0 -> menuView.displayMessage("Volviendo al menú principal...");
                default -> menuView.displayMessage("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuBusquedaYListado() {
        int opcion;
        do {
            inventarioView.displayInventarioMenu();
            opcion = inventarioView.getUserChoice();
            switch (opcion) {
                case 1 -> inventarioView.listarProductos();
                case 2 -> inventarioView.generarReporteInventario();
                case 3 -> inventarioView.buscarProductoPorCodigo();
                case 4 -> inventarioView.buscarProductoPorNombre();
                case 5 -> inventarioView.buscarProductoPorDescripcion();
                case 0 -> menuView.displayMessage("Volviendo al menú principal...");
                default -> menuView.displayMessage("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        try {
            model.Producto nuevo = productoView.crearProducto();
            if (nuevo == null) {
                menuView.displayMessage("No se creó el producto.");
                return;
            }
            // verificar existencia por código
            if (inventarioView.getInventario().searchByCode(nuevo.getCodigo()) != null) {
                menuView.displayMessage("Ya existe un producto con ese código.");
                return;
            }
            inventarioView.getInventario().addProducto(nuevo);
            menuView.displayMessage("Producto agregado correctamente.");
        } catch (Exception e) {
            menuView.displayMessage("Error al agregar producto: " + e.getMessage());
        }
    }

    private void actualizarProducto() {
        System.out.println("* Al modificar un producto, si se deja algun campo vacio se mantiene el valor actual");
        String codigo = productoView.obtenerCampoObligatorio("Código del producto a actualizar");
        model.Producto existente = inventarioView.getInventario().searchByCode(codigo);
        if (existente == null) {
            menuView.displayMessage("No existe un producto con ese código.");
            return;
        }
        productoView.actualizarProducto(existente);
        menuView.displayMessage("Producto actualizado correctamente.");
    }

    private void eliminarProducto() {
        String codigo = productoView.obtenerCampoObligatorio("Código del producto a eliminar");
        model.Producto existente = inventarioView.getInventario().searchByCode(codigo);
        if (existente == null) {
            menuView.displayMessage("No existe un producto con ese código.");
            return;
        }
        inventarioView.getInventario().deleteProducto(codigo);
        menuView.displayMessage("Producto eliminado correctamente.");
    }

}
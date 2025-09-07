package model;

import java.util.HashMap;

public class Inventario {
    private HashMap<String, Producto> productos;

    // Constructor
    public Inventario() {
        this.productos = new HashMap<>();
    }

    public void addProducto(Producto producto) {
        productos.put(producto.getCodigo(), producto);
    }

    /**
     * * Elimina un producto del inventario basado en su código.
     * * @param codigo El código del producto a eliminar.
     * */
    public void deleteProducto(String codigo) {
        productos.remove(codigo);
    }

    /**
     * * Busca un producto en el inventario basado en su código.
     * * @param codigo El código del producto a buscar.
     * * @return El producto encontrado o null si no existe.
     * **/
    public Producto searchByCode(String codigo) {
        return productos.get(codigo);
    }

    /**
     * * Busca productos en el inventario basado en su nombre (case insensitive).
     * * @param nombre El nombre del producto a buscar.
     * **/
    public void searchByName(String nombre) {
        for (Producto p : productos.values()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.fullDescription(p);
            } else {
                System.out.println("No se encontró el(los) producto(s) con el nombre: " + nombre);
            }
        }
    }

    /**
     * * Busca productos en el inventario basado en su descripción (case insensitive).
     * * @param descripcion La descripción del producto a buscar.
     * **/
    public void searchByDescription(String descripcion) {
        for (Producto p : productos.values()) {
            if (p.getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
                p.fullDescription(p);
            } else {
                System.out.println("No se encontró el(los) producto(s) con la descripción: " + descripcion);
            }
        }
    }

    public void searchByCategory(Categoria categoria) {
        for (Producto p : productos.values()) {
            if (p.getCategoria() == categoria) {
                p.fullDescription(p);
            } else {
                System.out.println("No se encontró el(los) producto(s) en la categoría: " + categoria);
            }
        }
    }

    /**
     * * Lista todos los productos en el inventario.
     * **/
    public void listAllProductos() {
        if (!productos.isEmpty()){
            for (Producto p : productos.values()) {
                p.toString();
            }
        } else System.out.println("No hay productos en el inventario.");
    }

    /**
     * * Genera un reporte del inventario, incluyendo el total de productos y el stock por categoría.
     * */
    public void getInventoryReport(){
        System.out.println("-".repeat(30));
        totalProductos();
        System.out.println("-".repeat(30));
        productosPerCategories();
        System.out.println("-".repeat(30));
    }

    /**
     * * Muestra el total de productos y unidades en stock en el inventario.
     * **/
    public void totalProductos(){
        int totalStock = productos.values().stream().mapToInt(Producto::getStock).sum();
        System.out.println("- Total de productos(ítem) en inventario: " + productos.size());
        System.out.println("- Total de unidades en stock: " + totalStock);
    }

    /**
     * * Muestra el total de productos en stock por categoría.
     * * @param categoria La categoría de productos a validar.
     * **/
    public void productosPerCategory(Categoria categoria) {
        long stock = productos.values().stream().filter(p -> p.getCategoria() == categoria).mapToLong(Producto::getStock).sum();
        System.out.println("- [ " + categoria + " ] - " + stock +" productos en stock.");
    }

    /**
     * * Muestra el total de productos en stock por todas las categorías.
     * **/
    public void productosPerCategories(){
        for (Categoria categoria : Categoria.values()) {
            productosPerCategory(categoria);
        }
    }
}

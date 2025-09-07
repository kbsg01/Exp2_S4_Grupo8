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

    public void searchByName(String nombre) {
        for (Producto p : productos.values()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.fullDescription(p);
            } else {
                System.out.println("No se encontró el(los) producto(s) con el nombre: " + nombre);
            }
        }
    }

    public void searchByDescription(String descripcion) {
        for (Producto p : productos.values()) {
            if (p.getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
                p.fullDescription(p);
            } else {
                System.out.println("No se encontró el(los) producto(s) con la descripción: " + descripcion);
            }
        }
    }

    public void listAllProductos() {
        for (Producto p : productos.values()) {
            p.toString();
        }
    }

    public void getInventoryReport(){
        System.out.println("-".repeat(30));
        totalProductos();
        System.out.println("-".repeat(30));
        productosPerCategories();
        System.out.println("-".repeat(30));
    }

    public void totalProductos(){
        System.out.println("- Total de productos en inventario: " + productos.size());
    }

    public void productosPerCategory(Categoria categoria) {
        long stock = productos.values().stream().filter(p -> p.getCategoria() == categoria).mapToLong(Producto::getStock).sum();
        System.out.println("- [ " + categoria + " ] - " + stock +" productos en stock.");
    }

    public void productosPerCategories(){
        for (Categoria categoria : Categoria.values()) {
            productosPerCategory(categoria);
        }
    }
}

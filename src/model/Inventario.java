package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestiona los productos en memoria y delega la persistencia a un repositorio.
 * Proporciona métodos para agregar, eliminar, buscar y listar productos.
 * También ofrece funcionalidades para generar reportes del inventario.
 **/
public class Inventario {
    private final HashMap<String, Producto> productos;

    public Inventario() { this.productos = new HashMap<>(); }

    /**
     * Agrega un nuevo producto al inventario.
     * @param producto El producto a agregar.
     **/
    public void addProducto(Producto producto) { productos.put(producto.getCodigo(), producto); }

    /**
     * Elimina un producto del inventario basado en su código.
     * @param codigo El código del producto a eliminar.
     */
    public void deleteProducto(String codigo) { productos.remove(codigo); }

    /**
     * Busca un producto en el inventario basado en su código.
     * @param codigo El código del producto a buscar.
     * @return El producto encontrado o null si no existe.
     **/
    public Producto searchByCode(String codigo) { return productos.get(codigo); }

    /**
     * Busca productos en el inventario basado en su nombre (case insensitive).
     * @param nombre El nombre del producto a buscar.
     * @return Lista de productos encontrados.
     **/
    public List<Producto> searchByName(String nombre) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(p);
            }
        }
        if (resultados.isEmpty()) {
            System.out.println("No se encontró el(los) producto(s) con el nombre: " + nombre);
        } else {
            if (resultados.size() < 5) {
                for (Producto p : resultados) {
                    p.fullDescription();
                }
            }
        }
        return resultados;
    }

    /**
     * Busca productos en el inventario basado en su descripción.
     * @param descripcion La descripción del producto a buscar.
     * @return resultados Lista de productos encontrados.
     **/
    public List<Producto> searchByDescription(String descripcion) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getDescripcion().toLowerCase().contains(descripcion.toLowerCase())) {
                resultados.add(p);
            }
        }
        if (resultados.isEmpty()) {
            System.out.println("No se encontró el(los) producto(s) con la descripción: " + descripcion);
        } else {
            for (Producto p : resultados) {
                p.fullDescription();
            }
        }
        return resultados;
    }

    /**
     * Lista todos los productos en el inventario.
     **/
    public void listAllProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            for (Producto p : productos.values()) {
                System.out.println(p.toString());
            }
        }
    }

    /**
     * Genera un reporte del inventario, incluyendo el total de productos y el
     * stock por categoría.
     */
    public void getReporteInventario() {
        System.out.println("-".repeat(30));
        System.out.println("Total de productos en inventario: " + totalProductos());
        System.out.println("Total de unidades en stock: " + totalUnidadesEnStock());
        System.out.println("-".repeat(30));
        System.out.println("- Valor total del inventario: $" + valorTotalInventario());
        System.out.println("-".repeat(30));
        System.out.println("- Productos con bajo stock (<= 5):");
        productosConBajoStock(5);
        System.out.println("-".repeat(30));
    }

    /**
     * Muestra el total de productos y unidades en stock en el inventario.
     **/
    public int totalProductos() {
        int totalProductos = productos.size();
        return totalProductos;
    }

    /**
     * Muestra el total de unidades en stock en el inventario.
     **/
    public int totalUnidadesEnStock() {
        int totalStock = productos.values().stream().mapToInt(Producto::getStock).sum();
        return  totalStock;
    }

    /**
     * Lista los productos cuyo stock es menor o igual al umbral dado.
     * @param umbral Valor máximo de stock para considerar un producto con bajo
     * stock.
     **/
    public void productosConBajoStock(int umbral) {
        boolean encontrado = false;
        for (Producto p : productos.values()) {
            if (p.getStock() <= umbral) {
                System.out.println(p.toString());
                encontrado = true;
            }
        }
    }

    /**
     * Calcula el valor total del inventario.
     * @return La suma del precio multiplicado por el stock de cada producto.
     **/
    public double valorTotalInventario() {
        return productos.values().stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())
                .sum();
    }
}

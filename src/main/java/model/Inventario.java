package model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Gestiona los productos en memoria y delega la persistencia a un repositorio.
 * Proporciona métodos para agregar, eliminar, buscar y listar productos.
 * También ofrece funcionalidades para generar reportes del inventario.
 **/
public class Inventario {
    private HashMap<String, Producto> productos;

    public Inventario() { this.productos = new HashMap<>(); }

    /**
     * Agrega un nuevo producto al inventario.
     * @param producto El producto a agregar.
     **/
    public void addProducto(Producto producto) {
        try {
            if(this.productos.containsKey(producto.getCodigo())) {
                throw new IllegalArgumentException("Producto ya existe");
            }
            productos.put(producto.getCodigo(), producto);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProducto(Producto producto) {
        if(!this.productos.containsKey(producto.getCodigo())) {
            throw new IllegalArgumentException("Producto no existe");
        }
        productos.put(producto.getCodigo(), producto);
    }

    /**
     * Elimina un producto del inventario basado en su código.
     * @param codigo El código del producto a eliminar.
     */
    public void deleteProducto(String codigo) {
        productos.remove(codigo);
    }

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
     * stock, valor total del inventario.
     * @return El reporte del inventario en formato String.
     */
    public String getReporteInventario() {
        String reporte = "REPORTE DEL INVENTARIO\n";
        reporte += "-".repeat(30) + "\n";
        reporte += MessageFormat.format("Total de productos: {0}\n", totalProductos());
        reporte += MessageFormat.format("Total de unidades en stock: {0}\n", totalUnidadesEnStock());
        reporte += MessageFormat.format("Valor total del inventario: ${0}\n", valorTotalInventario());
        reporte += "-".repeat(30) + "\n";
        reporte += MessageFormat.format("ALERTA BAJO STOCK\n{0}\n", ((List<Producto>)productosConBajoStock(5)));
        reporte += "-".repeat(30) + "\n";
        reporte += MessageFormat.format("Fecha del reporte: {0}\n", java.time.LocalDate.now());
        return reporte;
    }

    /**
     * Muestra el total de productos y unidades en stock en el inventario.
     **/
    public int totalProductos() {
        return productos.size();
    }

    /**
     * Muestra el total de unidades en stock en el inventario.
     **/
    public int totalUnidadesEnStock() {
        return productos.values().stream().mapToInt(Producto::getStock).sum();
    }

    /**
     * Lista los productos cuyo stock es menor o igual al umbral dado.
     * @param umbral Valor máximo de stock para considerar un producto con bajo
     * stock.
     **/
    public List<Producto> productosConBajoStock(int umbral) {
        List<Producto> bajoStock = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getStock() <= umbral) {
                bajoStock.add(p);
            }
        }
        return bajoStock;
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

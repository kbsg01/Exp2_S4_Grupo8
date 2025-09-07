package model;

public class Producto {
    private final String codigo;
    private String nombre;
    private Categoria categoria;
    private String descripcion;
    private int stock;
    private double precio;

    /**
     * Constructor de la clase Producto.
     * @param codigo Código único del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param stock Cantidad en inventario.
     * @param precio Precio del producto.
     *
     * */
    public Producto(String codigo, String nombre, Categoria categoria, String descripcion, double precio, int stock ) {
        this.codigo = codigo;
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setStock(stock);
        this.setPrecio(precio);
        this.setCategoria(categoria);
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) {
        if (categoria != null) {
            this.categoria = categoria;
        }
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    /**
     * Muestra la descripción completa del producto.
     * @param p El producto cuya descripción se va a mostrar.
    * **/
    public void fullDescription(Producto p) {
        System.out.println("-".repeat(30));
        System.out.println("DETALLE DEL PRODUCTO");
        System.out.println("-".repeat(30));
        System.out.println("Código: " + p.getCodigo());
        System.out.println("Nombre: " + p.getNombre());
        System.out.printf("Categoría: %s%n", p.getCategoria() != null ? p.getCategoria() : "Sin categoría");
        System.out.println("Precio: $" + p.getPrecio());
        System.out.println("Stock: "+ p.getStock());
        System.out.println("Descripción: " + descripcion);
        System.out.println("-".repeat(30));
    }

    /**
     * Representación en cadena del producto.
     * @return Una cadena que representa el producto.
     * **/
    @Override
    public String toString() {
        String categoria = this.categoria == null ? "Sin categoría" : this.categoria.name();
        return "[ " + codigo + " ] | "+ stock+" | " + nombre + " | " + categoria + " | $" + precio + " | " + descripcion.substring(0, Math.min(20, descripcion.length())) + "..."+" |" ;
    }
}

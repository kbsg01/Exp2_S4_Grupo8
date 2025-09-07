package model;

public class Producto {
    private final String codigo;
    private String nombre;
    private Categoria categoria;
    private int stock;
    private double precio;
    private String descripcion;

    // Constructor
    public Producto(String nombre, double precio, String descripcion, String codigo) {
        this.codigo = codigo;
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    private void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }


    public void fullDescription(Producto p) {
        System.out.println("-".repeat(30));
        System.out.println("DETALLE DEL PRODUCTO");
        System.out.println("-".repeat(30));
        System.out.println("Código: " + p.getCodigo());
        System.out.println("Nombre: " + p.getNombre());
        System.out.printf("Categoría: %s%n", p.getCategoria() != null ? p.getCategoria() : "Sin categoría");
        System.out.println("Precio: $" + p.getPrecio());
        System.out.println("Strok: "+ p.getStock());
        System.out.println("Descripción: " + descripcion);
        System.out.println("-".repeat(30));
    }

    @Override
    public String toString() {
        String categoria = this.categoria == null ? "Sin categoría" : this.categoria.name();
        return "[ " + codigo + " ] | "+ stock+" | " + nombre + " | " + categoria + " | $" + precio + " | " + descripcion.substring(0, Math.min(20, descripcion.length())) + "..."+" |" ;
    }
}

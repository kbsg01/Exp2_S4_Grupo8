package view;

import model.Producto;

import java.util.Scanner;

/**
 * Vista para la creación y actualización de productos.
 * Se encarga de validar las entradas del usuario antes de interactuar con el modelo.
 */
public class ProductoView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayProductoMenu() {
        System.out.println("=== MENÚ MODIFICACIÓN INVENTARIO ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. actualizar producto");
        System.out.println("0. Volver al menú principal");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public int getUserChoice() {
        while (true) {
            try {
                System.out.print("Seleccione una opción: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                displayMessage("Error: ingrese un número válido.");
            }
        }
    }

    /**
     * Solicita los datos al usuario y crea un producto validado.
     * @return Producto creado o null si ocurre un error.
     */
    public Producto crearProducto() {
        try {
            String codigo = obtenerCampoObligatorio("Código");
            String nombre = obtenerCampoObligatorio("Nombre");
            String descripcion = obtenerCampoObligatorio("Descripción");
            double precio = obtenerPrecio();
            int stock = obtenerStock();
            return new Producto(codigo, nombre, descripcion, precio, stock);
        } catch (Exception e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un producto existente solicitando nueva información al usuario.
     * Los campos vacíos mantienen el valor anterior.
     * @param producto Producto a actualizar.
     */
    public void actualizarProducto(Producto producto) {
        try {
            String nombre = obtenerCampoOpcional("Nombre", producto.getNombre());
            if (!nombre.isEmpty()) {
                producto.setNombre(nombre);
            }

            String descripcion = obtenerCampoOpcional("Descripción", producto.getDescripcion());
            if (!descripcion.isEmpty()) {
                producto.setDescripcion(descripcion);
            }

            String precioStr = obtenerCampoOpcional("Precio", String.valueOf(producto.getPrecio()));
            if (!precioStr.isEmpty()) {
                try {
                    double precio = Double.parseDouble(precioStr);
                    if (precio < 0) {
                        System.out.println("El precio no puede ser negativo. Se mantiene el valor anterior.");
                    } else {
                        producto.setPrecio(precio);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Precio inválido. Se mantiene el valor anterior.");
                }
            }

            String stockStr = obtenerCampoOpcional("Stock", String.valueOf(producto.getStock()));
            if (!stockStr.isEmpty()) {
                try {
                    int stock = Integer.parseInt(stockStr);
                    if (stock < 0) {
                        System.out.println("El stock no puede ser negativo. Se mantiene el valor anterior.");
                    } else {
                        producto.setStock(stock);
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Stock inválido. Se mantiene el valor anterior.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Métodos auxiliares

    /** Solicita y valida el precio. */
    private double obtenerPrecio() {
        while (true) {
            System.out.print("Precio: ");
            String input = scanner.nextLine().trim();
            try {
                double precio = Double.parseDouble(input);
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                } else {
                    return precio;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido para el precio.");
            }
        }
    }

    /** Solicita y valida el stock. */
    private int obtenerStock() {
        while (true) {
            System.out.print("Stock: ");
            String input = scanner.nextLine().trim();
            try {
                int stock = Integer.parseInt(input);
                if (stock < 0) {
                    System.out.println("El stock debe ser mayor o igual a 0.");
                } else {
                    return stock;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero para el stock."+e.getMessage());
            }
        }
    }

    /** Solicita repetidamente un campo obligatorio hasta que tenga contenido. */
    public String obtenerCampoObligatorio(String campo) {
        String valor;
        do {
            System.out.print(campo + ": ");
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("El " + campo.toLowerCase() + " es obligatorio.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    /** Solicita un campo opcional mostrando el valor actual. */
    public String obtenerCampoOpcional(String campo, String actual) {
        System.out.printf("%s (%s): ", campo, actual);
        return scanner.nextLine().trim();
    }
}
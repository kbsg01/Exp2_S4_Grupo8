package view;

import model.Inventario;
import model.Producto;

import java.util.Scanner;

public class InventarioView {
    private final Inventario inventario;
    private final Scanner scanner = new Scanner(System.in);

    public InventarioView(Inventario inventario) {
        this.inventario = inventario;
    }

    //Menu de inventario
    public void displayInventarioMenu(){
        System.out.println("=== MENÚ VISUALIZACIÓN DE INVENTARIO ===");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Generar reporte de inventario");
        System.out.println("3. Buscar producto por código");
        System.out.println("4. Buscar producto por nombre");
        System.out.println("5. Buscar producto por descripción");
        System.out.println("0. Volver al menú principal");

    }

    //Listar todos los productos
    public void listarProductos(){
        System.out.println("=== LISTA DE PRODUCTOS EN INVENTARIO ===");
        System.out.println("Código | Stock | Nombre |  Precio | Descripción ");
        inventario.listAllProductos();
    }

    // Genera reporte inventario
    public void generarReporteInventario(){
        System.out.println("=== REPORTE DE INVENTARIO ===");
        System.out.println(inventario.getReporteInventario());
        
    }

    //Buscar por ID
    public void buscarProductoPorCodigo() {
        String codigo = getInput("Código del producto: ");
        Producto p = inventario.searchByCode(codigo);
        if (p != null) {
            p.fullDescription();
        } else {
            displayMessage("No se encontró un producto con ese código.");
        }
    }
    //Buscar por nombre
    public void buscarProductoPorNombre() {
        String nombre = getInput("Nombre o parte del nombre del producto: ");
        inventario.searchByName(nombre);
    }

    //Buscar por descripcion
    public void buscarProductoPorDescripcion() {
        String descripcion = getInput("Descripción o parte de la descripción del producto: ");
        inventario.searchByDescription(descripcion);
    }

    //métodos axiliares
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

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /** Permite acceder al modelo Inventario asociado a esta vista. */
    public Inventario getInventario() {
        return inventario;
    }
}

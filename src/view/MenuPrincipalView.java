package view;

import java.util.Scanner;

public class MenuPrincipalView implements IView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Agregar producto");
        System.out.println("2. Eliminar producto");
        System.out.println("3. Buscar producto por código");
        System.out.println("4. Listar productos");
        System.out.println("5. Reporte de inventario");
        System.out.println("0. Salir");
    }

    @Override
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

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
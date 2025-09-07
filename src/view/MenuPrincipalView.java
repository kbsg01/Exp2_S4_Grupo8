package view;

import java.util.Scanner;

public class MenuPrincipalView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Modificar inventario");
        System.out.println("2. Buscar y listar productos");
        System.out.println("3. Reporte de inventario");
        System.out.println("0. Salir");
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

    String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
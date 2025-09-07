package view;

public interface IView {

    void displayMenu();
    int getUserChoice();
    void displayMessage(String message);
    String getInput(String prompt);
}

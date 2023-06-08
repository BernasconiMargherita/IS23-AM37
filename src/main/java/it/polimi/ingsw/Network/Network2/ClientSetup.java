package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.view.gui.Gui;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class ClientSetup extends Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose mode (gui/cli): ");
        String mode = scanner.nextLine();

        if (mode.equalsIgnoreCase("gui")) {
            launch(args);
        } else if (mode.equalsIgnoreCase("cli")) {
            startCLI();
        } else {
            System.out.println("Invalid mode selected. Exiting.");
        }

        scanner.close();
    }

    public void start(Stage primaryStage) {
        Gui gui = new Gui();
        gui.start(primaryStage);
    }

    public static void startCLI() {
        System.out.println("CLI mode selected. Enter commands:");
    }
}



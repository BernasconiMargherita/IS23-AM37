package it.polimi.ingsw.Network2;


import it.polimi.ingsw.view.cli.Cli;
import it.polimi.ingsw.view.gui.Gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The ClientSetup class is the entry point of the client application.
 * It provides the option to choose between GUI mode and CLI mode for running the client.
 */
public class ClientSetup extends Application {

    /**
     * The main method of the client application.
     * It prompts the user to choose between GUI mode and CLI mode and launches the application accordingly.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Choose mode (gui/cli): ");
            String mode = scanner.nextLine();
            if (mode.equalsIgnoreCase("gui")) {
                launch(args);
                break;
            } else if (mode.equalsIgnoreCase("cli")) {
                startCLI(scanner);
                break;
            } else {
                System.out.println("Invalid mode selected. Choose mode (gui/cli): ");
            }
        }


    }

    /**
     * The start method of the JavaFX application in GUI mode.
     * It creates an instance of the Gui class and starts the GUI application.
     *
     * @param primaryStage The primary stage for the JavaFX application
     */
    public void start(Stage primaryStage) {
        Gui gui = new Gui();
        gui.start(primaryStage);
        primaryStage.setOnHidden(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Starts the application in CLI mode.
     * It creates an instance of the Cli class and starts the CLI application.
     *
     * @param scanner The scanner object used for CLI input
     */
    public static void startCLI(Scanner scanner) {
        new Cli(scanner).start();
    }
}



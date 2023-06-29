package it.polimi.ingsw;
import it.polimi.ingsw.Network2.Client.ClientSetup;
import it.polimi.ingsw.view.cli.Cli;
import javafx.application.Application;

import java.util.Scanner;

public class Launcher {
    public static void main(final String[] args) {

            startCLI(new Scanner(System.in));
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

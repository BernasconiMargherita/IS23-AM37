package it.polimi.ingsw;
import com.google.gson.Gson;
import it.polimi.ingsw.Network.Client.ClientSetup;
import it.polimi.ingsw.view.cli.Cli;
import javafx.application.Application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Launcher {

    public static void main(final String[] args) {
        int TPCPort = 0;
        int RMIPort = 0;

        if(args[1]!=null){
            RMIPort=Integer.parseInt(args[1]);
        }else System.exit(-1);

        if(args[2]!=null){
            TPCPort=Integer.parseInt(args[2]);
        }else System.exit(-1);

        Gson gson=new Gson();
        String jsonTCP=gson.toJson(TPCPort);
        String jsonRMI=gson.toJson(RMIPort);

        FileWriter writer = null;
        try {
            writer = new FileWriter("/json/ServerPortTCP.json");
            writer.write(jsonTCP);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer = new FileWriter("/json/ServerPortRMI.json");
            writer.write(jsonRMI);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (args[0].equalsIgnoreCase("gui")) {
            Application.launch(ClientSetup.class, args);
        } else if (args[0].equalsIgnoreCase("cli")) {
            startCLI(new Scanner(System.in));
        }
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

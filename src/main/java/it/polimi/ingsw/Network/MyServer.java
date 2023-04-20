package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Player.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import it.polimi.ingsw.Network.RemoteController;



public class MyServer {
    /**
     * The main method creates a RemoteControllerImpl object, creates a registry on port 1099,
     * and binds the RemoteControllerImpl object to the registry with the name "RemoteController".
     * This starts the server and prints a message to indicate that the server is running.
     * @param args The command line arguments passed to the main method.
     * @throws Exception If an error occurs while creating the registry or binding the object to it.
     */
    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;
        Gson gson = new Gson();


        try{
            FileReader filePort = new FileReader("ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        RemoteController server = new RemoteControllerImpl();
        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind("RemoteController", server);
        System.out.println("Server is running...");
        while(true){

        }
    }
}
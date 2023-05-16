package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Network.RemoteClient;
import it.polimi.ingsw.model.Player.Player;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import it.polimi.ingsw.Network.RemoteController;

public class MyClient {


    /**

     * The main method that starts the client application. It creates a client object, connects to the server via RMI,
     * and continuously prompts the user to enter their nickname and play the game until the game is over.
     * @param args an array of command-line arguments
     * @throws Exception if there is an error while connecting to the server
     */


    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;
        Scanner scanner = new Scanner(System.in);

        Gson gson = new Gson();
        try{
            FileReader filePort = new FileReader("src/main/resources/ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            FileReader fileName = new FileReader("src/main/resources/ServerHostName.json");
            hostName = gson.fromJson(fileName, String.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Registry registry = LocateRegistry.getRegistry(hostName, portNumber);
        RemoteController server = null;
        try {
            server = (RemoteController) registry.lookup("RemoteController");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }


        assert server != null;
        ClientImpl client = new ClientImpl(server);


    }



}
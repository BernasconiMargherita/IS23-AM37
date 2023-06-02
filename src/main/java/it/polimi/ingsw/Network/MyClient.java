package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Utils.Coordinates;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MyClient {


    private  Client client;
    public void createConnection(String protocol, String username, String address, int port) throws IOException {
        if(protocol.equals("rmi") || protocol.equals("RMI")){
            Registry registry = LocateRegistry.getRegistry(address, port);
            RemoteController server = null;
            try {
                server = (RemoteController) registry.lookup("RemoteController");
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
            Client client = new RMIClient(server);

        } else{
            Socket serverSocket = new Socket(address, port);
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            client = new TCPClient(in , out);
            client.setNickname(username);
        }

    }

    public boolean imFirstPlayer() throws IOException {
        return client.imFirstPlayer();
    }

    public boolean initMess() throws IOException {
        return client.initMess();
    }

    public void setNumOfPlayers(int numOfPlayers) throws IOException {
        client.setNumOfPlayers(numOfPlayers);
    }

    public boolean yourTurn() throws IOException {
        return client.yourTurn();
    }

    public String removeTCP(Coordinates[] coordinates) throws IOException {
        return client.removeTCP(coordinates);
    }


    public String columnInsertion(int column) throws IOException {
        return client.columnInsertion(column);
    }



    public static void main(String[] args) throws Exception {

    }


}
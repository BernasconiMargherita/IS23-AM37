package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.model.Player.Player;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MyClient {





    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;
        Scanner scanner = new Scanner(System.in);

        Gson gson = new Gson();
        try{
            FileReader filePort = new FileReader("ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            FileReader fileName = new FileReader("ServerHostName.json");
            hostName = gson.fromJson(fileName, String.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Registry registry = LocateRegistry.getRegistry(hostName, portNumber);
        RemoteController server = (RemoteController)  registry.lookup("RemoteController");

        System.out.print("Enter your Nickname: ");
        ClientImpl client = new ClientImpl(server, new Player((scanner.next())));

        while(true){
            if(client.isMyTurn()){
                server.placeInShelf(client.getGameID());
            }
        }
    }



}
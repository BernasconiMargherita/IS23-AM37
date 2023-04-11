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

    private RemoteController server;
    private Player player;
    private boolean myTurn;

    protected MyClient(RemoteController server, Player player) throws Exception{
        super();
        Scanner scanner = new Scanner(System.in);
        this.server = server;
        this.player = player;
        server.registerPlayer(this.player);
        myTurn = player.equals(server.getCurrentPlayer());
        System.out.println("Connected as " + player);
        if (myTurn) {
            System.out.println("It's your turn");
        } else {
            System.out.println("Waiting for opponent to move...");
        }
    }



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

        try{
            FileReader fileName = new FileReader("ServerHostName.json");
            hostName = gson.fromJson(fileName, String.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Registry registry = LocateRegistry.getRegistry(hostName, portNumber);
        RemoteController server = (RemoteController)  registry.lookup("RemoteController");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        MyClient client = new MyClient(server, new Player(name));


        while(true){
            if(client.isMyTurn()){
                server.removeFromBoard();
                server.addCardInColumn();
                server.checkCommon();
                if(server.isLastTurn()){
                    server.checkPersonal();
                }
            }
         }
    }

    public boolean isMyTurn(){
        return myTurn;
    }
}
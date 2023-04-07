package it.polimi.ingsw.Network;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyClient {

    public static void main(String[] args) throws NotBoundException, RemoteException {
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
        RemoteController remoteController = (RemoteController)  registry.lookup("RemoteController");
        remoteController.doSomething();
    }
}
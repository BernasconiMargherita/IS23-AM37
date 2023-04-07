package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();

        RemoteControllerImpl remoteControllerImpl =new RemoteControllerImpl(controller);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RemoteController", remoteControllerImpl);

        System.out.println("Server is running...");

    }
}
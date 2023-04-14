package it.polimi.ingsw.Network;

import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Player.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) throws Exception {
        RemoteController server = new RemoteControllerImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RemoteController", server);
        System.out.println("Server is running...");
    }
}
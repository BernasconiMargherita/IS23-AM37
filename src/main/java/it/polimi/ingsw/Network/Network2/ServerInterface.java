package it.polimi.ingsw.Network.Network2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    String onMessage(String request) throws RemoteException;
}


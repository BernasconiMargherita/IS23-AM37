package it.polimi.ingsw.Network2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote {
    void notify(String message) throws RemoteException;
}

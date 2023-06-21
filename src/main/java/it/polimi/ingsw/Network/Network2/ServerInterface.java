package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    Message onMessage(Message message) throws RemoteException;

    void disconnect();
}


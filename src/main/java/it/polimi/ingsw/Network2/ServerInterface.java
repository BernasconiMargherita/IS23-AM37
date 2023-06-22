package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    void onMessage(Message message) throws RemoteException;
    long addRmiClient(CommunicationProtocol protocol) throws RemoteException;

}


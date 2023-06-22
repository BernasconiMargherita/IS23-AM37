package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    void onMessage(Message message) throws RemoteException;

    void disconnect() throws RemoteException;

    public long addRmiClient(CommunicationProtocol protocol) throws RemoteException;


}


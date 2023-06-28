package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommunicationProtocol extends Remote {
    void sendMessage(Message message) throws RemoteException;
    void onMessage(Message message) throws RemoteException;
    ArrayList<Message> getMessages() throws RemoteException;
    void closeConnection() throws RemoteException;
    void setup() throws RemoteException;
    long getUID() throws RemoteException;

    void ping() throws RemoteException;

    void onDisconnection() throws RemoteException;
}


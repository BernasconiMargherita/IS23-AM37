package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CommunicationProtocol extends Remote {
    public void sendMessage(Message message) throws RemoteException;
    public void onMessage(Message message)throws RemoteException;

    public ArrayList<Message> getMessages()throws RemoteException;

    public void closeConnection()throws RemoteException;

    public  void setup()throws RemoteException;

    public long getUID()throws RemoteException;
}


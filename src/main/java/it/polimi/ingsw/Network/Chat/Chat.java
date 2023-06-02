package it.polimi.ingsw.Network.Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote{
    void sendMessage(String message) throws RemoteException;
    String[] getMessages() throws RemoteException;

}



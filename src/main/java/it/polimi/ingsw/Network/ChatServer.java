package it.polimi.ingsw.Network;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements Chat{
    private List<String> messages;

    public ChatServer() throws RemoteException {
        messages = new ArrayList<>();
    }

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        messages.add(message);
    }

    @Override
    public synchronized String[] getMessages() throws RemoteException {
        return messages.toArray(new String[0]);
    }
}



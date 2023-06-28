package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.Message;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMICommunicationProtocol extends UnicastRemoteObject implements CommunicationProtocol, Serializable {
    private final String serverUrl;
    private ArrayList<Message> messageList=new ArrayList<>();
    private ServerInterface server;
    long UID;

    public RMICommunicationProtocol(String serverUrl) throws RemoteException {
        super();
        Registry registry = LocateRegistry.getRegistry("localhost", 5001);
        try {
            server = (ServerInterface) registry.lookup(serverUrl);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
        this.serverUrl = "RemoteController";
    }

    public void sendMessage(Message message) throws RemoteException {
        try {
            server.onMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) throws RemoteException {
            messageList.add(message);
    }

    @Override
    public ArrayList<Message> getMessages() throws RemoteException {
        ArrayList<Message> copy;
        copy = new ArrayList<>(List.copyOf(messageList));
        messageList.clear();
        return copy;
    }

    @Override
    public void closeConnection() {
        try {
            server.disconnect();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        server = null;
    }

    @Override
    public void setup() throws RemoteException{
        try{
            UID = server.addRmiClient(this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long getUID() throws RemoteException{
        return UID;
    }

    @Override
    public void ping() throws RemoteException {
        System.out.println("ping arrivato");
    }

    @Override
    public void onDisconnection() throws RemoteException {
        System.out.println("Partita finita. disconnessione");
        this.server=null;

    }
}


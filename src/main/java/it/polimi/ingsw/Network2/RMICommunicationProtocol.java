package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.ErrorMessage;
import it.polimi.ingsw.Network2.Messages.Message;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * The RMICommunicationProtocol class represents an implementation of the CommunicationProtocol interface
 * using the RMI (Remote Method Invocation) protocol for communication between the client and server.
 * It extends the UnicastRemoteObject class to enable remote object communication,
 * and implements the Serializable interface for object serialization.
 */
public class RMICommunicationProtocol extends UnicastRemoteObject implements CommunicationProtocol, Serializable {
    private final String serverUrl;
    private ArrayList<Message> messageList=new ArrayList<>();
    private ServerInterface server;
    long UID;

    /**
     * Constructs a new RMICommunicationProtocol instance with the specified server URL.
     *
     * @param serverUrl the URL of the server to connect to
     * @throws RemoteException if a remote exception occurs during the RMI setup
     */
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

    /**
     * Sends a message to the server using the RMI protocol.
     *
     * @param message the message to be sent
     * @throws RemoteException if a remote exception occurs during the RMI communication
     */
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
}


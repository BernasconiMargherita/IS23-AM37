package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.ErrorMessage;
import it.polimi.ingsw.Network2.Messages.Message;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class RMICommunicationProtocol implements CommunicationProtocol, Serializable {
    private final String serverUrl;
    private ArrayList<Message> messageList;
    private ServerInterface server;
    long UID;

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            server = (ServerInterface) registry.lookup(serverUrl);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage("Error occurred during RMI communication");
            onMessage(errorMessage);
        }
    }

    public void sendMessage(Message message) {

        try {
            server.onMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public ArrayList<Message> getMessages() {

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
    public void setup() {
        try {
            UID = server.addRmiClient(this);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getUID() {
        return UID;
    }
}


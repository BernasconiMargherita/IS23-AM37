package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.ErrorMessage;
import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class RMICommunicationProtocol implements CommunicationProtocol {
    private final String serverUrl;
    private ArrayList<Message> messageList;
    private ServerInterface server;
    long UID;

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
    }

    public void sendMessage(Message message) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            server = (ServerInterface) registry.lookup(serverUrl);
            server.onMessage(message);

        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage("Error occurred during RMI communication");
            onMessage(errorMessage);
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
        server.disconnect();
        server = null;
    }

    @Override
    public void setup() {
        UID = server.addRmiClient(this);
    }

    @Override
    public long getUID() {
        return UID;
    }
}


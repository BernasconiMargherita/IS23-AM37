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

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
    }

    public Message sendMessage(Message message) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            server = (ServerInterface) registry.lookup(serverUrl);
            return server.onMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorMessage("Error occurred during RMI communication");
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

}


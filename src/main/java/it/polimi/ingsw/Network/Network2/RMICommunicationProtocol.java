package it.polimi.ingsw.Network.Network2;


import it.polimi.ingsw.Network.Messages.Message;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICommunicationProtocol implements CommunicationProtocol {
    private String serverUrl;

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
    }

    public String sendMessage(Message message) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            ServerInterface server = (ServerInterface) registry.lookup(serverUrl);
            return server.onMessage(message).getMessage();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during RMI communication";
        }
    }
}


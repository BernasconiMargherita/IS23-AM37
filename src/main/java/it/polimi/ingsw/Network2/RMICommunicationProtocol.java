package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICommunicationProtocol implements CommunicationProtocol {
    private final String serverUrl;
    private ServerInterface server;

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            ServerInterface server = (ServerInterface) registry.lookup(serverUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(Message message) {
        try {
            return server.onMessage(message).getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during RMI communication";
        }
    }
}


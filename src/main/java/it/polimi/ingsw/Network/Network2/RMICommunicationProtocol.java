package it.polimi.ingsw.Network.Network2;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICommunicationProtocol implements CommunicationProtocol {
    private String serverUrl;

    public RMICommunicationProtocol(String serverUrl) {
        this.serverUrl = "RemoteController";
    }

    public String sendRequest(String request) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5001);
            ServerInterface server = (ServerInterface) registry.lookup(serverUrl);
            return server.onMessage(request);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during RMI communication";
        }
    }
}


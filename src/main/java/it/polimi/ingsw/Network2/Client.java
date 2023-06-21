package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements ClientCallback  {
    private String username;
    private CommunicationProtocol communicationProtocol;

    public Client(CommunicationProtocol communicationProtocol) throws RemoteException {
        super();
        this.communicationProtocol = communicationProtocol;
    }

    @Override
    public void notify(String message) throws RemoteException {

    }
    public Message sendMessage(Message message) {
        return communicationProtocol.sendMessage(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void onMessage(Message message){
        communicationProtocol.onMessage(message);
    }

    public ArrayList<Message> getMessages() {
        return communicationProtocol.getMessages();
    }

    public void closeConnection() {
        communicationProtocol.closeConnection();
    }
}


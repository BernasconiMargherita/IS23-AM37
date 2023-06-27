package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject{
    private int gameID;
    private String username;
    private final CommunicationProtocol communicationProtocol;

    public Client(CommunicationProtocol communicationProtocol) throws RemoteException {
        super();
        this.communicationProtocol = communicationProtocol;
    }


    //da client a server
    public void sendMessage(Message message) {
        try {
            communicationProtocol.sendMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   //da server a client
    public void onMessage(Message message){
        try {
            communicationProtocol.onMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Message> getMessages() {
        try {
            return communicationProtocol.getMessages();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            communicationProtocol.closeConnection();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setup(){
        try {
            communicationProtocol.setup();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public long getUID(){
        try {
            return communicationProtocol.getUID();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}


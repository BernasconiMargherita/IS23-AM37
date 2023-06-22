package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements ClientCallback  {
    private int gameID;

    private String username;
    private final CommunicationProtocol communicationProtocol;

    boolean firstPlayer=false;
    private boolean initPlayer=false;

    public Client(CommunicationProtocol communicationProtocol) throws RemoteException {
        super();
        this.communicationProtocol = communicationProtocol;
    }

    @Override
    public void notify(String message) throws RemoteException {

    }
    //da client a server
    public void sendMessage(Message message) {
         communicationProtocol.sendMessage(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   //da server a client
    public void onMessage(Message message){
        communicationProtocol.onMessage(message);
    }

    public ArrayList<Message> getMessages() {
        return communicationProtocol.getMessages();
    }

    public void closeConnection() {
        communicationProtocol.closeConnection();
    }

    public void setup(){
        communicationProtocol.setup();
    }

    public long getUID(){
        return communicationProtocol.getUID();
    }

    public void setFirst() {
        firstPlayer=true;
    }

    public void setInit() {
        initPlayer=true;
    }

    public boolean isInitPlayer() {
        return initPlayer;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}


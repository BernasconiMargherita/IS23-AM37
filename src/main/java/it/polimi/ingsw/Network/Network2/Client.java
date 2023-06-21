package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Chat.ChatClient;
import it.polimi.ingsw.Network.Messages.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Client {
    private String username;
    private final CommunicationProtocol communicationProtocol;
    private ArrayList<Message> messageList;

    public Client(CommunicationProtocol communicationProtocol) {
        this.communicationProtocol = communicationProtocol;

        ChatClient chatClient=new ChatClient();
        chatClient.start();
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

    public void close() {
        communicationProtocol.closeConnection();
    }
}


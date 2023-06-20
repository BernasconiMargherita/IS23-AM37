package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Messages.Message;

public class Client {
    private String username;
    private final CommunicationProtocol communicationProtocol;

    public Client(CommunicationProtocol communicationProtocol) {
        this.communicationProtocol = communicationProtocol;
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
}


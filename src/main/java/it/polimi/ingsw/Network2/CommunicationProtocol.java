package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.util.ArrayList;

public interface CommunicationProtocol {
    Message sendMessage(Message message);
    void onMessage(Message message);

    ArrayList<Message> getMessages();

    void closeConnection();
}


package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Messages.Message;

import java.util.ArrayList;

public interface CommunicationProtocol {

    Message sendMessage(Message message);

    void onMessage(Message message);

    ArrayList<Message> getMessages();

    void closeConnection();
}


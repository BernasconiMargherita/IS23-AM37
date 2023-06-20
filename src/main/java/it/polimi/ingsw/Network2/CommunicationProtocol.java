package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

public interface CommunicationProtocol {
    String sendMessage(Message message);
    void onMessage(Message message);
}


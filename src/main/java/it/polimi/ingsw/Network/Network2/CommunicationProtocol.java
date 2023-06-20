package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.Network.Messages.Message;

public interface CommunicationProtocol {
    Message sendMessage(Message message);
}


package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

public interface Connection {
    public void sendMessage(Message message);
}

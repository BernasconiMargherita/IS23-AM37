package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

public class RMIConnect implements Connection{
    private CommunicationProtocol protocol;
    public RMIConnect(CommunicationProtocol protocol){
        this.protocol = protocol;
    }

    public void sendMessage(Message message){
        protocol.onMessage(message);
    }
}

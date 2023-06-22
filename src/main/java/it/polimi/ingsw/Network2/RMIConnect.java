package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;

public class RMIConnect implements Connection{
    private final CommunicationProtocol protocol;
    private final String nickname;
    public RMIConnect(CommunicationProtocol protocol, String nickname){
        this.protocol = protocol;
        this.nickname = nickname;
    }



    public void sendMessage(Message message){
        try {
            protocol.onMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getNickname() {
        return nickname;
    }
}

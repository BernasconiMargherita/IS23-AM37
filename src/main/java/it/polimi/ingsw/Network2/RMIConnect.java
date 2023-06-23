package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.rmi.RemoteException;

public class RMIConnect implements Connection{
    private final CommunicationProtocol protocol;
    private Long UID;
    private String nickname;
    public RMIConnect(CommunicationProtocol protocol, Long UID, String nickname){
        this.protocol = protocol;
        this.UID = UID;
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
    public Long getUID() {
        return UID;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

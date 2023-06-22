package it.polimi.ingsw.Network2.Messages;

public class WakeMessage extends Message{
    public WakeMessage() {
        super(-1);
    }

    public String typeMessage(){
        return "WakeMessage";
    }
}

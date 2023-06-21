package it.polimi.ingsw.Network2.Messages;

public class InitMessage extends Message {
    public InitMessage() {
    }

    public boolean init(){
        return true;
    }

    public String typeMessage(){
        return "InitMessage";
    }
}

package it.polimi.ingsw.Network2.Messages;

public class SetResponse extends Message{
    public SetResponse() {
        super(-1);
    }

    public String typeMessage(){
        return "SetResponse";
    }

}

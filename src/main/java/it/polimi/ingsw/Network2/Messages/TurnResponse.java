package it.polimi.ingsw.Network2.Messages;

public class TurnResponse extends Message{

    private int status;

    public TurnResponse(int status) {
        this.status = status;
    }

    public String typeMessage(){
        return "TurnResponse";
    }
}

package it.polimi.ingsw.Network2.Messages;

public class InitMessage extends Message {

    private int gameID;

    @Override
    public int getGameID() {
        return gameID;
    }

    public InitMessage() {
    }

    public boolean init(){
        return true;
    }

    public String typeMessage(){
        return "InitMessage";
    }
}

package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginResponse extends Message implements Serializable {
    private boolean usernameError;
    private int gameID;
    private boolean first;
    private boolean init;

    public LoginResponse( int gameID) {
        super(gameID);
        this.usernameError = usernameError;
        this.first = first;
        this.init = init;
        this.gameID = gameID;
    }

    @Override
    public int getGameID() {
        return gameID;
    }
    public String typeMessage(){
        return "LoginResponse";
    }


}

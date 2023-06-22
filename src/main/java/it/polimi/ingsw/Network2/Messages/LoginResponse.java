package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

public class LoginResponse extends Message implements Serializable {
    private boolean usernameError;
    private int gameID;
    private boolean first;
    private boolean init;

    public LoginResponse(boolean usernameError, int gameID, boolean first, boolean init) {
        super(gameID);
        this.usernameError = usernameError;
        this.first = first;
        this.init = init;
    }

    @Override
    public int getGameID() {
        return gameID;
    }

    public boolean isUsernameError() {
        return usernameError;
    }

    public boolean isFirst() {
        return first;
    }

    public String typeMessage(){
        return "LoginResponse";
    }

    public boolean isInit() {
        return init;
    }
}

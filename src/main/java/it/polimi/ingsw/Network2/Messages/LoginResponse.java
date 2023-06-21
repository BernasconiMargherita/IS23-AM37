package it.polimi.ingsw.Network2.Messages;

public class LoginResponse extends Message{
    private boolean usernameError;
    private int gameID;
    private boolean first;
    private boolean init;

    public LoginResponse(boolean usernameError, int gameID, boolean first, boolean init) {
        this.usernameError = usernameError;
        this.gameID = gameID;
        this.first = first;
        this.init = init;
    }

    public boolean isUsernameError() {
        return usernameError;
    }

    public int getGameID() {
        return gameID;
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

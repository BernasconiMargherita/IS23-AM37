package it.polimi.ingsw.Network2.Messages;

public class LoginResponse extends Message{
    private boolean usernameError;
    private int gameID;
    private boolean first;

    public LoginResponse(boolean usernameError, int gameID, boolean first) {
        this.usernameError = usernameError;
        this.gameID = gameID;
        this.first = first;
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
}

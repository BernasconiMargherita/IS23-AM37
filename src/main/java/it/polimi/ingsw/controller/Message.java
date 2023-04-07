package it.polimi.ingsw.controller;

public abstract class Message {
    private final int gameID;
    private final String message;
    private final String senderNickname;


    public Message(int gameID, String message, String senderNickname) {
        this.gameID = gameID;
        this.message = message;
        this.senderNickname = senderNickname;
    }

    public int getGameID() {
        return gameID;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderNickname() {
        return senderNickname;
    }
}

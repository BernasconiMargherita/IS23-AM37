package it.polimi.ingsw.Network;

public class Message {
    private String nickname;
    private String message;
    private int status;

    public String getNickname() {
        return nickname;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

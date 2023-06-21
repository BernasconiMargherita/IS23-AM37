package it.polimi.ingsw.Network2.Messages;

public class endMessage extends Message{

    private String winner;

    public endMessage(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public String typeMessage() {
        return "endMessage";
    }
}

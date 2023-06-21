package it.polimi.ingsw.Network2.Messages;

public class EndMessage extends Message{

    private String winner;

    public EndMessage(String winner) {
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

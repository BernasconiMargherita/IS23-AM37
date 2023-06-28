package it.polimi.ingsw.Network2.Messages;

/**
 * The PingMessage class represents a ping message in the game.
 */
public class PingMessage extends Message {
    private final String typeMessage;

    /**
     * Constructs a PingMessage object with the specified game ID and unique identifier.
     *
     * @param gameID The ID of the game associated with the ping message.
     * @param UID    The unique identifier of the client.
     */
    public PingMessage(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "PingMessage";
    }

    /**
     * Returns the type of the message.
     *
     * @return The type of the message.
     */
    public String typeMessage(){
        return "PingMessage";
    }
}

package it.polimi.ingsw.Network2.Messages;

/**
 * The DisconnectionMessage class represents a disconnection message in the game.
 */
public class DisconnectionMessage extends Message {

    private final String typeMessage;

    /**
     * Constructs a DisconnectionMessage object with the specified game ID and unique identifier.
     *
     * @param gameID The ID of the game associated with the disconnection message.
     * @param UID    The unique identifier of the client.
     */
    public DisconnectionMessage(int gameID, Long UID) {
        super(gameID, UID);
        this.typeMessage = "DisconnectionMessage";
    }

    /**
     * Returns the type of the message.
     *
     * @return The type of the message.
     */
    public String typeMessage(){
        return "DisconnectionMessage";
    }
}

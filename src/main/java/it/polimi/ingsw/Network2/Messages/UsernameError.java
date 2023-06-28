package it.polimi.ingsw.Network2.Messages;

/**
 * The UsernameError class represents an error related to a username in the application.
 */
public class UsernameError extends Message{

    private String typeMessage ;
    /**
     * Constructs a UsernameError object with the specified game ID and unique identifier.
     *
     * @param gameID The ID of the game associated with the error.
     * @param UID    The unique identifier of the client.
     */
    public UsernameError(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "UsernameError";
    }

    @Override
    public String typeMessage() {
        return "UsernameError";
    }

}

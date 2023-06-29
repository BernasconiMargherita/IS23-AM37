package it.polimi.ingsw.Network2.Messages;

import java.io.Serializable;

/**
 * The InitResponse class represents the initial response in the game.
 */
public class InitResponse extends Message implements Serializable {

    private String typeMessage ;

    /**
     * Constructs an InitResponse object with the specified game ID and unique identifier.
     *
     * @param gameID The ID of the game associated with the initial response.
     * @param UID    The unique identifier of the client.
     */
    public InitResponse(int gameID,long UID) {
        super(gameID,UID);
        this.typeMessage = "InitResponse";
    }



    @Override
    public String typeMessage() {
        return "InitResponse";
    }


}

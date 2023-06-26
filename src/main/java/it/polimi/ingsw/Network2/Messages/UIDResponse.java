package it.polimi.ingsw.Network2.Messages;

public class UIDResponse extends Message {
    private String typeMessage ;
    public UIDResponse(long UID) {
        super(-1,UID);
        this.typeMessage = "UIDResponse";
    }
}

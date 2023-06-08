package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class LoginMessage extends Message implements Serializable {

    public LoginMessage(String message) {
        super(message);
    }

}

package it.polimi.ingsw.Network2.Server;

import it.polimi.ingsw.Network2.Messages.Message;

/**
 * The Connection interface represents a connection between a client and a server.
 */
public interface Connection {
    /**
     * Sends a message over the connection.
     *
     * @param message The message to be sent
     */
    public void sendMessage(Message message);


    /**
     * Retrieves the unique identifier associated with the connection.
     *
     * @return The unique identifier
     */
    public Long getUID();


    /**
     * Retrieves the nickname associated with the connection.
     *
     * @return The nickname
     */
    public String getNickname();


    /**
     * Sets the nickname for the connection.
     *
     * @param nickname The nickname to be set
     */
    public void setNickname(String nickname);
}

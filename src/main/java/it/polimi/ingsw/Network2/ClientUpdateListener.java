package it.polimi.ingsw.Network2;


import it.polimi.ingsw.Network2.Messages.Message;

public interface ClientUpdateListener {
    void onUpdate(Message message);
}

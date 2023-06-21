package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.TurnResponse;
import it.polimi.ingsw.Network2.Messages.EndMessage;
import it.polimi.ingsw.Network2.Messages.WakeMessage;

public interface ClientListener {
    void updateBoard(BoardMessage boardMessage);

    void turnResponse(TurnResponse turnResponse);

    void endGame(EndMessage endGameMessage);

    void wakeUp(WakeMessage wakeMessage);



}

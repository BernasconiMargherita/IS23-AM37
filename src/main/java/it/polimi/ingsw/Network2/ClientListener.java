package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.*;

public interface ClientListener {
    void loginResponse(LoginResponse loginResponse);
    void initResponse(InitResponse initResponse);
    void updateBoard(BoardResponse boardMessage);
    void removeResponse(RemoveResponse removeResponse);
    void turnResponse(TurnResponse turnResponse);
    void endGame(EndMessage endGameMessage);
    void wakeUp(WakeMessage wakeMessage);
    void setResponse(SetResponse setResponse);
    void firstResponse(FirstResponse firstResponse);
    void preLoginResponse(PreLoginResponse preLoginResponse);
    void usernameError(UsernameError usernameError);
    void cardsResponse(CardsResponse cardsResponse);

}

package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.Controller;
import it.polimi.ingsw.model.Player.Player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController {
    private final Controller controller;
    public RemoteControllerImpl(Controller controller) throws  RemoteException {
        super();
        this.controller = controller;
    }

    public void doSomething() throws  RemoteException{
        controller.startGame();
    }


}
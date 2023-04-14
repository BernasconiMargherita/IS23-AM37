package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.Player.Player;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientImpl {
    private final int gameID;
    private final RemoteController server;

    private final Player player;
    private boolean myTurn;
    static Scanner scanner = new Scanner(System.in);

    protected ClientImpl(RemoteController server, Player player) throws Exception{
        Scanner scanner = new Scanner(System.in);
        this.server = server;
        this.player = player;
        try{
            gameID = server.registerPlayer(player, server.getCurrentGameID());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected as " + player.getNickname());
    }

    public boolean isMyTurn(){
        try {
            return server.getCurrentPlayer(gameID).equals(player);
        } catch (java.rmi.RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGameID() {
        return gameID;
    }
}
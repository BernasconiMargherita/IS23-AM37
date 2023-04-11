package it.polimi.ingsw.Network;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Player.Player;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MyServer implements RemoteController {
    private Queue<Player> playerList;
    private int numPlayers;
    private Player currentPlayer;
    private GameController gameController;


    public MyServer() {
        playerList = new LinkedList<>();
        /**
         *  idea di RamiroCicero
         */
        numPlayers = 1;
    }

    public static void main(String[] args) throws Exception {
        MyServer server = new MyServer();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RemoteController", server);
        System.out.println("Server is running...");
    }




    @Override
    public void registerPlayer(Player player) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        if (playerList.size() < numPlayers) {
            playerList.add(player);
        } else throw new RemoteException();

        if(playerList.size() == 1) {
            setNumPlayers(scanner.nextInt());
        }
    }

    @Override
    public void setNumPlayers(int numPlayers) throws RemoteException {
            this.numPlayers = numPlayers;
    }


    @Override
    public void startGame() throws RemoteException {
          this.gameController = new GameController(playerList);
          this.gameController.initCase();
    }

    @Override
    public void removeFromBoard() throws RemoteException {
         this.gameController.removeCardFromBoard();
    }

    @Override
    public void addCardInColumn() throws RemoteException {
        this.gameController.addCardInColumn();

    }

    @Override
    public void checkPersonal() throws RemoteException {

    }

    @Override
    public void checkCommon() throws RemoteException {

    }

    @Override
    public void checkWin() {

    }

    public void switchPlayers() {
        playerList.offer(playerList.poll());
        currentPlayer = playerList.peek();
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }


}
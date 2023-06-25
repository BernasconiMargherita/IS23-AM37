package it.polimi.ingsw.Network2;

import com.google.gson.Gson;
import it.polimi.ingsw.Exception.*;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.Utils.TileSlot;
import it.polimi.ingsw.controller.MasterController;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.ColourTile;
import it.polimi.ingsw.model.Tile.Tile;
import javafx.util.Pair;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * This class represents the implementation of the RemoteController interface. It provides the methods
 * to manage the remote operations of the game.
 */
public class RemoteControllerImpl extends UnicastRemoteObject implements RemoteController, Serializable {

    private final MasterController masterController;
    private final HashMap<Long, Socket> tempTcp;
    private final HashMap<Long, CommunicationProtocol> tempRmi;
    private final HashMap<Integer, List<Connection>> clients;
    private int currentGameID;
    private final ArrayList<ArrayList<Pair<Long, String>>> lobby;


    /**
     * Constructor for RemoteControllerImpl class.
     * Creates a new instance of MasterController and sets the currentGameID to 0.
     */
    public RemoteControllerImpl() throws RemoteException {
        super();
        lobby = new ArrayList<>();
        lobby.add(new ArrayList<>());
        tempTcp = new HashMap<>();
        tempRmi = new HashMap<>();
        clients = new HashMap<>();
        masterController = new MasterController();
        currentGameID = -1;
    }

    @Override
    public void onMessage(Message message) throws RemoteException {


        if (message.typeMessage().equals("LoginMessage")) {
            registerPlayer(message.getGameID(), message.getNickname(), message.getUID());
        }

        if (message.typeMessage().equals("PreLoginMessage")) {
            preRegistration(message);
        }

        if (message.typeMessage().equals("SetMessage")) {
            setMaxPlayers(message);
        }
        if (message.typeMessage().equals("RemoveMessage")) {
            remove(message.getGameID(), message.getPositions(), message.getUID());
        }
        if (message.typeMessage().equals("TurnMessage")) {
            turn(message.getGameID(), message.getColours(), message.getColumn(), message.getNickname(), message.getUID());
        }
        if (message.typeMessage().equals("BoardMessage")) {
            sendBoard(message.getGameID(), message.getNickname(), message.getUID());
        }


    }


    public void preRegistration(Message message) throws RemoteException {
        for (int i = 0; i < lobby.size(); i++) {

            if (lobby.get(i).size() == 0) {
                lobby.get(i).add(new Pair<>(message.getUID(), message.getNickname()));
                int gameID = startGame();
                clients.put(gameID, new ArrayList<>());
                addClient(message.getUID(), message.getNickname(), gameID);
                registerPlayer(gameID, message.getNickname(), message.getUID());
                clients.get(gameID).get(getPosition(message.getUID(), gameID)).sendMessage(new FirstResponse(gameID,message.getUID()));
                break;
            }
            Gson gson = new Gson();
            Message preLogMess = new PreLoginResponse(-1,message.getUID());
            if (lobby.get(i).size() == 1 || lobby.get(i).size() == 2) {
                System.out.println("Sono prima");
                lobby.get(i).add(new Pair<>(message.getUID(), message.getNickname()));
                containsTcpTemp(message, gson, preLogMess);
                System.out.println("Sono dopo");
                break;
            }
            else if (lobby.get(i).size() == 3) {
                lobby.get(i).add(new Pair<>(message.getUID(), message.getNickname()));
                lobby.add(new ArrayList<>());
                containsTcpTemp(message, gson, preLogMess);
                break;
            }

        }

    }

    public void containsTcpTemp(Message message, Gson gson, Message preLogMess) throws RemoteException {
        if (tempTcp.containsKey(message.getUID())) {
            try {
                PrintWriter out = new PrintWriter(tempTcp.get(message.getUID()).getOutputStream());

                String jsonMess = gson.toJson(preLogMess);
                out.println(jsonMess);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (tempRmi.containsKey(message.getUID())) {

            CommunicationProtocol communication = tempRmi.get(message.getUID());
            communication.onMessage(preLogMess);

            System.out.println("ho mandato");
        }
        return;
    }

    public void setMaxPlayers(Message message) throws RemoteException {
        int gameID = message.getGameID();
        int maxPlayers = message.getMaxPlayers();
        masterController.getGameController(gameID).setMaxPlayers(maxPlayers);
        clients.get(gameID).get(0).sendMessage(new SetResponse(gameID,message.getUID()));
        Thread timerThread = new Thread(() -> startTimer(message, maxPlayers));
        timerThread.start();
    }

    private void startTimer(Message message, int maxPlayers) {
        while (!checkPlayerCount(message, maxPlayers)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            lobbyManagement(message, maxPlayers);
            Thread.currentThread().interrupt();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkPlayerCount(Message message, int maxPlayers) {
        for (ArrayList<Pair<Long, String>> pairs : lobby) {
            if (pairs.get(0).getKey().equals(message.getUID())) {
                return pairs.size() >= maxPlayers;
            }
        }
        return false;
    }

    public void lobbyManagement(Message message, int maxPlayers) throws RemoteException {

        int gameID = message.getGameID();

        for (ArrayList<Pair<Long, String>> pairs : lobby) {
            if (pairs.get(0).getKey().equals(message.getUID())) {
                for (int j = 1; j < maxPlayers; j++) {
                    Long UID = pairs.get(j).getKey();
                    if (tempTcp.containsKey(UID)) {
                        clients.get(gameID).add(new TCPConnect(tempTcp.get(UID), UID, pairs.get(j).getValue()));
                    }
                    if (tempRmi.containsKey(UID)) {
                        clients.get(gameID).add(new RMIConnect(tempRmi.get(UID), UID, pairs.get(j).getValue()));
                    }
                    registerPlayer(gameID, pairs.get(j).getValue(), UID);
                }

                initGame(gameID);

                if (lobby.get(gameID).size() > maxPlayers) {
                    if (lobby.get(lobby.size() - 1).size() == 4) {
                        lobby.add(new ArrayList<>());
                    }
                    for (int j = lobby.get(gameID).size() - maxPlayers; j < lobby.get(gameID).size(); j++) {
                        for (int k = gameID + 1; k < lobby.size(); k++) {
                            if (lobby.get(k).size() == 0) {
                                lobby.get(k).add(new Pair<>(lobby.get(gameID).get(j).getKey(), lobby.get(gameID).get(j).getValue()));
                                int gameID2 = startGame();
                                clients.put(gameID2, new ArrayList<>());
                                if (tempTcp.containsKey(lobby.get(gameID).get(j).getKey())) {
                                    clients.get(gameID2).add(new TCPConnect(tempTcp.get(lobby.get(gameID2).get(j).getKey()), lobby.get(gameID2).get(j).getKey(), lobby.get(gameID2).get(j).getValue()));
                                }
                                if (tempRmi.containsKey(lobby.get(gameID).get(j).getKey())) {
                                    clients.get(gameID2).add(new RMIConnect(tempRmi.get(lobby.get(gameID2).get(j).getKey()), lobby.get(gameID2).get(j).getKey(), lobby.get(gameID2).get(j).getValue()));
                                }
                                registerPlayer(gameID2, lobby.get(gameID2).get(j).getValue(), lobby.get(gameID2).get(j).getKey());
                                clients.get(gameID2).get(getPosition(lobby.get(gameID).get(j).getKey(), gameID2)).sendMessage(new FirstResponse(gameID2,lobby.get(gameID).get(j).getKey()));
                            }

                            if (lobby.get(k).size() == 1 || lobby.get(k).size() == 2) {
                                lobby.get(k).add(new Pair<>(lobby.get(gameID).get(j).getKey(), lobby.get(gameID).get(j).getValue()));
                            }

                            if (lobby.get(k).size() == 3) {
                                lobby.get(k).add(new Pair<>(lobby.get(gameID).get(j).getKey(), lobby.get(gameID).get(j).getValue()));
                                lobby.add(new ArrayList<>());
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    protected void addClient(Long UID, String nickname, int gameID) {
        if (tempTcp.containsKey(UID)) {
            clients.get(gameID).add(new TCPConnect(tempTcp.get(UID), UID, nickname));
        }

        if (tempRmi.containsKey(UID)) {
            clients.get(gameID).add(new RMIConnect(tempRmi.get(UID), UID, nickname));
        }
        return;
    }


    public void addRmiCl(long UID, CommunicationProtocol protocol) throws RemoteException {
        tempRmi.put(UID, protocol);
    }

    public void addTcpCl(long UID, Socket socket) throws RemoteException {
        tempTcp.put(UID, socket);
    }


    @Override
    public synchronized int startGame() throws RemoteException {
        masterController.newGameController();
        currentGameID=currentGameID + 1;
        return currentGameID;
    }


    @Override
    public synchronized void registerPlayer(int gameID, String nickname, Long UID) throws RemoteException {
        try {
            clients.get(gameID).get(getPosition(UID, gameID)).setNickname(nickname);
            masterController.getGameController(gameID).login(nickname);

        } catch (UsernameException e) {
            clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(new UsernameError(gameID,UID));
        } catch (GameAlreadyStarted | MaxPlayerException e) {
            throw new RuntimeException(e);
        }
        if (masterController.getGameController(gameID).getNumOfPlayers() != 1) {
            clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(new LoginResponse(gameID,UID));
        }

    }


    public void initGame(int gameID) throws RemoteException {
        try {
            this.masterController.getGameController(gameID).initGame();
            ArrayList<CardCommonTarget> commonTargets = masterController.getGameController(gameID).getCommonTargets();
            for (int i = 0; i < clients.get(gameID).size(); i++) {
                CardPersonalTarget cardPersonalTarget = masterController.getGameController(gameID).getCardPersonalTarget(clients.get(gameID).get(i).getNickname());
                Message initResponse = new InitResponse(commonTargets, cardPersonalTarget,gameID,clients.get(gameID).get(i).getUID());
                clients.get(gameID).get(i).sendMessage(initResponse);
            }
            currentPlayer(gameID);
        } catch (GameNotReadyException | GameAlreadyStarted e) {
            throw new RuntimeException(e);
        }
    }

    public void currentPlayer(int gameID) throws RemoteException {
        String player = masterController.getGameController(gameID).getCurrentPlayer().getNickname();
        for (int j = 0; j < clients.get(gameID).size(); j++) {
            if (clients.get(gameID).get(j).getNickname().equals(player)) {
                playClient(getPosition(clients.get(gameID).get(j).getUID(), gameID), gameID);
                break;
            }
        }
    }


    @Override
    public void remove(int gameID, Coordinates[] positions, Long UID) throws RemoteException {
        try {
            masterController.getGameController(gameID).remove(positions);
            clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(new RemoveResponse(gameID,UID));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void turn(int gameID, String[] colors, int column, String nickname, Long UID) throws RemoteException {
        for (int i = 0; i < clients.get(gameID).size(); i++) {
            if (masterController.getGameController(gameID).getPlayers().get(i).getNickname().equals(nickname)) {

                try {
                    Tile[] tiles = new Tile[colors.length];

                    for (int j = 0; j < colors.length; j++) {
                        tiles[j] = new Tile(ColourTile.valueOf(colors[j]));
                    }

                    masterController.getGameController(gameID).turn(tiles, column);
                } catch (EmptySlotException | InvalidPositionsException | InvalidSlotException |
                         NoSpaceInColumnException | SoldOutTilesException | GameAlreadyStarted e) {
                    clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(new TurnResponse(-1,gameID,UID));

                } catch (EndGameException e) {

                    String winner = getWinner(gameID);
                    for (int j = 0; j < clients.get(gameID).size(); j++) {
                        clients.get(gameID).get(i).sendMessage(new EndMessage(winner,gameID,UID));
                    }
                }
                Message message = new TurnResponse(0,gameID,UID);
                clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(message);

                currentPlayer(gameID);


            }
        }
    }







    public String getWinner(int gameID) throws RemoteException {
        return masterController.getGameController(gameID).endGame().getNickname();
    }


    public void sendBoard(int gameID, String nickname, Long UID) throws RemoteException{
        TileSlot[][] board = masterController.getGameController(gameID).getBoard();
        ColourTile[][] colours = new ColourTile[11][11];
        for(int i = 0; i < 11; i++){
            for(int j = 0; j<11 ; j++){
                if(!board[i][j].isFree()){
                    colours[i][j] = board[i][j].getAssignedTile().getColour();
                }
                else{
                    colours[i][j] = ColourTile.FREE;
                }
            }
        }
        clients.get(gameID).get(getPosition(UID, gameID)).sendMessage(new BoardResponse(colours,gameID,UID));

    }

    @Override
    public void playClient(int client, int gameID) throws RemoteException{
        Message message = new WakeMessage(gameID, clients.get(gameID).get(client).getUID());
        clients.get(gameID).get(client).sendMessage(message);
    }

    public int getPosition(Long UID ,int gameID){
            for(int i = 0; i < clients.get(gameID).size();i++){
                if(clients.get(gameID).get(i).getUID().equals(UID)){
                    return i;
                }
            }
        return -1;
    }



}
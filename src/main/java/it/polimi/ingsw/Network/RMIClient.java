package it.polimi.ingsw.Network;

import it.polimi.ingsw.Utils.Coordinates;

import java.io.Serializable;
import java.rmi.RemoteException;

// Classe concreta per il client RMI
public class RMIClient extends Client  implements Serializable {
    private RemoteClient client;
    private final RemoteController server;

    public RMIClient(RemoteController server){
        try{
            this.server = server;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void rmiRegistration() throws Exception {
        client = new RemoteClientImpl(server, this);
        client.registration(this);
        server.addClient(this, client.getGameID());
        server.initGame(client.getGameID());

    }

    public boolean imTCP(){
        return false;
    }




    public int getNum() {
        try{
            return client.getNum();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    };

    public String getNickname() throws RemoteException{
        return client.getNickname();
    };

    public String getString() {
        try{
            return client.getString();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    };

    public Coordinates getTilePosition() throws RemoteException{
        return client.getTilePosition();
    };

    public boolean isMyTurn() throws RemoteException{
        return client.isMyTurn();
    };

    public int getGameID() throws RemoteException{
        return client.getGameID();
    };

    public void sendMessage(String message) {
        try{
            client.sendMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    };

    public int getPositionInArrayServer() throws RemoteException{
        return client.getPositionInArrayServer();
    };

    public void remove() {
        try{
            client.remove();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    };

    public void turn() {
        try{
            client.turn();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    };

    public void printMessage(String message) throws RemoteException{
        client.printMessage(message);
    };

    public void pong() throws RemoteException{
        client.pong();
    };

    public void setMaxPlayers(int maxPlayers) throws RemoteException{
        client.setMaxPlayers(maxPlayers, this);
    };

    public boolean setNickname(String username) throws RemoteException{
        return client.setNickname(username);
    };

    public void setInit(){
        try{
            client.setInit();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFirst(){
        client.setFirst();
    }

    public boolean getInit(){
        return client.getInit();
    }


}

package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.Player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import java.rmi.RemoteException;



public class TCPClient extends Client  implements Serializable {


    private String nickname;
    private Player player;
    PrintWriter out;
    BufferedReader in;
    Gson gson;


    public TCPClient(BufferedReader in, PrintWriter out){
        gson = new Gson();
        this.out = out;
        this.in = in;
    }

    public boolean setNickname(String nickname) throws IOException {
        this.player = new Player(nickname);
        this.nickname = nickname;
        String jsonPlayer = gson.toJson(player);
        sendMessage(jsonPlayer);
        return in.readLine().equals("NicknameOk");
    }

    public TCPClient(String nickname){
        this.player = new Player(nickname);
        this.nickname = nickname;
    }

    public boolean imFirstPlayer() throws IOException {
        String msgTmp;
        msgTmp = (in.readLine());
        return msgTmp.equals("setMaxPlayer");
    }

    // se è il client che sceglie il numero di giocatori
    public boolean initMess() throws IOException {
        return (in.readLine()).equals("initGame");
    }
    //
    public void setNumOfPlayers(int numOfPlayers){
        out.println(numOfPlayers);
        out.flush();
    }


    public boolean imTCP(){
        return true;
    }

    public String getNickname() throws RemoteException{
        return nickname;
    }

    public boolean yourTurn() throws IOException {
        String msgTmp = in.readLine();
        return msgTmp.equals("yourTurn");
    }

    public String removeTCP(Coordinates[] coordinates) throws IOException {
        String removeMess = in.readLine();
        if(removeMess.equals("remove")){
            out.println(coordinates.length);
            for(int i = 0; i < coordinates.length; i++){
                out.println();
                out.flush();
                out.println();
                out.flush();
            }
        }
        String removeException = in.readLine();
        return switch (removeException) {
            case "emptySlot" -> "emptySlot";
            case "invalidSlot" -> "invalidSlot";
            case "removeOk" -> "validSlot";
            default -> "null";
        };
    }


    public String columnInsertion(int column) throws IOException {

        if(in.readLine().equals("columnInsertion")){
            out.println(column);
            out.flush();
            String columnMsg = in.readLine();
            if(columnMsg.equals("wrongColumn")){
                return "wrongColumn";
            }
            if(columnMsg.equals("endgame")){
                return "endgame";
            }
            if(columnMsg.equals("columnFinished")){
                return "columnOk";
            }
        }
        return "null";

    }
}
package it.polimi.ingsw.Network2;

import it.polimi.ingsw.Network2.Messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnect implements Connection{
    private Socket socket;
    private String nickname;
    private final PrintWriter out;
    private BufferedReader in;
    long UID;
    public TCPConnect(Socket socket, Long UID, String nickname) {
        this.socket = socket;
        this.UID = UID;
        this.nickname = nickname;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public Long getUID() {
        return UID;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void sendMessage(Message message){
        out.println(message.toJson());
        out.flush();
    }
}

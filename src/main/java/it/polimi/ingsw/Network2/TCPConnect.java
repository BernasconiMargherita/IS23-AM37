package it.polimi.ingsw.Network2;

import com.google.gson.Gson;
import it.polimi.ingsw.Network2.Messages.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnect implements Connection{
    private Socket socket;
    private final PrintWriter out;
    private BufferedReader in;
    private String nickname;
    public TCPConnect(Socket socket, String nickname) {
        this.socket = socket;
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
    public String getNickname() {
        return nickname;
    }

    public void sendMessage(Message message){
        Gson gson = new Gson();
        String json = gson.toJson(message);
        out.println(json);
    }
}

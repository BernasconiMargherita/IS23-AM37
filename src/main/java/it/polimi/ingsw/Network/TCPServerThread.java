package it.polimi.ingsw.Network;

import it.polimi.ingsw.model.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPServerThread extends Thread{
    private Socket socket = null;
    private Controller controller;

    public TCPServerThread(Socket socket, Controller controller){
        super("TCPServerThread");
        this.socket = socket;
        this.controller = controller;
    }

    public void run(){
        //esempio
        try(
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ){
            String inputLine, outputLine;

            while((inputLine = in.readLine()) != null){
                //gestione richieste del client

            }

            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
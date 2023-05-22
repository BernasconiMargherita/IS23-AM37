package it.polimi.ingsw.Network;

import com.google.gson.Gson;
import it.polimi.ingsw.model.Player.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyServer {
    private static final RemoteController server;

    static {
        try {
            server = new RemoteControllerImpl();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {

        int portNumber;
        String hostName;

        Gson gson = new Gson();


        try{
            FileReader filePort = new FileReader("src/main/resources/ServerPort.json");
            portNumber = gson.fromJson(filePort, Integer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind("RemoteController", server);
        System.out.println("RMI server is running...");















        // Creare un socket server TCP
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Il server TCP è in esecuzione...");

        while (true) {
            // Accettare le connessioni dei client TCP in ingresso
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connessione del client TCP accettata da " + clientSocket.getInetAddress());

            // Gestire le richieste del client TCP
            new Thread(new TcpHandler(clientSocket)).start();
        }
    }




    private static class TcpHandler implements Runnable {
        private final Socket clientSocket;
        private int gameID;

        public TcpHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        public void run() {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                gameID = tcpRegistration(in, out);

                System.out.println("almeno il gameid è stato impostato");




            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        public int tcpRegistration(BufferedReader in, PrintWriter out) throws IOException {


            Gson gson = new Gson();
            String jsonPlayer = in.readLine();
            Player player = gson.fromJson(jsonPlayer, Player.class);
            while(true){
                if(server.nicknameOccupato(player.getNickname())){
                    System.out.println("ilnicknameemoltooccupato");
                    out.println("NicknameOccupato");
                    out.flush();
                }
                else{
                    System.out.println("ilnicknameePOCOOCOCOCOoccupato");
                    break;
                }
                jsonPlayer = in.readLine();
                player = gson.fromJson(jsonPlayer, Player.class);
            }
            out.println("NicknameOk");
            out.flush();
            Client client = new TCPClient(player.getNickname());
            server.setSocket(in , out, client.getNickname());
            int gameID = server.registerPlayer(gson.fromJson(jsonPlayer, Player.class), -2, client );
            server.addClient(client, server.getCurrentGameID());
            System.out.println("finsicelatcpregistration");
            return gameID;
        }
    }

}

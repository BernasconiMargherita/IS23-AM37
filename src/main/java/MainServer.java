import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import  java.net.ServerSocket;

public class MainServer {
    private String hostName;
    private int portNumber;
    private ServerSocket serverSocket;
    Socket clientSocket;

    public MainServer(String[] args)  {

        if(args.length == 2){
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }
         else{
             Gson gson = new Gson();
             try{
                 FileReader fileName = new FileReader("ServerHostName.json");
                 hostName = gson.fromJson(fileName, String.class);
             } catch (FileNotFoundException e) {
                 throw new RuntimeException(e);
             }

             try{
                 FileReader filePort = new FileReader("ServerPort.json");
                 portNumber = gson.fromJson(filePort, Integer.class);
             } catch (FileNotFoundException e) {
                 throw new RuntimeException(e);
             }

        }

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch(IOException e){
            e.printStackTrace();
        }

        clientSocket = null;

        try{
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader in = null;

        try{
            assert clientSocket != null;
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package it.polimi.ingsw.Network.Chat;

import java.rmi.Naming;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try {
            Chat chat = (Chat) Naming.lookup("//localhost/Chat");

            // Avvia un thread separato per ricevere i messaggi
            new Thread(() -> {
                try {
                    while (true) {
                        String[] messages = chat.getMessages();
                        for (String message : messages) {
                            System.out.println(message);
                        }
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                message = scanner.nextLine();
                chat.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



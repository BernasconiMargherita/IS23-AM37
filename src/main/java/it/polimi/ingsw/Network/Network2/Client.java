package it.polimi.ingsw.Network.Network2;

import it.polimi.ingsw.view.gui.Gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8082;
    private static final String RMI_SERVER_URL = "rmi://localhost/Server";

    private TextField textField;
    private TextArea textArea;
    private CommunicationProtocol protocol;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Gui gui= new Gui();
        gui.start(primaryStage);
    }

    private void sendMessage() {
        String message = textField.getText();
        String response = protocol.sendRequest(message);
        textArea.appendText("Server response: " + response + "\n");
        textField.clear();
    }
}


package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.*;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.util.Objects;

public class ConnectionSceneController {
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Label loadingMessage;
    @FXML
    public ProgressIndicator progressIndicator;
    private GuiMaster guiMaster;
    @FXML
    private TextField usernameField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button reSendButton;
    @FXML
    private Label rimettiUsername;

    @FXML
    public void initialize() {
        guiMaster = GuiMaster.getInstance();
        guiMaster.setConnectionSceneController(this);
        createScene();
    }

    public void createScene() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double stageWidth = screenWidth * 0.8;
        double stageHeight = screenHeight * 0.8;

        rootPane.setPrefWidth(stageWidth);
        rootPane.setPrefHeight(stageHeight);
        
        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");
        loadingMessage.setText("Wait for other Players");
    }


    public void initResponse(InitResponse initResponse) {
        Scene scene=rootPane.getScene();
        GuiMaster.setLayout(scene,"/fxml/gameScene.fxml");
    }

    public void loginResponse(LoginResponse loginResponse) {
        guiMaster.getClient().setGameID(loginResponse.getGameID());
    }

    public void usernameError(UsernameError usernameError) {
        guiMaster.getClient().setGameID(usernameError.getGameID());
        for (Node node : rootPane.getChildren()) {
            if (node.isVisible()) {
                node.setVisible(false);
            }
        }
        usernameLabel.setVisible(true);
        usernameField.setVisible(true);
        reSendButton.setVisible(true);
        rimettiUsername.setVisible(true);
        rimettiUsername.setText("Username already taken!");
    }

    public void reSendUsername(MouseEvent mouseEvent) {

        String username = usernameField.getText();

        if (username == null || username.trim().isEmpty()) {
            rimettiUsername.setText("Insert a Username!");
        } else {
            Client client = guiMaster.getClient();
            client.setUsername(username);
            client.sendMessage(new LoginMessage(username,client.getGameID(),client.getUID()));
        }
    }

    public void reFirstResponse(ReFirstResponse reFirstResponse) {
        Scene scene=rootPane.getScene();
        GuiMaster.setLayout(scene,"/fxml/firstConnectionScene.fxml");
    }
}

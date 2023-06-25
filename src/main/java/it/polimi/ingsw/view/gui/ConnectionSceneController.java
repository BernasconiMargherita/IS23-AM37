package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.InitMessage;
import it.polimi.ingsw.Network2.Messages.InitResponse;
import it.polimi.ingsw.Network2.Messages.SetMessage;
import it.polimi.ingsw.Network2.Messages.SetResponse;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import java.util.Objects;

public class ConnectionSceneController {

    public RadioButton TwoPlayers;
    public RadioButton ThreePlayers;
    public RadioButton FourPlayers;
    public AnchorPane rootPane;
    public GridPane gridPane;
    public Label numError;
    public Label loadingMessage;
    public ProgressIndicator progressIndicator;
    private ToggleGroup toggleGroup;
    private GuiMaster guiMaster;

    @FXML
    public void initialize() {
        guiMaster = GuiMaster.getInstance();
        guiMaster.setConnectionSceneController(this);
        createScene();
    }

    public void createScene() {
        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");
        
        Client client=guiMaster.getClient();

        if (client.isFirstPlayer()){
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().add(rowConstraints);
        
        toggleGroup=new ToggleGroup();
        TwoPlayers.setToggleGroup(toggleGroup);
        ThreePlayers.setToggleGroup(toggleGroup);
        FourPlayers.setToggleGroup(toggleGroup);
        
        }else{
            waitingPlayers();
        }
        
    }

    public void selectNumOfPlayers() {
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();

        if (!TwoPlayers.isSelected() && !ThreePlayers.isSelected()&& !FourPlayers.isSelected()) {
            numError.setText("Scegli un numero!");
        }
        else {
            int numOfPlayers = Integer.parseInt(selected.getText());
            Client client = guiMaster.getClient();
            client.sendMessage(new SetMessage(numOfPlayers, client.getGameID()));
        }

    }

    private void waitingPlayers() {
        Client client = guiMaster.getClient();

        if (client.isInitPlayer()){
            client.sendMessage(new InitMessage(client.getGameID()));
        }
        else {
            for (Node node : rootPane.getChildren()) {
                if (node.isVisible()) {
                    node.setVisible(false);
                }
            }
            loadingMessage.setText("Attendi gli altri giocatori");
            loadingMessage.setVisible(true);

            progressIndicator.setProgress(-1);
            progressIndicator.setVisible(true);
        }

    }

    public void setResponse(SetResponse setResponse) {
        waitingPlayers();
    }

    public void initResponse(InitResponse initResponse) {
        Scene scene=gridPane.getScene();
        GuiMaster.setLayout(scene,"/fxml/gameScene.fxml");
    }
}

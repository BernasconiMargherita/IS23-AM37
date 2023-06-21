package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.OkMessage;
import it.polimi.ingsw.Network.Messages.RequestMessage;
import it.polimi.ingsw.Network.Network2.Client;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    @FXML
    public void initialize() {
        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setConnectionSceneController(this);

        createScene();

    }
    public void createScene() {
        //Client client=GuiMaster.getClient();
        //if (client.getFirst==true){
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().add(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().add(rowConstraints);


        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

        toggleGroup=new ToggleGroup();
        TwoPlayers.setToggleGroup(toggleGroup);
        ThreePlayers.setToggleGroup(toggleGroup);
        FourPlayers.setToggleGroup(toggleGroup);

        //}else...
    }
    public void selectNumOfPlayers() {
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();

        if (!TwoPlayers.isSelected() && !ThreePlayers.isSelected()&& !FourPlayers.isSelected()) {

            numError.setText("Scegli un numero!");
        }
        else {

            int numOfPlayers = Integer.parseInt(selected.getText());
            Client client = GuiMaster.getClient();

            Message response= client.sendMessage(new RequestMessage("numOfPlayers: " + numOfPlayers));

            if (response instanceof OkMessage) {
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


    }
}

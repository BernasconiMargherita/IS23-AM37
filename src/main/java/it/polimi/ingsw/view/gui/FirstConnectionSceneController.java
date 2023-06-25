package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.SetMessage;
import it.polimi.ingsw.Network2.Messages.SetResponse;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

import java.util.Objects;

public class FirstConnectionSceneController {

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
        guiMaster.setFirstConnectionSceneController(this);
        createScene();
    }

    public void createScene() {
        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

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

    }

    public void selectNumOfPlayers() {
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
        if (!TwoPlayers.isSelected() && !ThreePlayers.isSelected()&& !FourPlayers.isSelected()) {
            numError.setText("Scegli un numero!");
        }
        else {
            int numOfPlayers = Integer.parseInt(selected.getText());
            Client client = guiMaster.getClient();
            client.sendMessage(new SetMessage(numOfPlayers, client.getGameID(),client.getUID()));
        }
    }

    public void setResponse(SetResponse setResponse) {
        Scene scene=gridPane.getScene();
        GuiMaster.setLayout(scene,"/fxml/connectionScene.fxml");
    }

}

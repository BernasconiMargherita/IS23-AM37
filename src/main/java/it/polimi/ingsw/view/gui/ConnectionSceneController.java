package it.polimi.ingsw.view.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    }
    public void selectNumOfPlayers() {
        RadioButton selected= (RadioButton) toggleGroup.getSelectedToggle();

        if (!TwoPlayers.isSelected() && !ThreePlayers.isSelected()&& !FourPlayers.isSelected()) {

            numError.setText("Scegli un numero!");
        }
        else {

            int numOfPlayers= Integer.parseInt(selected.getText());

            for (Node node:rootPane.getChildren()){
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

package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.model.Tile.ColourTile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class GameSceneController {

    public AnchorPane rootPane;

    public ImageView board;

    public ImageView shelf;
    public ListView<ChatMessage> chatListView;
    public TextField messageTextField;
    public GridPane boardMask;
    public GridPane shelfMask;
    private static final int BOARD_SIZE = 11;
    @FXML
    public void initialize() {

        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setGameSceneController(this);

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");

        double boardWidth = board.getImage().getWidth();
        double boardHeight = board.getImage().getHeight();

        boardMask.setPrefWidth(boardWidth);
        boardMask.setPrefHeight(boardHeight);

        updateBoard(new BoardResponse(new ColourTile[1][1]));
    }
    public void sendChatMessage(ActionEvent actionEvent) {

    }

    public void updateBoard(BoardResponse boardMessage) {
        for (int row = 1; row < BOARD_SIZE-1; row++) {
            for (int col = 1; col < BOARD_SIZE-1; col++) {
                ImageView tile = createTile();
                boardMask.add(tile, col, row);
            }
        }
    }

    private ImageView createTile() {

        String TileImage = Objects.requireNonNull(getClass().getResource("/assets/item tiles/Gatti1.3.png")).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(TileImage));

        imageView.setFitWidth(70);
        imageView.setFitHeight(70);


        imageView.setOnMouseClicked(event -> System.out.println("Tile clicked"));
        return imageView;

    }

    public void removeResponse(RemoveResponse removeResponse) {
    }

    public void turnResponse(TurnResponse turnResponse) {
    }

    public void endGame(EndMessage endGameMessage) {
    }

    public void wakeUp(WakeMessage wakeMessage) {

    }

}

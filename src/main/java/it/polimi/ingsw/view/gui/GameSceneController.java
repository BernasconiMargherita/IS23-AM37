package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Messages.ChatMessage;
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

    @FXML
    public void initialize() {

        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setGameSceneController(this);

        String boardImage = Objects.requireNonNull(getClass().getResource("/assets/boards/livingroom.png")).toExternalForm();
        board.setImage(new Image(boardImage));

        String shelfImage = Objects.requireNonNull(getClass().getResource("/assets/boards/bookshelf.png")).toExternalForm();
        shelf.setImage(new Image(shelfImage));

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");



    }

    public void sendChatMessage(ActionEvent actionEvent) {

    }

    public void updateBoard(BoardMessage boardMessage) {
    }
}

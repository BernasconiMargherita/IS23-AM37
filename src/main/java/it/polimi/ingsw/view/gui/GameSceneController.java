package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.ColourTile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameSceneController {

    public AnchorPane rootPane;

    public ImageView board;

    public ImageView shelf;
    public ListView<ChatMessage> chatListView;
    public TextField messageTextField;
    public GridPane boardMask;
    public GridPane shelfMask;
    public Label tileError;

    private static final int BOARD_SIZE = 11;
    public GridPane hand;
    public GridPane personalCard;
    public GridPane commonTarget2;
    public GridPane commonTarget1;
    public GridPane scoringToken1;
    public GridPane scoringToken2;
    public Label turnText;
    public Button sendHandButton;
    private ColourTile[][] turnBoard;
    private Coordinates[] tileHand=new Coordinates[3];
    private ArrayList<Coordinates> tileHandTmp=new ArrayList<>(3);


    @FXML
    public void initialize() {

        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setGameSceneController(this);

        board.fitWidthProperty().bind(boardMask.widthProperty());

        String boardImage = Objects.requireNonNull(getClass().getResource("/assets/boards/livingroom.png")).toExternalForm();
        board.setImage(new Image(boardImage));

        String backgroundImage = Objects.requireNonNull(getClass().getResource("/assets/misc/sfondo parquet.jpg")).toExternalForm();
        rootPane.setStyle("-fx-background-image: url('" + backgroundImage + "'); -fx-background-size: cover;");
        
        turnText.setVisible(false);
        disableGUI();

    }
    public void sendChatMessage(ActionEvent actionEvent) {

    }

    public void updateBoard(BoardResponse boardMessage) {
        turnBoard = boardMessage.getBoard();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!turnBoard[row][col].equals(ColourTile.FREE)) {
                    ImageView tile = createBoardTile(turnBoard[row][col],row,col);
                    boardMask.add(tile, col, row);
                }
            }
        }

    }

    private ImageView createBoardTile(ColourTile colourTile, int row, int col) {
        String path = "/assets/item tiles/";
        Random random = new Random();
        int randInt = 0;
        while ((randInt < 1)){
            randInt = random.nextInt(4);
        }
        switch (colourTile){

            case CATS -> path+="Gatti1."+randInt+".png";
            case BOOKS -> path+="Libri1."+randInt+".png";
            case GAMES -> path+="Giochi1."+randInt+".png";
            case FRAMES -> path+="Cornici1."+randInt+".png";
            case TROPHIES -> path+="Trofei1."+randInt+".png";
            case PLANTS -> path+="Piante1."+randInt+".png";

        }
        String TileImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(TileImage));

        imageView.setFitWidth(55);
        imageView.setFitHeight(55);

        String finalPath = path;
        imageView.setOnMouseClicked(event -> ChooseTile(row,col, finalPath));
        return imageView;

    }

    private void ChooseTile(int row, int col, String finalPath) {
        ImageView tile=findTile(row,col);
        if ((turnBoard[row + 1][col].equals(ColourTile.FREE)) || (turnBoard[row - 1][col].equals(ColourTile.FREE)) || (turnBoard[row][col + 1].equals(ColourTile.FREE)) || (turnBoard[row][col - 1].equals(ColourTile.FREE))){
            if (tileHandTmp.isEmpty()){
                tileHandTmp.add(new Coordinates(row,col));
                tile.setImage(null);
                ImageView handTile=createShelfTile(finalPath);
                hand.add(handTile,findFirstEmptyColumn(hand),0);
            }
            else if ((tileHandTmp.size()==1)&&( (tileHandTmp.get(0).equals(new Coordinates(row-1,col)))||(tileHandTmp.get(0).equals(new Coordinates(row,col-1))) )){
                tileHandTmp.add(new Coordinates(row,col));
                tile.setImage(null);
                ImageView handTile=createShelfTile(finalPath);
                hand.add(handTile,findFirstEmptyColumn(hand),0);
            }
            else if ((tileHandTmp.size()==2)  &&  ( (tileHandTmp.get(1).equals(new Coordinates(row-1,col)) &&  tileHandTmp.get(0).equals(new Coordinates(row-2,col))) || ( tileHandTmp.get(1).equals(new Coordinates(row,col-1)) && tileHandTmp.get(0).equals(new Coordinates(row,col-2))))){
                tileHandTmp.add(new Coordinates(row,col));
                tile.setImage(null);
                ImageView handTile=createShelfTile(finalPath);
                hand.add(handTile,findFirstEmptyColumn(hand),0);
            }
        }
    }

    private int findFirstEmptyColumn(GridPane hand) {
        int numColumns = 3;

        for (int column = 0; column < numColumns; column++) {
            if (isColumnEmpty(column,hand)) {
                return column;
            }
        }
        return -1;
    }

    private boolean isColumnEmpty(int column, GridPane hand) {
        for (javafx.scene.Node node : hand.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            if (columnIndex != null && columnIndex == column) {
                return false;
            }
        }
        return true;
    }

    private ImageView createShelfTile(String finalPath) {
        String TileImage = Objects.requireNonNull(getClass().getResource(finalPath)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(TileImage));

        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        
        imageView.setOnMouseClicked(event ->addToHand());
        return imageView;
    }

    private void addToHand() {

    }

    private ImageView findTile(int row, int col) {

        ImageView targetImageView = null;
        for (javafx.scene.Node node : boardMask.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                if (node instanceof ImageView) {
                    targetImageView = (ImageView) node;
                    break;
                }
            }
        }
        return targetImageView;
    }

    public void removeResponse(RemoveResponse removeResponse) {
    }

    public void turnResponse(TurnResponse turnResponse) {
    }

    public void endGame(EndMessage endGameMessage) {
    }

    public void wakeUp(WakeMessage wakeMessage) {
        enableGUI();
        Client client=GuiMaster.getInstance().getClient();
        client.sendMessage(new BoardMessage(client.getUsername(), client.getGameID(), client.getUID()));
    }

    private void enableGUI() {
        turnText.setVisible(false);
        boardMask.setDisable(false);
        shelfMask.setDisable(false);
        hand.setDisable(false);
        sendHandButton.setDisable(true);

    }

    private void disableGUI() {
        turnText.setVisible(true);
        boardMask.setDisable(true);
        shelfMask.setDisable(true);
        hand.setDisable(true);
        sendHandButton.setDisable(true);
    }

    public void cardsResponse(CardsResponse cardsResponse) {
        CardPersonalTarget personalTarget = cardsResponse.getCardPersonalTarget();
        showPersonalTarget(personalTarget);
        ArrayList<CardCommonTarget> commonTargets = cardsResponse.getCommonTargets();
        showCommonTargets(commonTargets);

    }

    private void showCommonTargets(ArrayList<CardCommonTarget> commonTargets) {
        String path1= "/assets/commonGoalCards/" +commonTargets.get(0).getCommonType().getId()+".jpg";
        loadCommonTargetImage(path1, commonTarget1);
        loadScoringToken(commonTargets.get(0).getHighestToken(),scoringToken1);

        String path2= "/assets/commonGoalCards/" +commonTargets.get(1).getCommonType().getId()+".jpg";
        loadCommonTargetImage(path2, commonTarget2);
        loadScoringToken(commonTargets.get(1).getHighestToken(),scoringToken2);
    }

    private void loadScoringToken(int highestToken, GridPane scoringToken) {
        String path="/assets/scoring tokens/scoring_"+ highestToken +".jpg";
        String tokenImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setImage(new Image(tokenImage));
        scoringToken.add(imageView,0,0);
    }

    private void loadCommonTargetImage(String path, GridPane commonTarget) {
        String cardImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setImage(new Image(cardImage));
        commonTarget.add(imageView,0,0);
    }

    private void showPersonalTarget(CardPersonalTarget personalTarget) {
        String path = "/assets/personal goal cards/Personal_Goals" + personalTarget.id() + ".png";
        String cardImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);
        imageView.setImage(new Image(cardImage));
        personalCard.add(imageView,0,0);
    }

    public void sendHand(ActionEvent actionEvent) {
    }
}

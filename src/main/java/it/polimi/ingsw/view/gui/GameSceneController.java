package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.Messages.*;
import it.polimi.ingsw.Utils.Coordinates;
import it.polimi.ingsw.model.CommonCards.CardCommonTarget;
import it.polimi.ingsw.model.PersonalCards.CardPersonalTarget;
import it.polimi.ingsw.model.Tile.ColourTile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class GameSceneController {
    private static final int SHELF_ROWS = 6;
    private static final int SHELF_COLUMNS = 5;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public ImageView board;
    @FXML
    public ImageView shelf;
    @FXML
    public ListView<ChatMessage> chatListView;
    @FXML
    public TextField messageTextField;
    @FXML
    public GridPane boardMask;
    @FXML
    public GridPane shelfMask;
    @FXML
    public Label tileError;

    private static final int BOARD_SIZE = 11;
    @FXML
    public GridPane hand;
    @FXML
    public GridPane personalCard;
    @FXML
    public GridPane commonTarget2;
    @FXML
    public GridPane commonTarget1;
    @FXML
    public GridPane scoringToken1;
    @FXML
    public GridPane scoringToken2;
    @FXML
    public Label turnText;
    @FXML
    public Button sendHandButton;
    @FXML
    public Label box1;
    @FXML
    public Label box2;
    @FXML
    public Label box3;
    public Label[] boxArray;
    @FXML
    public Button column1;
    @FXML
    public Button column2;
    @FXML
    public Button column3;
    @FXML
    public Button column4;
    @FXML
    public Button column5;
    public GridPane endGameToken;
    private ColourTile[][] turnBoard;
    private final ArrayList<Coordinates> tileHand=new ArrayList<>();
    private final ArrayList<Coordinates> tileHandTmp=new ArrayList<>();


    @FXML
    public void initialize() {

        GuiMaster guiMaster = GuiMaster.getInstance();
        guiMaster.setGameSceneController(this);

        board.fitWidthProperty().bind(boardMask.widthProperty());
        boxArray=new Label[]{box1, box2, box3};

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
        int[] tokens = boardMessage.getCommonTokens();
        loadScoringToken(tokens[0],scoringToken1);
        loadScoringToken(tokens[1],scoringToken2);
        if (boardMessage.isEndGameTokenTaken()) emptyGridPane(endGameToken);
        else loadEndGameToken();
        turnBoard = boardMessage.getBoard();
        emptyGridPane(boardMask);
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (!turnBoard[row][col].equals(ColourTile.FREE)) {
                    ImageView tile = createBoardTile(turnBoard[row][col],row,col);
                    boardMask.add(tile, col, row);
                }
            }
        }

    }

    private void loadEndGameToken() {
        String path="/assets/scoring tokens/end game.jpg";
        String EndGameTokenImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(EndGameTokenImage));
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);

        endGameToken.add(imageView,0,0);
    }

    private ImageView createBoardTile(ColourTile colourTile, int row, int col) {
        String path = "/assets/item tiles/";
        switch (colourTile){

            case CATS -> path+="Gatti1.1.png";
            case BOOKS -> path+="Libri1.1.png";
            case GAMES -> path+="Giochi1.1.png";
            case FRAMES -> path+="Cornici1.1.png";
            case TROPHIES -> path+="Trofei1.1.png";
            case PLANTS -> path+="Piante1.1.png";

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
        ImageView tile = findTile(row, col);
        boolean allAdjacencyMatch = true;

         if ((turnBoard[row + 1][col].equals(ColourTile.FREE)) || (turnBoard[row - 1][col].equals(ColourTile.FREE)) || (turnBoard[row][col + 1].equals(ColourTile.FREE)) || (turnBoard[row][col - 1].equals(ColourTile.FREE))) {

             if (tileHandTmp.size()==0){
                 tileHandTmp.add(new Coordinates(row, col));
                 tile.setImage(null);
                 ImageView handTile = createHandTile(finalPath);
                 hand.add(handTile, findFirstEmptyColumn(hand), 0);
             } else {
                 int firstRow = tileHandTmp.get(0).getRow();
                 int firstCol = tileHandTmp.get(0).getColumn();

                 for (int i = 1; i < tileHandTmp.size(); i++) {
                     Coordinates currentCoordinate = tileHandTmp.get(i);
                     int currentRow = currentCoordinate.getRow();
                     int currentCol = currentCoordinate.getColumn();

                     if (!((currentRow == firstRow && currentCol == firstCol + i) ||
                             (currentCol == firstCol && currentRow == firstRow + i))) {
                         allAdjacencyMatch = false;
                         break;
                     }
                 }
                 if (allAdjacencyMatch) {

                     tileHandTmp.add(new Coordinates(row, col));
                     tile.setImage(null);
                     ImageView handTile = createHandTile(finalPath);
                     hand.add(handTile, findFirstEmptyColumn(hand), 0);
                 }
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

    private ImageView createHandTile(String finalPath) {
        String TileImage = Objects.requireNonNull(getClass().getResource(finalPath)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(TileImage));

        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        int pos=tileHandTmp.size()-1;

        imageView.setOnMouseClicked(event ->addToHand(pos));

        return imageView;
    }

    private void addToHand(int i) {
        Coordinates coordinates=tileHandTmp.get(i);
        tileHand.add(coordinates);
        Label label = boxArray[i];
        label.setText(String.valueOf(tileHand.size()));
        if(tileHand.size()==tileHandTmp.size()) sendHandButton.setVisible(true);
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
        column1.setVisible(true);
        column2.setVisible(true);
        column3.setVisible(true);
        column4.setVisible(true);
        column5.setVisible(true);
    }

    public void turnResponse(TurnResponse turnResponse) {
        if (turnResponse.getStatus()==0) {

            updateShelf(turnResponse);
            disableGUI();

        }
        else {
            column1.setVisible(true);
            column2.setVisible(true);
            column3.setVisible(true);
            column4.setVisible(true);
            column5.setVisible(true);
        }

    }

    private void updateShelf(TurnResponse turnResponse) {
        ColourTile[][] turnShelf = turnResponse.getShelf();
        emptyGridPane(shelfMask);
        for (int row = SHELF_ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < SHELF_COLUMNS; col++) {
                int adjustedRow = SHELF_ROWS - 1 - row;

                if (!turnShelf[adjustedRow][col].equals(ColourTile.FREE)) {
                    ImageView tile = createShelfTile(turnShelf[adjustedRow][col], adjustedRow, col);
                    shelfMask.add(tile, col, row);
                }
            }
        }
    }


    private void emptyGridPane(GridPane gridPane) {
        gridPane.getChildren().clear();
    }

    private ImageView createShelfTile(ColourTile colourTile, int row, int col) {
        String path = "/assets/item tiles/";
        switch (colourTile){

            case CATS -> path+="Gatti1.1.png";
            case BOOKS -> path+="Libri1.1.png";
            case GAMES -> path+="Giochi1.1.png";
            case FRAMES -> path+="Cornici1.1.png";
            case TROPHIES -> path+="Trofei1.1.png";
            case PLANTS -> path+="Piante1.1.png";

        }
        String TileImage = Objects.requireNonNull(getClass().getResource(path)).toExternalForm();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(TileImage));

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        return imageView;
    }

    public void endGame(EndMessage endGameMessage) {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle("Game Over");

        Label winnerLabel = new Label("The winner is: " + endGameMessage.getWinner());

        Button closeButton = new Button("Close game");
        closeButton.setOnAction(event -> {
            closeConnection();
            modalStage.close();
        });

        VBox modalVBox = new VBox(winnerLabel, closeButton);
        modalVBox.setStyle("-fx-padding: 20px; -fx-spacing: 10px");

        Scene modalScene = new Scene(modalVBox);

        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    private void closeConnection() {
        GuiMaster.getInstance().closeConnection();
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
    }

    private void disableGUI() {
        turnText.setVisible(true);
        boardMask.setDisable(true);
        shelfMask.setDisable(true);
        hand.setDisable(true);
        tileHand.clear();
        emptyGridPane(hand);
        for (Label box:boxArray) {
            box.setText("");
        }
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


        String path2= "/assets/commonGoalCards/" +commonTargets.get(1).getCommonType().getId()+".jpg";
        loadCommonTargetImage(path2, commonTarget2);

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
        sendHandButton.setVisible(false);
        tileHandTmp.clear();
        Client client = GuiMaster.getInstance().getClient();
        client.sendMessage(new RemoveMessage(tileHand, client.getGameID(), client.getUID(), client.getUsername()));
    }

    public void sendTurn(MouseEvent mouseEvent) {
        column1.setVisible(false);
        column2.setVisible(false);
        column3.setVisible(false);
        column4.setVisible(false);
        column5.setVisible(false);

        Button clickedButton = (Button) mouseEvent.getSource();
        String buttonText = clickedButton.getText();
        int column = Integer.parseInt(buttonText);
        column=column-1;

        Client client = GuiMaster.getInstance().getClient();
        String[] colours=createColoursArray();
        client.sendMessage(new TurnMessage(client.getGameID(), client.getUID(), column, client.getUsername(), colours));
    }

    private String[] createColoursArray() {
        String[] colours=new String[tileHand.size()];

        for (int i=0;i<colours.length;i++){
            colours[i]= String.valueOf(turnBoard[tileHand.get(i).getRow()][tileHand.get(i).getColumn()]);
        }

        return colours;
    }
}

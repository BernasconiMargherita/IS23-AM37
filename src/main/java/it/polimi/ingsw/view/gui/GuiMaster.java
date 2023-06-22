package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.Network2.Client;
import it.polimi.ingsw.Network2.ClientManager;
import it.polimi.ingsw.Network2.Messages.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GuiMaster extends ClientManager {

    private LoginSceneController loginSceneController;
    private GameSceneController gameSceneController;
    private ConnectionSceneController connectionSceneController;

    private static GuiMaster instance = null;
    private static Client client;


    public static <T> T setLayout(Scene scene, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(GuiMaster.class.getResource(path));
        Parent root = loader.load();
        scene.setRoot(root);
        return loader.getController();
    }


    public static GuiMaster getInstance() {
        if (instance==null){
            instance=new GuiMaster();
        }
        return instance;
    }

    public void setLoginSceneController(LoginSceneController loginSceneController) {
        this.loginSceneController = loginSceneController;
    }

    public void setGameSceneController(GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController;
    }

    public static void setClient(Client client) {
        GuiMaster.client = client;
    }

    public static Client getClient() {
        return client;
    }


    public void setConnectionSceneController(ConnectionSceneController connectionSceneController) {
        this.connectionSceneController=connectionSceneController;
    }


    @Override
    public void loginResponse(LoginResponse loginResponse) {
        Platform.runLater(() ->
                loginSceneController.loginResponse(loginResponse));
    }

    @Override
    public void initResponse(InitResponse initResponse) {
        Platform.runLater(() ->
                connectionSceneController.initResponse(initResponse));
    }

    @Override
    public void updateBoard(BoardResponse boardMessage) {
        Platform.runLater(() ->
                gameSceneController.updateBoard(boardMessage));
    }

    @Override
    public void removeResponse(RemoveResponse removeResponse) {
        Platform.runLater(() ->
                gameSceneController.removeResponse(removeResponse));
    }

    @Override
    public void turnResponse(TurnResponse turnResponse) {
        Platform.runLater(() ->
                gameSceneController.turnResponse(turnResponse));
    }

    @Override
    public void endGame(EndMessage endGameMessage) {
        Platform.runLater(() ->
                gameSceneController.endGame(endGameMessage));
    }

    @Override
    public void wakeUp(WakeMessage wakeMessage) {
        Platform.runLater(() ->
                gameSceneController.wakeUp(wakeMessage));
    }
}

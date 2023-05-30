module it.polimi.ingsw {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.rmi;


    opens it.polimi.ingsw.model.Board to com.google.gson;
    opens it.polimi.ingsw.Utils to com.google.gson;
    opens it.polimi.ingsw.model.Player to com.google.gson;
    opens it.polimi.ingsw.model.Tile to com.google.gson;
    opens it.polimi.ingsw.view.gui to javafx.fxml;

    exports it.polimi.ingsw.Network to java.rmi;

    exports it.polimi.ingsw.model.PersonalCards to com.google.gson;
    exports it.polimi.ingsw.model.Tile to com.google.gson;
    exports it.polimi.ingsw.model.Player to com.google.gson;
    exports it.polimi.ingsw.Utils ;
    exports it.polimi.ingsw.Network.Chat to java.rmi;
    exports it.polimi.ingsw.view.gui;


}
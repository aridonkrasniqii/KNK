package components;


import helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controllers.RoomDetailsController;

import java.io.File;
import java.net.URL;

public class RoomDetails {
    public Node singleComponent(Rooms room, long daysToStay, EventHandler<ActionEvent> handler) throws Exception{
        FXMLLoader loader=new FXMLLoader();
        URL url=new File("src/views/room-details.fxml").toURI().toURL();
        loader.setLocation(url);
        Pane pane = loader.load();
        RoomDetailsController controller=loader.getController();
        controller.getRoomToShow(room,daysToStay);
        controller.setRemoveButtonAction(handler);

        return pane;
    }
}
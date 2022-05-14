package controllers;

import helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import controllers.LanguageController;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomDetailsController extends LanguageController {
    private Rooms room;
    @FXML
    private Label roomNumber;
    @FXML private Label roomFloor;
    @FXML private Label price;
    @FXML private Label roomType;
    @FXML private Label nrOfBeds;
    @FXML private Button removeButton;
    @FXML private Label id;
    @FXML private Label floor;
    @FXML private Label beds;
    @FXML private Label toPay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
    }

    public void getRoomToShow(Rooms room, long daysToStay){
        id.setText(String.valueOf(room.getRoom_number()));
        floor.setText(String.valueOf(room.getFloor_number()));
        beds.setText(String.valueOf(room.getBed_number()));
        toPay.setText(String.valueOf(room.getPrice()*daysToStay));
        roomType.setText(capitalize(room.getRoom_type()));
    }
    
    public void setRemoveButtonAction(EventHandler<ActionEvent> eventHandler){
        removeButton.setOnAction(eventHandler);
    }
    
    private String capitalize(String word){
        String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
        return cap;
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        roomNumber.setText(langBundle.getString("roomNr"));
        roomFloor.setText(langBundle.getString("floorNr"));
        nrOfBeds.setText(langBundle.getString("bedNumber"));
        price.setText(langBundle.getString("price"));
    }
}

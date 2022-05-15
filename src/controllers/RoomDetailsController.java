package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoomDetailsController extends LanguageController {

    @FXML
    private Label beds;

    @FXML
    private Button bookButton;

    @FXML
    private Label floor;

    @FXML
    private Label id;

    @FXML
    private Label nrOfBeds;

    @FXML
    private Label price;

    @FXML
    private Label roomFloor;

    @FXML
    private Label roomNumber;

    @FXML
    private Label roomType;

    @FXML
    void onActionBook(ActionEvent event) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("../views/reservation.fxml"));
//        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(parent);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("LAMALE Hotel");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
    }

    public void getRoomToShow(Rooms room, long daysToStay) {
//        id.setText(String.valueOf(room.getRoom_number()));
//        floor.setText(String.valueOf(room.getFloor_number()));
//        beds.setText(String.valueOf(room.getBed_number()));
//        roomType.setText(capitalize(room.getRoom_type()));
    }

    public void setBookButtonAction(EventHandler<ActionEvent> eventHandler) {
//        bookButton.setOnAction(eventHandler);
    }

    private String capitalize(String word) {
//        String cap = word.substring(0, 1).toUpperCase() + word.substring(1);
//        return cap;
        return "";
    }

    public void setData(String roomNr, String floorNr, String bedNr, double price) {

        //

    }

    private String setImagePath(int roomNr) {
        return "../style/images/Dhoma" + roomNr;
    }

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        roomNumber.setText(langBundle.getString("roomNr"));
        roomFloor.setText(langBundle.getString("floorNr"));
        nrOfBeds.setText(langBundle.getString("bedNumber"));
        price.setText(langBundle.getString("price"));
    }

}
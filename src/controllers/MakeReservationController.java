package controllers;

import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MakeReservationController implements Initializable {


    @FXML
    private TextField personalNumberField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField totalField;

    @FXML
    private ChoiceBox adultsNumber;
    @FXML
    private ChoiceBox childrensNumber;

    @FXML
    private TextField checkInDate;
    @FXML
    private TextField checkOutDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void onReserveAction(ActionEvent e) throws Exception {
        // TODO: store payment in db
        // TODO: store reservation in db

    }


    @FXML
    private void onCancleAction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/main-view.fxml"));
        Parent parent = loader.load();
        MainViewController controller = loader.getController();
        controller.setView(MainViewController.RESERVATION_ROOM_VIEW);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }





    public void setData(String checkIn, String checkOut, double price) {
        ObservableList<String> numOfAdults = FXCollections.observableArrayList("1","2","3");
        ObservableList<String> numOfChildrens = FXCollections.observableArrayList("1","2","3");
        adultsNumber.setItems(numOfAdults);
        childrensNumber.setItems(numOfChildrens);

        if (checkIn == null || checkOut == null) {
            checkInDate.setEditable(true);
            checkOutDate.setEditable(true);
            checkInDate.setDisable(false);
            checkOutDate.setDisable(false);
            totalField.setText(Double.toString(price));
        }else {
            checkInDate.setText(checkIn);
            checkOutDate.setText(checkOut);
            totalField.setText(Double.toString(price));

        }
    }




}

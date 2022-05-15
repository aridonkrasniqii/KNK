package controllers;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private Label totalField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setData(String firstName, String lastName, double total) {
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
//        totalField.setText(Double.toString(total) + " Euro");
    }


    @FXML
    private void onReserveAction(ActionEvent e ) throws Exception {
        // TODO: store reservation in db

    }


    @FXML
    private void onCancleAction(ActionEvent e ) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/main-view.fxml"));
        Parent parent = loader.load();
        MainViewController controller = loader.getController();
        controller.setView(MainViewController.RESERVATION_ROOM_VIEW);
        Stage stage = (Stage) ((Node) e.getSource()) .getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

}

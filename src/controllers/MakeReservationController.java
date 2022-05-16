package controllers;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.DateHelper;
import helpers.Reservation;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import helpers.SessionManager;
import models.Payments;
import repositories.PaymentRepository;
import repositories.ReservationRepository;

import java.net.URL;
import java.util.Date;
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

    private static int room_number;


    @FXML
    private void onReserveAction(ActionEvent e) throws Exception {

        // TODO: store payment in db
        int guest_id = SessionManager.user.getId();
        int staff_id = 1;
        double total = Double.parseDouble(totalField.getText());
        String payment_method = "";
        int is_payed = 0;

        Payments payments = new Payments(0, guest_id, staff_id, total, payment_method, is_payed, null);


        Payments createdPayment = PaymentRepository.create(payments);
        if(createdPayment == null){
            ErrorPopupComponent.show("Error occured");
            return;
        }


        int adults = Integer.parseInt(adultsNumber.getValue().toString());
        int children = Integer.parseInt(childrensNumber.getValue().toString());
        String checkIn = checkInDate.getText();
        String checkOut = checkOutDate.getText();
        int payment_id = createdPayment.getId();

        Reservation reservation = new Reservation(0 , guest_id, room_number ,new Date(), DateHelper.fromSql(checkIn) , DateHelper.fromSql(checkOut), adults, children, payment_id );

        Reservation createdReservation = ReservationRepository.create(reservation);

        if(createdReservation == null) ErrorPopupComponent.show("Error occured 2");


        // load reservation completed
        SuccessPopupComponent.show("Reservation created ", "Created");

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


    public void setData(String checkIn, String checkOut, double price, int room_number) {
        ObservableList<String> numOfAdults = FXCollections.observableArrayList("1", "2", "3");
        ObservableList<String> numOfChildrens = FXCollections.observableArrayList("1", "2", "3");
        adultsNumber.setItems(numOfAdults);
        childrensNumber.setItems(numOfChildrens);
        this.room_number = room_number;
        if (checkIn == null || checkOut == null) {
            checkInDate.setEditable(true);
            checkOutDate.setEditable(true);
            checkInDate.setDisable(false);
            checkOutDate.setDisable(false);
            totalField.setText(Double.toString(price));
        } else {
            checkInDate.setText(checkIn);
            checkOutDate.setText(checkOut);
            totalField.setText(Double.toString(price));

        }
    }


}

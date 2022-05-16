package controllers;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Reservation;
import helpers.SessionManager;
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
import models.Payments;
import processor.DateHelper;
import repositories.PaymentRepository;
import repositories.ReservationRepository;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MakeReservationController implements Initializable {
    private int roomNumber, guestId, staffId, isBillPayed, adultsNum, childrenNum, paymentId;
    private String paymentMethod, roomType, checkInDate, checkOutDate;
    private double totalBill;

    @FXML
    private TextField personalNumberField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField totalField;

    @FXML
    private ChoiceBox adultsNumberField;
    @FXML
    private ChoiceBox childrensNumberField;

    @FXML
    private TextField checkInDateField;
    @FXML
    private TextField checkOutDateField;


    private ObservableList<String> numOfAdults = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> numOfChildrens = FXCollections.observableArrayList("1", "2", "3");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adultsNumberField.setItems(numOfAdults);
        childrensNumberField.setItems(numOfChildrens);
    }


    public void setData(int roomNumber, String checkInDate, String checkOutDate, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.guestId = SessionManager.user.getId();
        this.staffId = 1; // default
        this.totalBill = price; // calculate totalbill
        this.paymentMethod = "cach"; // cach default
        this.isBillPayed = 0; // default 0
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;


        checkInDateField.setText(checkInDate);
        checkOutDateField.setText(checkOutDate);
        totalField.setText(Double.toString(price));

    }


    @FXML
    private void onReserveAction(ActionEvent e) throws Exception {

        // first we create the payment object
        Payments payments = new Payments(0, guestId, staffId, totalBill, paymentMethod, isBillPayed, null);


        // than we store payment into database
        Payments createdPayment = PaymentRepository.create(payments);
        if (createdPayment != null) {
            ErrorPopupComponent.show("Payment error occurred");
            return;
        }

        this.adultsNum = toInt(adultsNumberField.getValue().toString());
        this.childrenNum = toInt(childrensNumberField.getValue().toString());
        this.paymentId = createdPayment.getId();


        Reservation reservation = new Reservation(0, guestId, roomNumber, new Date(),
                DateHelper.fromSqlDate(checkInDate.toString()), DateHelper.fromSqlDate(checkInDate.toString())
                , adultsNum, childrenNum, paymentId);


        if (ReservationRepository.create(reservation) != null) {
            ErrorPopupComponent.show("Reservation error occurred");
            return;
        }

        // if everything goes well this pop up will be shown
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

    private static int toInt(String num) {
        return Integer.parseInt(num);
    }


}

package controllers;

import helpers.Rooms;
import helpers.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PayPaymentsController implements Initializable {


    // FIXME: figure out for what is service table

    @FXML
    private Label totalValue;

    @FXML
    private RadioButton cachButton;

    @FXML
    private RadioButton creditCardButton;

    @FXML
    private RadioButton giftCuponButton;

    private ToggleGroup payMethodGroup;


    @FXML
    private TableView<Rooms> roomTableView;

    @FXML
    private TableColumn<Rooms, Integer> roomNumberCol;

    @FXML
    private TableColumn<Rooms, String> roomTypeCol;

    @FXML
    private TableColumn<Rooms, Double> roomPriceCol;


    @FXML
    private TableView<Services> serviceTableView;


    @FXML
    private TableColumn<Services, String> serviceNameCol;


    @FXML
    private TableColumn<Services, Double> servicePriceCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            bindPaymentMethod();
            initializeRooms();
            initializeServices();
        }catch(Exception ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void onPayAction(ActionEvent e) throws Exception {
        RadioButton payM = (RadioButton) payMethodGroup.getSelectedToggle();
        String payMethod = payM.getText();

    }



    private void bindPaymentMethod() {
        cachButton.setToggleGroup(payMethodGroup);
        creditCardButton.setToggleGroup(payMethodGroup);
        giftCuponButton.setToggleGroup(payMethodGroup);
    }

    private void initializeRooms() {
        this.roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        this.roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        this.roomPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    private void initializeServices(){
        // TODO:
        this.serviceNameCol.setCellValueFactory(new PropertyValueFactory<>(""));
        this.servicePriceCol.setCellValueFactory(new PropertyValueFactory<>(""));
    }
    private ArrayList<Services> loadServices() {
        //TODO:
        return null;
    }


    // TODO:
//    private void loadDate( ..... ) {
//
//    }


}

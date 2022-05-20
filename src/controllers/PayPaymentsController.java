package controllers;

import helpers.Rooms;
import helpers.Service_Type;
import helpers.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repositories.ServicesTypeRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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


    public void initRadio() {
        cachButton.setToggleGroup(payMethodGroup);
        creditCardButton.setToggleGroup(payMethodGroup);
        giftCuponButton.setToggleGroup(payMethodGroup);

        RadioButton method = (RadioButton) payMethodGroup.getSelectedToggle();
        String meth = method.getText();
    }

    @FXML
    private TableView<Rooms> roomTableView;

    @FXML
    private TableColumn<Rooms, Integer> roomNumberCol;

    @FXML
    private TableColumn<Rooms, String> roomTypeCol;

    @FXML
    private TableColumn<Rooms, Double> roomPriceCol;


    @FXML
    private TableView<Service_Type> serviceTableView;


    @FXML
    private TableColumn<Service_Type, String> serviceNameCol;


    @FXML
    private TableColumn<Service_Type, Double> servicePriceCol;


    ObservableList<Rooms> roomsObservableList;
    ObservableList<Service_Type> servicesObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            bindPaymentMethod();
            initializeRooms();
            initializeServices();
            servicesObservableList = FXCollections.observableArrayList(loadServices());
            serviceTableView.setItems(servicesObservableList);
        } catch (Exception ex) {
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


    private void initializeServices() {
        // TODO:
        this.serviceNameCol.setCellValueFactory(new PropertyValueFactory<>(""));
        this.servicePriceCol.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    private ArrayList<Service_Type> loadServices() throws Exception {
        ArrayList<Service_Type> services = ServicesTypeRepository.findAll();
        if (services != null) return services;
        return null;
    }


    public void loadRoomData(Rooms room) throws Exception {
        ArrayList<Rooms> r = new ArrayList<>(List.of(room));
        roomsObservableList = FXCollections.observableArrayList(r);
    }


}

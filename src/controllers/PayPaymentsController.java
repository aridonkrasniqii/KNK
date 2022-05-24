package controllers;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import models.Service_Type;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Payments;
import repositories.PaymentRepository;
import repositories.ServicesTypeRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class PayPaymentsController implements Initializable {

    private double priceInvoice;
    private ToggleGroup toggle;
    @FXML
    private Label userCredentials;

    @FXML
    private Label userId;

    @FXML
    private Label totalValue;

    @FXML
    private RadioButton cachButton;

    @FXML
    private RadioButton creditCardButton;

    @FXML
    private RadioButton giftCuponButton;



    @FXML
    private TableView<Service_Type> serviceTableView;


    @FXML
    private TableColumn<Service_Type, String> serviceNameCol;


    @FXML
    private TableColumn<Service_Type, Double> servicePriceCol;

    ObservableList<Service_Type> servicesObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            bindRadio();
            initializeServices();
            servicesObservableList = FXCollections.observableArrayList(loadServices());
            serviceTableView.setItems(servicesObservableList);

            serviceTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            serviceTableView.getSelectionModel().selectedItemProperty().addListener((observe, old, _new) -> {
                if (_new != null) {
                    try {
                        renderPrice(_new);
                    } catch (Exception e) {
                        ErrorPopupComponent.show(e);
                    }
                }
            });

        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    public void renderPrice(Service_Type service) {
        totalValue.setText(Double.toString(priceInvoice));
        double price = Double.parseDouble(totalValue.getText()) + service.getPrice();
        totalValue.setText(Double.toString(price));
    }

    private void bindRadio() {
        toggle = new ToggleGroup();
        cachButton.setToggleGroup(toggle);
        creditCardButton.setToggleGroup(toggle);
        giftCuponButton.setToggleGroup(toggle);
    }

    @FXML
    private void onPayAction(ActionEvent e) throws Exception {

        int user_id = SessionManager.user.getId();
        int staff_id = 1;
        double price = Double.parseDouble(totalValue.getText());
        String payment_method = null;
        try {
            RadioButton button = (RadioButton)toggle.getSelectedToggle();
            payment_method = button.getText();
        }catch(Exception ex) {
            ErrorPopupComponent.show("You must specify payment method");
            return;
        }
        if(payment_method == null) {
            ErrorPopupComponent.show("You must specify payment method ");
            return;
        }

        int is_payed = 1;
        Date date = new Date();

        Payments model = new Payments(1, user_id, staff_id, price, payment_method, is_payed,date);
        if(PaymentRepository.update(model) != null ) {
            SuccessPopupComponent.show("Invoice is successfully payed", "Payed :) ");
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/main-view.fxml"));
            Parent parent = loader.load();
            MainViewController controller = loader.getController();
            controller.setView(MainViewController.PAYMENT_VIEW);
            stage.setScene(new Scene(parent));
            return;
        }

        ErrorPopupComponent.show("Payment failed !");

    }


    private void initializeServices() {
        this.serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        this.servicePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private ArrayList<Service_Type> loadServices() throws Exception {
        return ServicesTypeRepository.getAvailable();
    }


    public void loadPrice(Double price) throws Exception {
        userCredentials.setText(SessionManager.user.getName());
        userId.setText(Integer.toString(SessionManager.user.getId()));
        this.priceInvoice = price;
        totalValue.setText(Double.toString(price));
    }


}

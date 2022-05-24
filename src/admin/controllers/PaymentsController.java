package admin.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.PaymentModel;
import repositories.PaymentsModelRepository;
import utilities.I18N;

public class PaymentsController implements Initializable{
  @FXML
  private AnchorPane paymentsPane;
  @FXML
  private TableView<PaymentModel> paymentsTableView;
  @FXML
  private TableColumn<PaymentModel, Integer> payment_id;
  @FXML
  private TableColumn<PaymentModel, String> firstname;
  @FXML
  private TableColumn<PaymentModel, String> lastname;
  @FXML
  private TableColumn<PaymentModel, Date> date;
  @FXML
  private TableColumn<PaymentModel, Double> price1;
  @FXML
  private TableColumn<PaymentModel, Integer> isPayed;
  @FXML
  private DatePicker paymentDtPickerFilter;
  @FXML
  private Button paymentFilterBtn;
  @FXML
  private Button refreshBtn;
  @FXML
  private Label paymentsLbl;
  ObservableList<PaymentModel> paymentsModel;



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      initializePayments();
      paymentsModel = FXCollections.observableArrayList(loadPayments());
      paymentsTableView.setItems(paymentsModel);
    } catch (Exception ex) {
      System.out.println(ex);
    }


    firstname.textProperty().bind(I18N.createStringBinding("nameField"));
    lastname.textProperty().bind(I18N.createStringBinding("last_name"));
    date.textProperty().bind(I18N.createStringBinding("data"));
    price1.textProperty().bind(I18N.createStringBinding("priceCol"));
    isPayed.textProperty().bind(I18N.createStringBinding("isPayed"));
    paymentFilterBtn.textProperty().bind(I18N.createStringBinding("search"));
    refreshBtn.textProperty().bind(I18N.createStringBinding("refreshBtn"));
    paymentsLbl.textProperty().bind(I18N.createStringBinding("paymentsBtn"));

  }

  public void initializePayments() {
    this.payment_id.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
    this.firstname.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.lastname.setCellValueFactory(new PropertyValueFactory<>("username"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.price1.setCellValueFactory(new PropertyValueFactory<>("price"));
    this.isPayed.setCellValueFactory(new PropertyValueFactory<>("ispayed"));
  }

  public ArrayList<PaymentModel> loadPayments() throws Exception {
    PaymentsModelRepository repository = new PaymentsModelRepository();
    return repository.findAll();
  }


  @FXML
  private void searchOnPayment(ActionEvent e ) throws Exception {

    String date = paymentDtPickerFilter.getValue().toString();
    if(paymentDtPickerFilter == null)
      return;


    ArrayList<PaymentModel> payments = PaymentsModelRepository.filterPayments(date);
    paymentsModel = FXCollections.observableArrayList(payments);
    paymentsTableView.setItems(paymentsModel);
    paymentsTableView.refresh();
  }

  @FXML
  private void onRefreshAction(ActionEvent e ) throws Exception {
    if(loadPayments() == null ) return;
    paymentsModel = FXCollections.observableArrayList(loadPayments());
    paymentsTableView.setItems(paymentsModel);
    paymentsTableView.refresh();
  }


}

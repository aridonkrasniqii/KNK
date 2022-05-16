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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.view.PaymentModel;
import repositories.PaymentsModelRepository;

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
  }

  public void initializePayments() {
    this.payment_id.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
    this.firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    this.lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
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

    System.out.println("Date : " + date);
    ArrayList<PaymentModel> payments = PaymentsModelRepository.filterPayments(date);
    paymentsModel = FXCollections.observableArrayList(payments);
    paymentsTableView.setItems(paymentsModel);
    paymentsTableView.refresh();
  }

  @FXML
  private void onRefreshAction(ActionEvent e ) throws Exception {
    paymentsModel = FXCollections.observableArrayList(loadPayments());
    paymentsTableView.setItems(paymentsModel);
    paymentsTableView.refresh();
  }


}

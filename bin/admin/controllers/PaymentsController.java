package admin.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.view.PaymentModel;
import repositories.PaymentsRepository;

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
  public ObservableList<PaymentModel> paymentsObservableList = null;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      initializePayments();
      ObservableList<PaymentModel> paymentsModel = FXCollections.observableArrayList(loadPayments());
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

  public ArrayList<PaymentModel> loadPayments() throws SQLException {
    PaymentsRepository repository = new PaymentsRepository();
    return repository.findAll();
  }



}

package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helpers.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.view.PaymentModel;
import repositories.PaymentsModelRepository;

public class PaymentsController implements Initializable {

	@FXML
	private TableView<PaymentModel> paymentsTableView;
	@FXML
	private TableColumn<PaymentModel, Integer> paymentIdCol;
	@FXML
	private TableColumn<PaymentModel, String> firstNameCol;
	@FXML
	private TableColumn<PaymentModel, String> lastNameCol;
	@FXML
	private TableColumn<PaymentModel, Date> dateCol;
	@FXML
	private TableColumn<PaymentModel, Double> priceCol;
	@FXML
	private DatePicker dateFilter;
	ObservableList<PaymentModel> paymentsModel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			initializePayments();
			paymentsModel = FXCollections.observableArrayList(loadGuestPayments());
			paymentsTableView.setItems(paymentsModel);
		} catch (Exception ex) {

		}
	}

	@FXML
	private void onSearchAction(ActionEvent e) throws Exception {
		String date = dateFilter.getValue().toString();
		if (date == null)
			return;

		ArrayList<PaymentModel> filteredPayment = PaymentsModelRepository.filterPayment(date);
		if (filteredPayment == null)
			return;

		paymentsModel = FXCollections.observableArrayList(filteredPayment);
		paymentsTableView.setItems(paymentsModel);
		paymentsTableView.refresh();
	}

	@FXML
	private void onRefreshAction(ActionEvent e) throws Exception {
		dateFilter.setValue(null);
		paymentsModel = FXCollections.observableArrayList(loadGuestPayments());
		paymentsTableView.setItems(paymentsModel);
		paymentsTableView.refresh();
	}

	@FXML
	private void onPayPaymentAction(ActionEvent e) throws Exception {
		// send data to pay payment controllerr

		PaymentModel selected = paymentsTableView.getSelectionModel().getSelectedItem();
		if (selected == null)
			return;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/pay-payment-view.fxml"));
		Parent parent = loader.load();
		PayPaymentsController controller = loader.getController();
//		controller.setTotal(selected.getPrice());
//		controller.setFullName(SessionManager.user.getName());
//		controller.setPaymentId(selected.getPayment_id());
		controller.setData(selected.getPrice(), SessionManager.user.getName(), selected.getPayment_id());

		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(parent));
		stage.show();

	}

	private void initializePayments() {
		this.paymentIdCol.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
		this.firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		this.lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		this.dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		this.priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	@SuppressWarnings("static-access")
	public ArrayList<PaymentModel> loadGuestPayments() throws Exception {
		PaymentsModelRepository repository = new PaymentsModelRepository();
		// User logged in Id
		return repository.findSpecificPayments(1);
	}

}

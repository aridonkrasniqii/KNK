package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Rooms;
import helpers.Services;
import helpers.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Payments;
import repositories.PaymentRepository;

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

	@FXML
	private ToggleGroup payMethodGroup;

	@FXML
	private Label paymentId;

	@FXML
	private Label fullName;

	@FXML
	private TableView<Rooms> roomTableView;

	@FXML
	private TableView<Services> serviceTableView;

	@FXML
	private TableColumn<Services, String> serviceNameCol;

	@FXML
	private TableColumn<Services, Double> servicePriceCol;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			initRadio();
			bindPaymentMethod();
			initializeServices();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onPayAction(ActionEvent e) throws Exception {
		RadioButton payM = (RadioButton) payMethodGroup.getSelectedToggle();
		String payMethod = payM.getText();

		Double price = Double.parseDouble(totalValue.getText());
		Payments pay = new Payments(Integer.parseInt(paymentId.getText()), SessionManager.user.getId(), 1, price,
				payMethod, 1, new Date());

		if (PaymentRepository.update(pay) != null) {
			SuccessPopupComponent.show("Successfully payed", "Updated pay");
		} else {
			ErrorPopupComponent.show("Failed payment!!!");
		}
	}

	private void bindPaymentMethod() {
		cachButton.setToggleGroup(payMethodGroup);
		creditCardButton.setToggleGroup(payMethodGroup);
		giftCuponButton.setToggleGroup(payMethodGroup);
	}

	private void initializeServices() {
		// TODO:
		this.serviceNameCol.setCellValueFactory(new PropertyValueFactory<>(""));
		this.servicePriceCol.setCellValueFactory(new PropertyValueFactory<>(""));
	}

	public void initRadio() {
		cachButton.setToggleGroup(payMethodGroup);
		creditCardButton.setToggleGroup(payMethodGroup);
		giftCuponButton.setToggleGroup(payMethodGroup);

		RadioButton method = (RadioButton) payMethodGroup.getSelectedToggle();
		String meth = method.getText();
	}

	public void setTotal(double price) {
		String servicePrice = servicePriceCol.getText();
		this.totalValue.setText(Double.toString(price) + servicePrice);

	}

	public void setFullName(String fullName) {
		this.fullName.setText(fullName);
	}

	public void setPaymentId(int id) {
		this.paymentId.setText(Integer.toString(id));
	}

}

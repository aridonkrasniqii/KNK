package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController implements Initializable {


	private static final String LOGOUT_VIEW = "login";
	private static final String RESERVATION_ROOM_VIEW = "reservation-rooms";
	private static final String PAYMENT_VIEW = "payment-view";

	@FXML
	private Button mainBtn;
	@FXML
	private Button reservationsBtn;
	@FXML
	private Button paymentsBtn;
	@FXML
	private Button logOutBtn;
	@FXML
	private Pane mainPane;
	@FXML
	private Label loggedInUser;
	@FXML
	private MenuItem logoutButton;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}




//	@FXML
//	private void insertGuestMenuClicked(ActionEvent actionEvent) {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			URL url1 = new File("src/views/InsertGuest.fxml").toURI().toURL(); // same
//			loader.setLocation(url1);
//			Pane pane = loader.load();
//			Scene scene = new Scene(pane);
//			Stage stage = new Stage();
//			stage.initModality(Modality.APPLICATION_MODAL);
//			stage.setScene(scene);
//			stage.show();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}




//	public void getUser(String emri, String mbiemri) {
//		loggedInUser.setText("Logged In: " + emri + " " + mbiemri);
//	}


//
//	@Override
//	public void loadLangTexts(ResourceBundle langBundle) {
//		// mainBtn.setText(langBundle.getString("overviewButton"));
//		reservationsBtn.setText(langBundle.getString("reservationButton"));
//		paymentsBtn.setText(langBundle.getString("paymentsButton"));
//		logOutBtn.setText(langBundle.getString("logoutButton"));
//	}

 	// TODO: about part
	// TODO: Insert Guest





	@FXML
	private void onReservationAction(ActionEvent e ) throws Exception {
		changeStage(RESERVATION_ROOM_VIEW, e);
	}
	@FXML
	private void onPaymentsAction(ActionEvent e ) throws Exception {
		changeStage(PAYMENT_VIEW, e);
	}

	@FXML
	private void onLogoutAction(ActionEvent e ) throws Exception {
		changeStage(LOGOUT_VIEW, e);
	}

	@FXML
	private void onMenuLogoutAction(ActionEvent e ) throws Exception {
		changeStage(LOGOUT_VIEW,e);
	}

	@FXML
	private void onMenuInsertAction(ActionEvent e ) throws Exception {
		System.out.println("On menu insert");
	}

	@FXML
	private void onMenuAboutAction(ActionEvent e ) throws Exception {
		System.out.println("on menu about");
	}

	private void changeStage(String path , ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(setPath(path)));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}

	private String setPath(String path) {
		return "../views/" + path + ".fxml";
	}



}
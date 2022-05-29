package admin.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import helpers.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilities.I18N;

public class MainController implements Initializable {

	public static final String GUESTS_DASHBOARD = "guests";
	public static final String OVERVIEW_DASHBOARD = "overview";
	public static final String PAYMENTS_DASHBOARD = "payments";
	public static final String ROOMS_DASHBOARD = "rooms";
	public static final String SERVICE_TYPES_DASHBOARD = "service_types";
	public static final String STAFF_DASHBOARD = "staff";

	public static final String EVENTS_DASHBOARD = "events";

	private static final String VIEW_PATH = "../views";

	@FXML
	private Label sessionLabel;
	@FXML
	private VBox contentPane;
	@FXML
	private Button alButton;

	@FXML
	private Button enButton;
	@FXML
	private Button overviewBtn;
	@FXML
	private Button eventsBtn;
	@FXML
	private Button guestsBtn;
	@FXML
	private Button staffBtn;
	@FXML
	private Button roomsBtn;
	@FXML
	private Button paymentsBtn;
	@FXML
	private Button serviceBtn;
	@FXML
	private Button logoutBtn;

	@FXML
	private Label navTitle;
	@FXML
	private Label sectionTitle;
	@FXML
	private Label adminDashboardTitle;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		adminDashboardTitle.textProperty().bind(I18N.createStringBinding("adminDashboardTitle"));
		overviewBtn.textProperty().bind(I18N.createStringBinding("overviewBtn"));
		eventsBtn.textProperty().bind(I18N.createStringBinding("eventsBtn"));
		guestsBtn.textProperty().bind(I18N.createStringBinding("guestsBtn"));
		staffBtn.textProperty().bind(I18N.createStringBinding("staffBtn"));
		roomsBtn.textProperty().bind(I18N.createStringBinding("roomsBtn"));
		paymentsBtn.textProperty().bind(I18N.createStringBinding("paymentsBtn"));
		serviceBtn.textProperty().bind(I18N.createStringBinding("serviceBtn"));
		logoutBtn.textProperty().bind(I18N.createStringBinding("logoutBtn"));
		navTitle.textProperty().bind(I18N.createStringBinding("navTitle"));
		sectionTitle.textProperty().bind(I18N.createStringBinding("sectionTitle"));
	}

	public void setView(String view) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(this.viewPath(view)));
			Pane pane = null;

			switch (view) {
			case GUESTS_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case OVERVIEW_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case PAYMENTS_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case ROOMS_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case SERVICE_TYPES_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case STAFF_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;
			case EVENTS_DASHBOARD:
				pane = loader.load();
				contentPane.setAlignment(Pos.TOP_CENTER);
				break;

			default:
				throw new Exception("ERR_VIEW_NOT_FOUND");
			}
			sessionLabel.setText(SessionManager.user.getUsername().toUpperCase() + " " + SessionManager.lastLogin);
			contentPane.getChildren().clear();
			contentPane.getChildren().add(pane);
			VBox.setVgrow(pane, Priority.ALWAYS);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public String viewPath(String view) {
		return VIEW_PATH + "/" + view + ".fxml";
	}

	@FXML
	private void onOverViewNavClick(ActionEvent ev) {
		try {
			this.setView(OVERVIEW_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onGuestsNavClick(ActionEvent ev) {
		try {
			this.setView(GUESTS_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onStaffNavClick(ActionEvent ev) {
		try {
			this.setView(STAFF_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onRoomsNavClick(ActionEvent ev) {
		try {
			this.setView(ROOMS_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onPaymentsNavClick(ActionEvent ev) {
		try {
			this.setView(PAYMENTS_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onServiceTypesNavClick(ActionEvent ev) {
		try {
			this.setView(SERVICE_TYPES_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onEventViewNavClick(ActionEvent e) {
		try {
			this.setView(EVENTS_DASHBOARD);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onLogoutNavClick(ActionEvent ev) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../../views/login-view.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Logout");
			alert.setHeaderText("Are you sure to log out");
			alert.setResizable(false);

			Optional<ButtonType> result = alert.showAndWait();
			ButtonType button = result.orElse(ButtonType.CANCEL);

			if (button == ButtonType.OK) {
				stage.setScene(scene);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@FXML
	private void onMenuExitAction(ActionEvent e) throws Exception {
		Stage stage = (Stage) sessionLabel.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void onMenuLogoutAction(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/login-view.fxml"));
		Parent parent = loader.load();
		Stage stage = (Stage) sessionLabel.getScene().getWindow();
		Scene scene = new Scene(parent);
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Are you sure to log out");
		alert.setResizable(false);

		Optional<ButtonType> result = alert.showAndWait();
		ButtonType button = result.orElse(ButtonType.CANCEL);

		if (button == ButtonType.OK) {
			stage.setScene(scene);
		}

	}

	@FXML
	private void onInsertUserMenuAction(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/add-guest.fxml"));
		Parent parent = loader.load();
		Stage stage = (Stage) sessionLabel.getScene().getWindow();
		stage.setScene(new Scene(parent));
		stage.titleProperty().bind(I18N.createStringBinding("window.title"));
		stage.show();
	}

	@FXML
	private void onInsertRoomMenuAction(ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/roomviews/add-new-room.fxml"));
		Stage stage = (Stage) sessionLabel.getScene().getWindow();
		stage.setScene(new Scene(parent));
		stage.titleProperty().bind(I18N.createStringBinding("window.title"));
	}

	@FXML
	private void onAboutMenuAction(ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../views/about-view.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(parent));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.titleProperty().bind(I18N.createStringBinding("window.title"));
		stage.show();
	}

	@FXML
	void albanianLanguageOnClick(MouseEvent event) {
		I18N.setLocale(Locale.GERMAN);
	}

	@FXML
	void englishLanguageOnClick(MouseEvent event) {
		I18N.setLocale(Locale.ENGLISH);
	}

}

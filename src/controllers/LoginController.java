package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.controllers.MainController;
import admin.repository.AdminRepository;
import application.LoginValidate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repositories.RegisterGuestsRepository;

public class LoginController implements Initializable {

	private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	private AnchorPane parent;

	@FXML
	private Pane content_area;
	@FXML
	private TextField usernameLogin;
	@FXML
	private PasswordField passwordLogin;

//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		makeStageDrageable();
//
//	}
//
//	public void makeStageDrageable() {
//		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				xOffset = event.getSceneX();
//				yOffset = event.getSceneY();
//			}
//		});
//		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				App.stage.setX(event.getScreenX() - xOffset);
//				App.stage.setY(event.getScreenY() - yOffset);
//				App.stage.setOpacity(0.7f);
//			}
//		});
//		parent.setOnDragDone((e) -> {
//			App.stage.setOpacity(1.0f);
//		});
//		parent.setOnMouseReleased((e) -> {
//			App.stage.setOpacity(1.0f);
//		});
//	}

	// -------------------------- LOGIN FORM ACTIONS -------------------------- \\
	@FXML
	public void onLoginAction(ActionEvent e) throws Exception {

		String username = usernameLogin.getText();
		String password = passwordLogin.getText();
		// Validation first
		LoginValidate loginUser = new LoginValidate(username, password);
		Boolean result = loginUser.validate();

		if (result) {

			// check first for admin

			if (new AdminRepository().findByUsernamePassword(username, password) != null) {
				try {
					// FIXME: error exception
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("../admin/views/admin-screen.fxml"));
					Parent parent = loader.load();

					MainController controller = loader.getController();
					controller.setView(MainController.GUESTS_DASHBOARD);
					Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
					Scene scene = new Scene(parent);
					primaryStage.setScene(scene);
				} catch (Exception except) {
					System.out.println(except);
				}
				// then check if user is guest

			} else {
				if (new RegisterGuestsRepository().findByUsernamePassword(username, password) != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Information");
					alert.setContentText("Successfully logged in ");
					alert.showAndWait();

				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText("Error");
					alert.setContentText("Wrong username or password");
					alert.showAndWait();

				}
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Invalid data");
			alert.showAndWait();
		}
	}

	@FXML
	private void close_App(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void open_registration(MouseEvent event) throws IOException {
		Parent fxml = FXMLLoader.load(getClass().getResource("../views/Register.fxml"));
		content_area.getChildren().removeAll();
		content_area.getChildren().setAll(fxml);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}

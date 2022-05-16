package controllers;

import java.awt.print.PrinterAbortException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import models.User;
import models.UserRole;
import processor.LoginProcessor;
import processor.LoginValidate;

public class LoginController {

	@FXML
	private AnchorPane parent;

	@FXML
	private Pane content_area;

	@FXML
	private TextField emailField;

	@FXML
	private TextField passwordField;



	private static final String ADMIN_SCREEN = "admin-screen";
	private static final String GUEST_SCREEN = "main-view";

	@FXML
	private void onLoginAction(ActionEvent e) {
		try {

			User user = null;
			String email = emailField.getText();
			String password = passwordField.getText();

			boolean emptyFields = LoginValidate.validate(email,password);
			if(emptyFields) ErrorPopupComponent.show("Empty fields");

			user = login(email, password);

			if (user == null)
			{
				ErrorPopupComponent.show("Wrong username or password");
				return;
			}

			if (user.getRole() == UserRole.Admin) {
				loadPage(e,user,ADMIN_SCREEN);
			} else if(user.getRole() == UserRole.Guest){
				loadPage(e,user,GUEST_SCREEN);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}


	public void loadPage(ActionEvent e, User user, String view) throws Exception{

		FXMLLoader loader = new FXMLLoader();
		Parent parent;
		switch (view) {
			case ADMIN_SCREEN :
				loader.setLocation(getClass().getResource("../admin/views/admin-screen.fxml"));
				parent = loader.load();
				SessionManager.user = user;
				SessionManager.lastLogin = new Date();
				MainController adminController = loader.getController();
				adminController.setView(MainController.GUESTS_DASHBOARD);
				Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				primaryStage.setScene(new Scene(parent));
				break;
			case GUEST_SCREEN:
				loader.setLocation(getClass().getResource("../views/main-view.fxml"));
				parent = loader.load();
				SessionManager.user = user;
				SessionManager.lastLogin = new Date();
				MainViewController guestController = loader.getController();
				guestController.setView(MainViewController.RESERVATION_ROOM_VIEW);
				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(new Scene(parent));
				break;
			default:
				ErrorPopupComponent.show("Error occurred");

		}

	}


	private User login(String email, String password) throws Exception {
		LoginProcessor loginProcessor = new LoginProcessor();
		return  loginProcessor.login(email ,password);
	}

	@FXML
	private void onRegisterAction(MouseEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/register-view.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(parent);
		stage.setScene(scene);

	}
}

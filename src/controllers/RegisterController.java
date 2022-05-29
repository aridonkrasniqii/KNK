package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import processor.RegisterValidate;
import processor.SecurityHelper;
import repositories.UserRepository;
import utilities.I18N;

public class RegisterController implements Initializable {

	@FXML
	private TextField nameField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField passwordField;
	@FXML
	private Button registerBtn;
	@FXML
	private Label goBackToLogin;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.usernameField.promptTextProperty().bind(I18N.createStringBinding("usernameField"));
		this.nameField.promptTextProperty().bind(I18N.createStringBinding("nameField"));
		this.emailField.promptTextProperty().bind(I18N.createStringBinding("emailField"));
		this.passwordField.promptTextProperty().bind(I18N.createStringBinding("passwordField"));
		this.registerBtn.textProperty().bind(I18N.createStringBinding("registerBtn"));
		this.goBackToLogin.textProperty().bind(I18N.createStringBinding("goBackToLogin"));
	}

	@FXML
	private void onRegisterAction(ActionEvent event) {

		try {
			String name = nameField.getText();
			String username = usernameField.getText();
			String email = emailField.getText();
			String password = passwordField.getText();

			// check for empty fields

			boolean emptyFields = RegisterValidate.validate(name, username, email, password);
			if (emptyFields)
				return;

			boolean userExists = UserRepository.find(email, username);
			if (userExists) {
				ErrorPopupComponent.show("User already exists");
				return;
			}

			User registeredUser = register(name, username, email, password);

			if (registeredUser != null) {
				SuccessPopupComponent.show("User is registered " , "Registered");
				return;
			} else {
				ErrorPopupComponent.show("User was not registered");
				return;
			}

		} catch (Exception ex) {
			System.out.println(ex);
			ErrorPopupComponent.show(ex);
		}

	}

	private User register(String name, String username, String email, String password) throws Exception {
		User user = new User();
		user.setIsActive(true);
		user.setEmail(email);
		user.setUsername(username);
		user.setName(name);
		user.setRole(UserRole.Guest);
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		String salt = SecurityHelper.generateSalt();
		String hashedPassword = SecurityHelper.computeHash(password, salt);
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		if(UserRepository.create(user) != null) {
			return user;
		}
		return null;
	}

	@FXML
	private void onBackToLoginAction(MouseEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/login-view.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(parent);
		stage.setScene(scene);


	}

}

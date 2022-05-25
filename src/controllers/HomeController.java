package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.html.ImageView;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable {

	@FXML
	private ImageView building;
	@FXML
	private Label sunhotel;
	@FXML
	private Button login_button;

	public void changeScreen(ActionEvent event) throws Exception {
		onLogin(event);
	}

	@FXML
	public void setOnKeyPressed(KeyEvent event) throws Exception {
		if (event.getCode().equals(KeyCode.ENTER)) {
			onLogin(event);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	public void onLogin(Event event) throws Exception {

		Parent loginParent = FXMLLoader.load(getClass().getResource("../views/login-view.fxml"));
		Scene loginscene = new Scene(loginParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();

	}
}
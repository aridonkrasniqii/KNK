package admin.controllers.staff;

import java.net.URL;
import java.util.ResourceBundle;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import models.Staff;
import processor.SecurityHelper;
import repositories.StaffRepository;

public class AddStaffController implements Initializable {

	@FXML
	private TextField fnameField;
	@FXML
	private TextField lnameField;
	@FXML
	private TextField personalNumberField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private DatePicker birthdayField;

	@FXML
	private RadioButton maleBtn;
	@FXML
	private RadioButton femaleBtn;

	private ToggleGroup toggle = new ToggleGroup();
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox position;
	@FXML
	private TextField salaryField;
	@FXML
	private PasswordField passwordField;

	@Override

	public void initialize(URL url, ResourceBundle resourceBundle) {
		initPosition();
		maleBtn.setToggleGroup(toggle);
		femaleBtn.setToggleGroup(toggle);
	}

	@SuppressWarnings("unchecked")
	private void initPosition() {
		position.getItems().add("Software Engineer");
		position.getItems().add("Cyber Security Engineer");
		position.getItems().add("Front End Engineer");
		position.getItems().add("Back End Engineer");
	}

	@FXML
	private void onCreateAction(ActionEvent e) throws Exception {
		try {
			String fname = fnameField.getText();
			String lname = lnameField.getText();
			String prsNumber = personalNumberField.getText();
			String phoneNum = phoneNumberField.getText();
			String birthday = birthdayField.getValue().toString();
			RadioButton gender = (RadioButton) toggle.getSelectedToggle();
			String genderStr = gender.getText();
			String pos = position.getValue().toString();
			double salary = Double.parseDouble(salaryField.getText());
			String salt = SecurityHelper.generateSalt();
			String hashedPassword = SecurityHelper.computeHash(passwordField.getText(), salt);
			Staff staff = new Staff(0, fname, lname, prsNumber, phoneNum, genderStr, birthday, pos, salary,
					hashedPassword);
			if (staff != null) {
				StaffRepository.create(staff);
				SuccessPopupComponent.show("SuccessFully created staff", "Created");
			}
		} catch (Exception ex) {
			ErrorPopupComponent.show(ex);
		}
	}

	@FXML
	private void onCancleAction(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../../views/admin-screen.fxml"));
		Parent parent = loader.load();
		MainController controller = loader.getController();
		controller.setView(MainController.STAFF_DASHBOARD);

		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(parent));
	}
}

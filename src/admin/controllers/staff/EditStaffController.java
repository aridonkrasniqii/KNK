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
import repositories.StaffRepository;

public class EditStaffController implements Initializable {

	private int id;
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
		maleBtn.setToggleGroup(toggle);
		femaleBtn.setToggleGroup(toggle);
	}

	@SuppressWarnings("unchecked")
	public void setData(Staff staff) throws Exception {
		id = staff.getId();
		fnameField.setText(staff.getFirst_name());
		lnameField.setText(staff.getLast_name());
		personalNumberField.setText(staff.getPersonal_number());
		phoneNumberField.setText(staff.getPhone_number());
		birthdayField.setValue(null);
		toggle.selectToggle(staff.getGender() == "male" ? maleBtn : femaleBtn);
		position.setValue(staff.getPosition());
		salaryField.setText(Double.toString(staff.getSalary()));
		passwordField.setText(staff.getPassword());
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

	@FXML
	private void onUpdateAction(ActionEvent e) throws Exception {
		String fname = fnameField.getText();
		String lname = lnameField.getText();
		String prsNumber = personalNumberField.getText();
		String phoneNum = phoneNumberField.getText();
		if (birthdayField.getValue().toString() == null) {
			ErrorPopupComponent.show("Must fill date");
			return;
		}
		String birthday = birthdayField.getValue().toString();
		RadioButton gen = (RadioButton) toggle.getSelectedToggle();
		String gender = gen.getText();
		String pos = position.getValue().toString();
		double salary = Double.parseDouble(salaryField.getText());
		String password = passwordField.getText();
		Staff staff = new Staff(id, fname, lname, prsNumber, phoneNum, gender, birthday, pos, salary, password);

		if (StaffRepository.update(staff) != null) {
			SuccessPopupComponent.show("Successfully updated", "Updated");
		}else {
			ErrorPopupComponent.show("Staff was not edited ");
		}
	}

}

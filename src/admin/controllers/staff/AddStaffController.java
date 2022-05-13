package admin.controllers.staff;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repositories.StaffRepository;

import java.net.URL;
import java.util.ResourceBundle;

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
            String password = passwordField.getText();
            Staff staff = new Staff(0, fname, lname, prsNumber, phoneNum, genderStr, birthday, pos, salary, password);
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

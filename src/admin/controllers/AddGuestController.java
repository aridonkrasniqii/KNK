package admin.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import components.ErrorPopupComponent;
import components.SecurityHelper;
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
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import processor.RegisterValidate;
import repositories.UserRepository;
import utilities.I18N;

public class AddGuestController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label createGuestTitle;
    @FXML
    private Button createBtn;
    @FXML
    private Button cancelBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGuestTitle.textProperty().bind(I18N.createStringBinding("createGuestTitle"));
        nameField.promptTextProperty().bind(I18N.createStringBinding("nameField"));
        usernameField.promptTextProperty().bind(I18N.createStringBinding("usernameField"));
        emailField.promptTextProperty().bind(I18N.createStringBinding("emailField"));
        passwordField.promptTextProperty().bind(I18N.createStringBinding("passwordField"));
        createBtn.textProperty().bind(I18N.createStringBinding("createBtn"));
        cancelBtn.textProperty().bind(I18N.createStringBinding("cancelBtn"));
    }
    @FXML
    private void onCreateAction(ActionEvent event) {

        try {
            String name = nameField.getText();
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            boolean emptyfields = RegisterValidate.validate(name, username, email, password);
            if (emptyfields) {
                ErrorPopupComponent.show("Fill fields");
                return;
            }


            boolean userExists = UserRepository.find(email, username);
            if (userExists) {
                ErrorPopupComponent.show("User already exists");
                return;
            }

            System.out.println("Test 3 ");
            User registeredUser = register(name, username, email, password);

            if (registeredUser != null) {
                SuccessPopupComponent.show("Successfully registered", "");
                return;
            } else {
                ErrorPopupComponent.show("User was not registered");
                return;
            }


        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }

    }


    @FXML
    private void onCancleAction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/admin-screen.fxml"));
        Parent parent = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MainController controller = loader.getController();
        controller.setView(MainController.GUESTS_DASHBOARD);
        stage.setScene(new Scene(parent));
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
        user = UserRepository.create(user);
        return user;
    }
}

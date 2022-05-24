package controllers;

import components.ErrorPopupComponent;
import components.SecurityHelper;
import components.SuccessPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import processor.RegisterValidate;
import repositories.UserRepository;

import java.util.Date;

public class MenuRegisterGuestController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;


    @FXML
    private void onCreateAction(ActionEvent event) {

        try {
            String name = nameField.getText();
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            // check for empty fields

            boolean fields = RegisterValidate.validate(name, username, email, password);
            if (!fields) {
                throw new Exception();
            }

            boolean userExists = UserRepository.find(email, username);
            if (userExists) {
                ErrorPopupComponent.show("User already exists");
                return;
            }

            User registeredUser = register(name, username, email, password);

            if (registeredUser != null) {
                SuccessPopupComponent.show("Successfully registered", "");
            } else {
                ErrorPopupComponent.show("User was not registered");
            }

        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }

    }


    @FXML
    private void onCancelAction(ActionEvent e) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("../../views/main-view.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

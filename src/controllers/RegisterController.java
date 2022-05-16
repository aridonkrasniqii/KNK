package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import components.ErrorPopupComponent;
import components.SecurityHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import models.User;
import models.UserRole;
import repositories.UserRepository;

public class RegisterController implements Initializable {

  @FXML
  private TextField nameField;
  @FXML
  private TextField usernameField;
  @FXML
  private TextField emailField;
  @FXML
  private TextField passwordField;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  private void onRegisterAction(ActionEvent event) {

    try {
      String name = nameField.getText();
      String username = usernameField.getText();
      String email = emailField.getText();
      String password = passwordField.getText();

      // if (fields are not empty ) call method register
      // register(username, email , password);

      register(name, username, email, password);

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
    user = UserRepository.create(user);
    return user;
  }

}

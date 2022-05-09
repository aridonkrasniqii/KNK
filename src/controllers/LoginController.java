package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.controllers.MainController;
import components.SecurityHelper;
import components.SuccessPopupComponent;
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
import repositories.UserRepository;

public class LoginController implements Initializable {

  @FXML
  private AnchorPane parent;

  @FXML
  private Pane content_area;

  @FXML
  private TextField emailField;

  @FXML
  private TextField passwordField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  private void onLoginAction(ActionEvent e) {
    try {

      User user = null;
      String email = emailField.getText();
      String password = passwordField.getText();

      user = login(email, password);

      if (user == null)
        throw new Exception();

      // TODO:
      // store user name and user last login and show these details to main page
      // when user is logged in

      if (user.getRole() == UserRole.Admin) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../admin/views/admin-screen.fxml"));
        Parent parent = loader.load();

        MainController controller = loader.getController();
        controller.setView(MainController.GUESTS_DASHBOARD);

        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
      } else {
        SuccessPopupComponent.show("Successfully logged in", "logged in");
        // Parent parent = FXMLLoader.load(getClass().getResource(" MAINNNNN PAGE "));
        // Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // Scene scene = new Scene(parent);
        // primaryStage.setScene(scene);

      }
    } catch (Exception ex) {
      System.out.println(ex);
    }

  }

  private User login(String email, String password) throws Exception {
    User user = UserRepository.find(email);
    if (user == null)
      return user;

    String hashedPassword = SecurityHelper.computeHash(password, user.getSalt());
    if (!user.getPassword().equals(hashedPassword))
      return null;
    return user;

  }

  @FXML
  private void onRegisterAction(MouseEvent e) throws IOException {
    Parent parent = FXMLLoader.load(getClass().getResource("../views/register.fxml"));
    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    Scene scene = new Scene(parent);
    stage.setScene(scene);

  }
}

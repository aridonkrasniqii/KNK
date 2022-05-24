package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import admin.controllers.MainController;
import com.mysql.cj.x.protobuf.Mysqlx;
import components.ErrorPopupComponent;
import helpers.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import processor.LoginProcessor;
import processor.LoginValidate;
import utilities.I18N;

public class LoginController {

    @FXML
    private AnchorPane parent;

    @FXML
    private Pane content_area;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button alButton;

    @FXML
    private Button enButton;

    @FXML
    private Button loginBtn;

    @FXML
    private Label goToRegister;

    @FXML
    private Label newUser;

    @FXML
    private Label forgotPw;

    @FXML
    private Label welcome;

    private static final String ADMIN_SCREEN = "admin-screen";
    private static final String GUEST_SCREEN = "main-view";

    @FXML
    private void onLoginAction(ActionEvent e) {
        try {
            User user = null;
            String email = usernameField.getText();
            String password = passwordField.getText();

            boolean emptyFields = LoginValidate.validate(email, password);
            if (emptyFields) {
                ErrorPopupComponent.show("Empty fields");
                return;
            }

            user = login(email, password);

            if (user == null) {
                ErrorPopupComponent.show("Wrong username or password");
                return;
            }

            if (user.getRole() == UserRole.Admin) {
                loadPage(e, user, ADMIN_SCREEN);
            } else if (user.getRole() == UserRole.Guest) {
                loadPage(e, user, GUEST_SCREEN);
            }
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }

    }

    public void loadPage(ActionEvent e, User user, String view) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent parent;
        switch (view) {
            case ADMIN_SCREEN:
                loader.setLocation(getClass().getResource("../admin/views/admin-screen.fxml"));
                parent = loader.load();
                SessionManager.user = user;
                SessionManager.lastLogin = new Date();
                MainController adminController = loader.getController();
                adminController.setView(MainController.OVERVIEW_DASHBOARD);
                Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                primaryStage.setScene(new Scene(parent));
                primaryStage.titleProperty().bind(I18N.createStringBinding("window.title"));
                primaryStage.centerOnScreen();
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
        return loginProcessor.login(email, password);
    }

    @FXML
    private void onRegisterAction(MouseEvent e) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/register-view.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.titleProperty().bind(I18N.createStringBinding("window.title"));

    }

    @FXML
    public void albanianLanguageOnClick(MouseEvent event) {
        this.usernameField.promptTextProperty().bind(I18N.createStringBinding("usernameField"));
        this.passwordField.promptTextProperty().bind(I18N.createStringBinding("passwordField"));
        this.loginBtn.textProperty().bind(I18N.createStringBinding("loginBtn"));
        this.goToRegister.textProperty().bind(I18N.createStringBinding("goToRegister"));
        this.welcome.textProperty().bind(I18N.createStringBinding("welcome"));
        this.forgotPw.textProperty().bind(I18N.createStringBinding("forgotPw"));
        this.newUser.textProperty().bind(I18N.createStringBinding("newUser"));

        I18N.setLocale(Locale.GERMAN);
    }

    @FXML
    public void englishLanguageOnClick(MouseEvent event) {
        I18N.setLocale(Locale.ENGLISH);
    }
}

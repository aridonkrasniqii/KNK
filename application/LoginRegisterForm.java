package application;

import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.stage.*;
import model.RegisterGuests;
import repository.RegisterGuestsRepository;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Date;

public class LoginRegisterForm extends Application {

  TextField usernameField;
  TextField passwordField;

  @Override
  public void start(Stage primaryStage) {

    TabPane tabPane = new TabPane();
    Tab loginTab = new Tab("Login", new LoginForm());
    Tab registerTab = new Tab("Register", new RegisterForm());

    loginTab.setClosable(false);
    registerTab.setClosable(false);
    tabPane.getTabs().addAll(loginTab, registerTab);

    primaryStage.setMinWidth(900);
    primaryStage.setMinHeight(700);
    primaryStage.setScene(new Scene(tabPane, 900, 700));
    primaryStage.setTitle("Application");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class LoginForm extends GridPane {

  private TextField usernameField;
  private PasswordField passwordField;

  public LoginForm() {
    super();
    this.load();
  }

  public LoginForm load() {

    try {

      Label usernameLabel = new Label("Username: ");
      usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
      Label passwordLabel = new Label("Password: ");
      passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));

      usernameField = new TextField();
      usernameField.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
      passwordField = new PasswordField();
      passwordField.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
      usernameField.setPromptText("Username..");
      passwordField.setPromptText("Password..");

      String css = "-fx-background-color: darkslateblue; -fx-text-fill:white;";

      Button login = new Button("Submit");
      login.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
      login.setPrefSize(80, 25);
      login.setStyle(css);
      Button clear = new Button("Clear");
      clear.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
      clear.setPrefSize(80, 25);
      clear.setStyle(css);

      HBox buttonPane = new HBox(10);
      buttonPane.getChildren().addAll(login, clear);

      login.setOnAction(e -> {
        try {
          submitAction();
        } catch (Exception except) {
          System.out.println("Error in login register class set on action method : " + except);
        }
      });

      clear.setOnAction(e -> {
        clearAction();
      });

      super.setVgap(10);
      super.setHgap(10);
      super.add(usernameLabel, 0, 0);
      super.add(usernameField, 1, 0, 2, 1);
      super.add(passwordLabel, 0, 1);
      super.add(passwordField, 1, 1, 2, 1);
      super.add(buttonPane, 1, 2);
      super.setAlignment(Pos.CENTER);

    } catch (Exception e) {
      System.out.println("An error happened in login form " + e);
    }
    return this;

  }

  public void clearAction() {
    usernameField.setText("");
    passwordField.setText("");
  }

  // Validation ....

  public void submitAction() throws Exception {

    String username = usernameField.getText();
    String password = passwordField.getText();

    // Validation first
    LoginUser loginUser = new LoginUser(username, password);
    Boolean result = loginUser.validate();

    if (result) {
      RegisterGuestsRepository repository = new RegisterGuestsRepository();
      RegisterGuests guest = repository.findByUsernamePassword(username, password);

      if (guest != null) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText("Successfully logged in ");
        alert.showAndWait();
        System.out.println("Username: " + guest.getUsername());
        System.out.println("Password: " + guest.getPassword());

      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Wrong username or password");
        alert.showAndWait();
      }

    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Invalid data");
      alert.showAndWait();
    }

  }

}

class RegisterForm extends GridPane {

  private TextField nameField;
  private TextField emailField;
  private PasswordField passwordField;
  private TextField usernameField;
  private PasswordField confirmPasswordField;
  private ToggleGroup genderGroup;
  private RadioButton female;
  private RadioButton male;
  private ComboBox<String> locationCombo;
  private Button registerButton;
  private Button clearButton;
  private DatePicker birthdayPicker;

  public RegisterForm() {
    super();
    this.load();
  }

  public RegisterForm load() {

    try {

      Label nameLabel = new Label("Name ");
      Label usernameLabel = new Label("Username");
      Label emailLabel = new Label("Email");
      Label passwordLabel = new Label("Password");
      Label confirmPasswordLabel = new Label("Confirm Password");
      Label birthdayLabel = new Label("Birthday");
      Label genderLabel = new Label("Gender");
      Label locationLabel = new Label("Location");

      nameField = new TextField();
      usernameField = new TextField();
      emailField = new TextField();
      passwordField = new PasswordField();
      confirmPasswordField = new PasswordField();

      birthdayPicker = new DatePicker();
      birthdayPicker.setValue(LocalDate.now());

      female = new RadioButton("Female");
      male = new RadioButton("Male");

      genderGroup = new ToggleGroup();
      female.setToggleGroup(genderGroup);
      male.setToggleGroup(genderGroup);
      HBox genderPane = new HBox(10);
      genderPane.getChildren().addAll(female, male);

      locationCombo = new ComboBox<String>();
      ObservableList<String> locationList = FXCollections.observableArrayList("Albania", "Kosovo", "Germany", "France",
          "Hungary");
      locationCombo.getItems().addAll(locationList);
      locationCombo.setPromptText("Select your Country...");

      registerButton = new Button("Register");
      registerButton.setAlignment(Pos.BOTTOM_LEFT);

      clearButton = new Button("Clear");
      clearButton.setAlignment(Pos.BOTTOM_RIGHT);

      HBox registerPane = new HBox();
      registerPane.getChildren().addAll(registerButton, clearButton);
      registerPane.setSpacing(20);

      registerButton.setOnAction(e -> {

        try {
          submitAction();
        } catch (Exception except) {
          System.out.println("Error on set on action method : " + except);
        }

      });

      clearButton.setOnAction(e -> {
        clearForm();
      });

      super.add(nameLabel, 0, 0);
      super.add(nameField, 1, 0, 3, 1);
      super.add(usernameLabel, 0, 1);
      super.add(usernameField, 1, 1, 3, 1);
      super.add(emailLabel, 0, 2);
      super.add(emailField, 1, 2, 3, 1);
      super.add(passwordLabel, 0, 3);
      super.add(passwordField, 1, 3, 3, 1);
      super.add(confirmPasswordLabel, 0, 4);
      super.add(confirmPasswordField, 1, 4, 3, 1);
      super.add(birthdayLabel, 0, 5);
      super.add(birthdayPicker, 1, 5, 3, 1);
      super.add(genderLabel, 0, 6);
      super.add(genderPane, 2, 6);
      super.add(locationLabel, 0, 7);
      super.add(locationCombo, 1, 7, 3, 1);
      super.add(registerPane, 2, 8);
      super.setHgap(10);
      super.setVgap(15);
      super.setAlignment(Pos.CENTER);

    } catch (Exception e) {

      System.out.println("An error happened in register form " + e);

    }

    return this;
  }

  public void submitAction() throws Exception {

    String name = nameField.getText();
    String username = usernameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();
    String email = emailField.getText();
    Date date = new Date();
    DateFormat date_format = new SimpleDateFormat("yyyy-mm-dd");
    String date_string = "";
    try {

      date = birthdayPicker.getValue() != null ? new Date(birthdayPicker.getValue().toEpochDay()) : null;
      date_string = date_format.format(date);

    } catch (Exception e) {

      Alert dateAlert = new Alert(AlertType.ERROR);
      dateAlert.setHeaderText("Error");
      dateAlert.setContentText("Invalid data");
      dateAlert.showAndWait();
      return;

    }

    String gender = null;
    if (female.isSelected()) {
      gender = "Female";
    }
    if (male.isSelected()) {
      gender = "Male";
    }

    String location = locationCombo.getSelectionModel().getSelectedItem();
    RegisterUser registerUser = RegisterUser.fromValuesRegister(name, username, password, confirmPassword, email,
        date_string, gender, location);

    Boolean result = registerUser.validate();

    if (result) {
      // TODO:
      // SAVE DATA TO DATABASE
      // Repository
      RegisterGuestsRepository repository = new RegisterGuestsRepository();
      //
      RegisterGuests guest = RegisterGuests.createGuest(name, username, email, password, "2020-02-02", gender,
          location);

      repository.create(guest);

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setContentText(result ? "Successfully registered " : "Invalid data ");
      alert.setHeaderText("Great work");
      alert.showAndWait();

    } else {
      // TODO:
      // THROW EXCEPTION

      Alert alert = new Alert(AlertType.ERROR);
      alert.setContentText("Invalid data");
      alert.setHeaderText("Error");
      alert.showAndWait();
    }

  }

  public void clearForm() {
    this.nameField.setText("");
    this.usernameField.setText("");
    this.emailField.setText("");
    this.passwordField.setText("");
    this.confirmPasswordField.setText("");
    this.birthdayPicker.setValue(null);
    this.genderGroup.selectToggle(null);
    this.locationCombo.setValue(null);
  }

}

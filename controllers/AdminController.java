package controllers;

import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.RegisterGuests;

public class AdminController implements Initializable {

  // ----------------- DB PARAMETERS -------------------- \\
  private static String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static String SERVER = "localhost:3306";
  private static String DATABASE = "DBHotel";
  private static String USERNAME = "aridon";
  private static String PASSWORD = "aridon123";
  private Connection connection;

  // ---------------- FXML ATTRIBUTES -------------------\\

  @FXML
  private TableView<RegisterGuests> tableView;
  @FXML
  private TableColumn<RegisterGuests, Integer> idColumn;
  @FXML
  private TableColumn<RegisterGuests, String> nameColumn;
  @FXML
  private TableColumn<RegisterGuests, String> usernameColumn;
  @FXML
  private TableColumn<RegisterGuests, String> emailColumn;
  @FXML
  private TableColumn<RegisterGuests, String> passwordColumn;
  @FXML
  private TableColumn<RegisterGuests, String> birthdateColumn;
  @FXML
  private TableColumn<RegisterGuests, String> registeredDateColumn;

  @FXML
  private TableColumn<RegisterGuests, String> genderColumn;
  @FXML
  private TableColumn<RegisterGuests, String> locationColumn;

  @FXML
  private TextField idField;
  @FXML
  private TextField nameField;
  @FXML
  private TextField usernameField;
  @FXML
  private TextField emailField;
  @FXML
  private TextField passwordField;
  @FXML
  private TextField registerDateField;
  @FXML
  private TextField birthdateField;
  @FXML
  private TextField genderField;
  @FXML
  private TextField locationField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    this.usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    this.birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    this.registeredDateColumn.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
    this.genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    this.locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

    try {
      this.connection = initConnection();
      ObservableList<RegisterGuests> guests = FXCollections.observableArrayList(getGuests());
      tableView.setItems(guests);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // when we select a row text fields will be updated
    tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    tableView.getSelectionModel().selectedItemProperty().addListener((observe, old, _new) -> {
      if (_new != null) {
        renderGuests(_new);
      }
    });
  }

  public Connection initConnection() throws SQLException {
    try {
      Class.forName(DRIVER);
      return DriverManager.getConnection(
          "jdbc:mysql://" + SERVER + "/" +
              DATABASE,
          USERNAME,
          PASSWORD);
    } catch (ClassNotFoundException e) {
      return null;
    }
  }

  @FXML
  public void onCreateAction(ActionEvent e) throws Exception {

    String name = nameField.getText();
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = emailField.getText();
    String birthdate = birthdateField.getText();
    String registered_date = registerDateField.getText();
    String gender = genderField.getText();
    String location = locationField.getText();

    String query = String.format(
        "INSERT INTO registerGuests(first_name,username,email,password,birthdate,registered_date,gender,location)" +
            "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",
        name, username, email, password, birthdate, registered_date, gender, location);

    Statement statement = this.connection.createStatement();
    int affectedRows = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
    if (affectedRows != 1) {
      // FIXME:
      throw new Exception();
    }

    ResultSet result = statement.getGeneratedKeys();

    if (result.next()) {

      int id = result.getInt(1);

      RegisterGuests guest = RegisterGuests.createGuest(id, name, username, email, password, birthdate,
          registered_date, gender, location);
      // add a row to the table
      tableView.getItems().add(guest);
      tableView.refresh();
      tableView.getSelectionModel().clearSelection();

    }

  }

  public void clearFields() {
    idField.clear();
    nameField.clear();
    usernameField.clear();
    emailField.clear();
    passwordField.clear();
    genderField.clear();
    birthdateField.clear();
    registerDateField.clear();
    locationField.clear();
    tableView.getSelectionModel().clearSelection();
  }

  // when item is selected this method upadate text fields with data
  private void renderGuests(RegisterGuests guest) {
    idField.setText(Integer.toString(guest.getId()));
    nameField.setText(guest.getFirst_name());
    usernameField.setText(guest.getUsername());
    emailField.setText(guest.getEmail());
    passwordField.setText(guest.getPassword());
    registerDateField.setText(guest.getRegisteredDate());
    birthdateField.setText(guest.getBirthdate());
    genderField.setText(guest.getGender());
    locationField.setText(guest.getLocation());
  }




  @FXML
  private void onUpdateAction(ActionEvent e ) throws Exception{
    int id = Integer.parseInt(idField.getText());
    String name = nameField.getText();
    String username = usernameField.getText();
    String email = emailField.getText();
    String password = emailField.getText();
    String birthdate = birthdateField.getText();
    String registered_date = registerDateField.getText();
    String gender = genderField.getText();
    String location = locationField.getText();
    String query = "UPDATE registerGuests SET first_name = ? , username = ? , email = ?, password = ? , birthdate = ? , registered_date = ? , gender = ? , location = ? WHERE id = ?";

    PreparedStatement statement = this.connection.prepareStatement(query);
    statement.setString(1, name);
    statement.setString(2, username);
    statement.setString(3, email);
    statement.setString(4, password);
    statement.setString(5, birthdate);
    statement.setString(6, registered_date);
    statement.setString(7, gender);
    statement.setString(8, location);
    statement.setInt(9, id);

    int affectedRows = statement.executeUpdate();
    if(affectedRows != 1)  throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

    RegisterGuests selected = tableView.getSelectionModel().getSelectedItem();

    selected.setName(name);
    selected.setUsername(username);
    selected.setEmail(email);
    selected.setRegisteredDate(registered_date);
    selected.setBirthdate(birthdate);
    selected.setGender(gender);
    selected.setLocation(location);
    tableView.refresh();
    clearFields();

  }



  @FXML
  public void onDeleteAction(ActionEvent e) throws Exception{
    int id = Integer.parseInt(idField.getText());

    String query = "DELETE FROM registerGuests WHERE id = ?";
    PreparedStatement statement = this.connection.prepareStatement(query);
    statement.setInt(1, id);

    if(statement.executeUpdate() != 1) throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

    RegisterGuests selected = tableView.getSelectionModel().getSelectedItem();
    tableView.getItems().remove(selected);
    clearFields();
    tableView.refresh();

  }

  private ArrayList<RegisterGuests> getGuests() throws SQLException {
    String query = "SELECT * FROM registerGuests";
    Statement statement = this.connection.createStatement();
    ResultSet result = statement.executeQuery(query);

    ArrayList<RegisterGuests> guests = new ArrayList<RegisterGuests>();

    // if something fails change id everywhere !!!!!!!!!
    while (result.next()) {
      RegisterGuests guest = RegisterGuests.createGuest(
          result.getInt("id"),
          result.getString("first_name"),
          result.getString("username"),
          result.getString("email"),
          result.getString("password"),
          result.getString("birthdate"),
          result.getString("registered_date"),
          result.getString("gender"),
          result.getString("location"));
      guests.add(guest);
    }

    return guests;
  }




}

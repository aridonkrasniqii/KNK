package admin.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.DBConnection;
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
import models.RegisterGuests;
import repositories.RegisterGuestsRepository;

public class GuestController implements Initializable{
  private DBConnection connection;
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
  @FXML
  private TextField searchField;
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try{
      this.connection = DBConnection.getConnection();
      initializeGuests();
      ObservableList<RegisterGuests> guests = FXCollections.observableArrayList(loadGuests());
      tableView.setItems(guests);


      tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      tableView.getSelectionModel().selectedItemProperty().addListener((observe, old, _new) -> {
        if (_new != null) {
          renderGuests(_new);
        }
      });

    }catch(Exception ex) {
      System.out.println(ex);
    }
  }

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

  public void clearGuestFields() {
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

  @FXML
  public void onCreateAction(ActionEvent e) throws Exception {
    RegisterGuests rGuests = RegisterGuests.createGuest(0,
        nameField.getText(),
        usernameField.getText(),
        emailField.getText(),
        passwordField.getText(),
        birthdateField.getText(),
        registerDateField.getText(),
        genderField.getText(),
        locationField.getText());
    RegisterGuestsRepository repository = new RegisterGuestsRepository();
    repository.create(rGuests);
    tableView.getItems().add(rGuests);
    tableView.refresh();
    tableView.getSelectionModel().clearSelection();
  }

  @FXML
  private void onUpdateAction(ActionEvent e) throws Exception {
    RegisterGuestsRepository repository = new RegisterGuestsRepository();
    RegisterGuests rGuests = RegisterGuests.createGuest(Integer.parseInt(idField.getText()),
        nameField.getText(),
        usernameField.getText(),
        emailField.getText(),
        passwordField.getText(),
        birthdateField.getText(),
        registerDateField.getText(),
        genderField.getText(),
        locationField.getText());
    repository.update(rGuests);
    RegisterGuests selected = tableView.getSelectionModel().getSelectedItem();
    selected.setName(nameField.getText());
    selected.setUsername(usernameField.getText());
    selected.setEmail(emailField.getText());
    selected.setPassword(passwordField.getText());
    selected.setBirthdate(birthdateField.getText());
    selected.setRegisteredDate(passwordField.getText());
    selected.setGender(genderField.getText());
    selected.setLocation(locationField.getText());
    tableView.refresh();
    clearGuestFields();
  }

  @FXML
  public void onDeleteAction(ActionEvent e) throws Exception {
    int id = Integer.parseInt(idField.getText());

    String query = "DELETE FROM registerGuests WHERE id = ?";
    PreparedStatement statement = this.connection.prepareStatement(query);
    statement.setInt(1, id);

    if (statement.executeUpdate() != 1)
      throw new Exception("ERR_MULTIPLE_ROWS_AFFECTED");

    RegisterGuests selected = tableView.getSelectionModel().getSelectedItem();
    tableView.getItems().remove(selected);
    clearGuestFields();
    tableView.refresh();
  }
  public void initializeGuests() {
    this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    this.usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    this.birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    this.registeredDateColumn.setCellValueFactory(new PropertyValueFactory<>("registeredDate"));
    this.genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    this.locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
  }

  private ArrayList<RegisterGuests> loadGuests() throws SQLException {
    RegisterGuestsRepository repository = new RegisterGuestsRepository();
    return repository.findAll();
  }






}

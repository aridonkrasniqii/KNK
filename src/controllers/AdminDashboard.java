package controllers;

import Helpers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.*;
import model.RegisterGuests;
import model.view.*;
import repository.PaymentsRepository;
import repository.RegisterGuestsRepository;
import repository.RoomRepository;
import repository.ServicesTypeRepository;
import repository.StaffRepository;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import Database.DBConnection;

public class AdminDashboard implements Initializable {
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
  @FXML
  private Button guestBtn;
  @FXML
  private Button overviewBtn;
  @FXML
  private Button staffBtn;
  @FXML
  private Button roomsBtn;
  @FXML
  private Button paymentsBtn;
  @FXML
  private Button sevicesBtn;
  @FXML
  private Button logoutBtn;
  @FXML
  private AnchorPane staffPane;
  @FXML
  private Button CreateMemberBtn;
  @FXML
  private Button refreshTableView;

  // TODO:
  // --------------------- WE NEED TO ADD A OVERVIEWPANE ALSO
  // ------------------------------
  @FXML
  private AnchorPane overviewPane;
  @FXML
  private AnchorPane guestsPane;
  @FXML
  private GridPane chartGridPane;
  @FXML
  private PieChart serviceChart;
  @FXML
  private PieChart staffChart;
  @FXML
  private PieChart roomsChart;
  @FXML
  private PieChart roomsChart2;
  @FXML
  private Label statusi3;
  @FXML
  private Label statusi1;
  @FXML
  private Label statusi;
  @FXML
  private Label statusi2;
  @FXML
  private AnchorPane paymentsPane;
  @FXML
  private TableView<PaymentModel> paymentsTableView;
  @FXML
  private TableColumn<PaymentModel, Integer> payment_id;
  @FXML
  private TableColumn<PaymentModel, String> firstname;
  @FXML
  private TableColumn<PaymentModel, String> lastname;
  @FXML
  private TableColumn<PaymentModel, Date> date;
  @FXML
  private TableColumn<PaymentModel, Double> price1;
  @FXML
  private TableColumn<PaymentModel, Integer> isPayed;
  @FXML
  private DatePicker paymentDtPickerFilter;
  @FXML
  private Button paymentFilterBtn;
  public ObservableList<PaymentModel> paymentsObservableList = null;
  @FXML
  private TableView<Staff> showStaffTable;
  @FXML
  private TableColumn<Staff, Integer> col_id;
  @FXML
  private TableColumn<Staff, String> col_fname;
  @FXML
  private TableColumn<Staff, String> col_lname;
  @FXML
  private TableColumn<Staff, String> col_prsNum;
  @FXML
  private TableColumn<Staff, String> col_position;
  @FXML
  private TableColumn<Staff, String> col_bday;
  @FXML
  private TableColumn<Staff, String> col_phone;
  @FXML
  private TableColumn<Staff, Double> col_salary;
  @FXML
  private TableColumn<Staff, String> col_gender;
  @FXML
  private TableColumn gender;
  @FXML
  private ChoiceBox positionCB;
  @FXML
  private Button positionFilterBtn;
  public ObservableList<Staff> staffi = null;
  @FXML
  public AnchorPane roomsPane;
  @FXML
  private Button btnAddNewRoom;
  @FXML
  private TableView<Rooms> roomsTableView;
  @FXML
  private TableColumn<Rooms, Integer> roomNumber;
  @FXML
  private TableColumn<Rooms, Integer> floorNumber;
  @FXML
  private TableColumn<Rooms, Integer> capacity;
  @FXML
  private TableColumn<Rooms, Integer> bedNumber;
  @FXML
  private TableColumn<Rooms, String> roomType;
  @FXML
  private TableColumn<Rooms, Double> price;
  @FXML
  private ChoiceBox<String> roomNumberFilter;
  @FXML
  private ChoiceBox<String> roomCapacityFilter;
  @FXML
  private ChoiceBox<String> roomTypeFilter;
  @FXML
  private Button findRooms;
  public ObservableList<Rooms> room = null;
  @FXML
  private AnchorPane servicesPane;
  @FXML
  private Button addNewServiceBtn;
  @FXML
  private TableView<Service_Type> servicesTableView;
  @FXML
  private TableColumn<Service_Type, Integer> serviceID;
  @FXML
  private TableColumn<Service_Type, String> serviceName;
  @FXML
  private TableColumn<Service_Type, Double> servicePrice;
  @FXML
  private TableColumn<Service_Type, Integer> serviceQuantity;
  public ObservableList<Service_Type> serviceObservableList = null;

  public void handleButtonAction(ActionEvent actionEvent) throws Exception {

    if (actionEvent.getSource() == guestBtn) {
      guestsPane.toFront();
    }

    if (actionEvent.getSource() == overviewBtn) {
      overviewPane.toFront();

      return;
    } else if (actionEvent.getSource() == staffBtn) {
      staffPane.toFront();
      // CreateMemberBtn.setOnAction(e -> {
      // CreateStaffMemberView cw = null;
      // try {
      // cw = new CreateStaffMemberView();

      // } catch (Exception ex) {
      // ex.printStackTrace();
      // }
      // try {
      // cw.display(staffi);
      // } catch (IOException ex) {
      // ex.printStackTrace();
      // } catch (SQLException ex) {
      // ex.printStackTrace();
      // }
      // });

    } else if (actionEvent.getSource() == roomsBtn) {

      roomsPane.toFront();
      // btnAddNewRoom.setOnAction(e -> {

      // });

    } else if (actionEvent.getSource() == sevicesBtn) {
      servicesPane.toFront();
      // addNewServiceBtn.setOnAction(e -> {

      // });
    } else if (actionEvent.getSource() == paymentsBtn) {
      paymentsPane.toFront();
      // paymentFilterBtn.setOnAction(e -> {

      // });
    } else if (actionEvent.getSource() == logoutBtn) {

      Parent parent = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
      Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
      Scene scene = new Scene(parent);
      stage.setScene(scene);
      stage.show();
    }

  }

  public void initialize(URL url, ResourceBundle resourceBundle) {
    overviewPane.toFront();

    try {

      // 1. load guests members
      this.connection = DBConnection.getConnection();
      initializeGuests();
      ObservableList<RegisterGuests> guests = FXCollections.observableArrayList(loadGuests());
      tableView.setItems(guests);
      // when we select a row text fields will be updated
      tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      tableView.getSelectionModel().selectedItemProperty().addListener((observe, old, _new) -> {
        if (_new != null) {
          renderGuests(_new);
        }
      });

      // 2. load staff members
      initializeStaff();
      ObservableList<Staff> staffs = FXCollections.observableArrayList(loadStaff());
      showStaffTable.setItems(staffs);

      // 3. load room rows
      initializeRooms();
      ObservableList<Rooms> rooms = FXCollections.observableArrayList(loadRooms());
      roomsTableView.setItems(rooms);

      // 4. load payments
      initializePayments();
      ObservableList<PaymentModel> paymentsModel = FXCollections.observableArrayList(loadPayments());
      paymentsTableView.setItems(paymentsModel);

      // 5. load services types
      initializeServiceTypes();
      ObservableList<Service_Type> service_types = FXCollections.observableArrayList(loadServicesTypes());
      servicesTableView.setItems(service_types);

    } catch (ClassNotFoundException e1) {
      System.out.println(e1);
    } catch (SQLException e1) {
      System.out.println(e1);
    } catch (Exception e) {
      System.err.println(e);
    }

  }

  // when item is selected this method upadate text fields with data
  // -------------------------------------------------- \\
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
    clearFields();
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
    clearFields();
    tableView.refresh();
  }

  // -------------------------------------------------- \\

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
    ArrayList<RegisterGuests> guests = repository.findAll();
    return guests;
  }

  public void initializeStaff() {
    this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.col_fname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    this.col_lname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
    this.col_prsNum.setCellValueFactory(new PropertyValueFactory<>("personal_number"));
    this.col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
    this.col_bday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    this.col_phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
    this.col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    this.col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
  }

  public ArrayList<Staff> loadStaff() throws Exception {

    StaffRepository repository = new StaffRepository();
    ArrayList<Staff> staff = repository.findAll();
    return staff;

  }

  public void initializeRooms() {
    this.roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
    this.floorNumber.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
    this.capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    this.bedNumber.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
    this.roomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
    this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
  }

  public ArrayList<Rooms> loadRooms() throws SQLException {
    RoomRepository repository = new RoomRepository();
    ArrayList<Rooms> rooms = repository.findAll();
    return rooms;
  }

  public void initializePayments() {
    this.payment_id.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
    this.firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    this.lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    this.date.setCellValueFactory(new PropertyValueFactory<>("date"));
    this.price1.setCellValueFactory(new PropertyValueFactory<>("price"));
    this.isPayed.setCellValueFactory(new PropertyValueFactory<>("ispayed"));
  }

  public ArrayList<PaymentModel> loadPayments() throws SQLException {
    PaymentsRepository repository = new PaymentsRepository();
    ArrayList<PaymentModel> paymentModel = repository.findAll();
    return paymentModel;
  }

  public void initializeServiceTypes() {
    this.serviceID.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.serviceName.setCellValueFactory(new PropertyValueFactory<>("service_name"));
    this.servicePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    this.serviceQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
  }

  public ArrayList<Service_Type> loadServicesTypes() throws SQLException, Exception {
    ServicesTypeRepository repository = new ServicesTypeRepository();
    ArrayList<Service_Type> service_types = repository.findAll();
    return service_types;
  }

}

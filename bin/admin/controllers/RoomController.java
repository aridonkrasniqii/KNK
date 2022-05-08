package admin.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helpers.Rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import repositories.RoomRepository;

public class RoomController implements Initializable{
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      initializeRooms();
      ObservableList<Rooms> staffs = FXCollections.observableArrayList(loadRooms());
      roomsTableView.setItems(staffs);
    } catch (Exception ex) {
      System.out.println(ex);
    }
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
    return repository.findAll();
  }


}

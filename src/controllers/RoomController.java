package controllers;

import components.ErrorPopupComponent;
import helpers.Reservation;
import helpers.Rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repositories.ReservationRepository;
import repositories.RoomRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RoomController implements Initializable {

    private static final String RESERVATION_ROOM_VIEW = "reservation-rooms.fxml";

    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;
    @FXML
    private ChoiceBox roomTypeSelector;
    @FXML
    private TableView<Rooms> tableView;
    @FXML
    private TableColumn<Rooms, Integer> roomNumberCol;
    @FXML
    private TableColumn<Rooms, Integer> roomFloorCol;
    @FXML
    private TableColumn<Rooms, Integer> capacityCol;
    @FXML
    private TableColumn<Rooms, Integer> bedNumberCol;
    @FXML
    private TableColumn<Rooms, String> roomTypeCol;
    @FXML
    private TableColumn<Rooms, Double> priceCol;
    //    ObservableList<String> roomTypesList = FXCollections.observableArrayList("All","Single","Double","Triple","Quad","Suite");
    ObservableList<Rooms> rooms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeRooms();
            rooms = FXCollections.observableArrayList(loadRooms());

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void onFindAction(ActionEvent e) throws Exception {
        String checkIn = checkInDate.getValue().toString();
        String checkOut = checkOutDate.getValue().toString();
        String roomType = roomTypeSelector.getValue().toString();

        ArrayList<Rooms> filteredRooms = RoomRepository.filterAvailableRooms(checkIn, checkOut, roomType);

        rooms = FXCollections.observableArrayList(filteredRooms);
        tableView.setItems(filteredRooms);
        tableView.refresh();
    }

    @FXML
    private void onMakeReservationAction(ActionEvent e) throws Exception {

        Rooms selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        Rooms available = RoomRepository.findAvailableRoom(selected.getRoom_number());

        if (available != null) {
            // make reservation

        } else {
            ErrorPopupComponent.show("Room is not available !");
        }
    }


    @FXML
    private void onCancleAction(ActionEvent e) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource(setPath(RESERVATION_ROOM_VIEW)));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    public void initializeRooms() {
        this.roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        this.roomFloorCol.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        this.capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        this.bedNumberCol.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        this.roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        this.priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private ArrayList<Rooms> loadRooms() throws SQLException {
        RoomRepository repository = new RoomRepository();
        return repository.findAll();
    }

    private String setPath(String path) {
        return "../views/" + path + ".fxml";
    }


}

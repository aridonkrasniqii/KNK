package controllers;

import components.ErrorPopupComponent;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import repositories.RoomRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationRoomsController implements Initializable {

    private static final String MAIN_VIEW = "main-view";
    private static final String MAKE_RESERVATION_VIEW = "make-reservation-view";

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

    ObservableList<Rooms> rooms;

    ObservableList<String> roomTypeSelectorList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeRooms();
            rooms = FXCollections.observableArrayList(loadRooms());
            roomTypeSelector.setItems(roomTypeSelectorList);
            tableView.setItems(rooms);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void onFindAction(ActionEvent e) throws Exception {
        String checkIn = checkInDate.getValue().toString();
        String checkOut = checkOutDate.getValue().toString();
        String roomType = roomTypeSelector.getValue().toString();
        if (checkIn == null || checkOut == null || roomType == null) return;

        ArrayList<Rooms> filteredRooms = RoomRepository.filterAvailableRooms(checkIn, checkOut, roomType);

        rooms = FXCollections.observableArrayList(filteredRooms);
        tableView.setItems(rooms);
        tableView.refresh();
    }

    @FXML
    private void onMakeReservationAction(ActionEvent e) throws Exception {
        String checkIn = null;
        String checkOut = null;
        String type = null;
        try {
             checkIn = checkInDate.getValue().toString();
             checkOut = checkOutDate.getValue().toString();
             type = roomTypeSelector.getValue().toString();


            Rooms selected = tableView.getSelectionModel().getSelectedItem();
            if (selected == null) return;

            Rooms availableRoom = RoomRepository.findAvailableRoom(selected.getRoom_number(), checkIn, checkOut, type);


            if (availableRoom != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(setPath(MAKE_RESERVATION_VIEW)));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);

                double price = selected.getPrice();

                //FIXME: controll if checkOut is before checkIn

                MakeReservationController controller = loader.getController();
                controller.setData(checkIn, checkOut, price, selected.getRoom_number());

                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                return;
            } else {
           
                ErrorPopupComponent.show("Room is reserved");
            }
        } catch (Exception ex) {
            if (checkIn == null || checkOut == null || type == null) {
                ErrorPopupComponent.show("Check In and Check Out must be filled");
            }
        }

    }


    @FXML
    private void onCancleAction(ActionEvent e) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource(setPath(MAIN_VIEW)));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    @FXML
    private void onRoomDetailsAction(ActionEvent e) throws Exception {
        Rooms selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/room-details.fxml"));
        Parent parent = loader.load();
        RoomDetailsController controller = loader.getController();
        controller.setDate(selected.getRoom_number(), selected.getFloor_number(), selected.getBed_number(), selected.getPrice(), 1);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.show();

    }


    private void initializeRooms() {
        roomTypeSelectorList = FXCollections.observableArrayList("All", "Single", "Double", "Triple", "Quad", "Suite");
        this.roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        this.roomFloorCol.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
        this.capacityCol.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        this.bedNumberCol.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
        this.roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("room_type"));
        this.priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private ArrayList<Rooms> loadRooms() throws Exception {
        RoomRepository repository = new RoomRepository();
        if (repository.findAll() == null) return null;

        return repository.findAll();
    }

    private String setPath(String path) {
        return "../views/" + path + ".fxml";
    }


}

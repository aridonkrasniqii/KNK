package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import models.Rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import processor.DateHelper;
import repositories.RoomRepository;
import utilities.I18N;

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
	@FXML
	private Label checkInLbl;
	@FXML
	private Label checkOutLbl;
	@FXML
	private Button findButtonId;
	@FXML
	private Button makeReservation;
	@FXML
	private Button roomDetails;

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

		roomNumberCol.textProperty().bind(I18N.createStringBinding("roomNumberCol"));
		roomFloorCol.textProperty().bind(I18N.createStringBinding("roomFloorCol"));
		capacityCol.textProperty().bind(I18N.createStringBinding("capacityCol"));
		bedNumberCol.textProperty().bind(I18N.createStringBinding("bedNumberCol"));
		roomTypeCol.textProperty().bind(I18N.createStringBinding("roomTypeCol"));
		priceCol.textProperty().bind(I18N.createStringBinding("priceCol"));
		checkInLbl.textProperty().bind(I18N.createStringBinding("checkInLbl"));
		checkOutLbl.textProperty().bind(I18N.createStringBinding("checkOutLbl"));
		findButtonId.textProperty().bind(I18N.createStringBinding("findButtonId"));
		makeReservation.textProperty().bind(I18N.createStringBinding("makeReservation"));
		roomDetails.textProperty().bind(I18N.createStringBinding("roomDetails"));

	}

	@FXML
	private void onFindAction(ActionEvent e) throws Exception {
		String checkIn = null;
		String checkOut = null;
		String roomType = null;
		try {
			checkIn = checkInDate.getValue().toString();
			checkOut = checkOutDate.getValue().toString();
			roomType = roomTypeSelector.getValue().toString();
		}catch(Exception ex) {
			ErrorPopupComponent.show("All fields must be filled !");
			return;
		}

		if (checkIn == null || checkOut == null || roomType == null) {
			ErrorPopupComponent.show("All fields must be filled !");
			return;
		}

		assert roomType != null;

		ArrayList<Rooms> filteredRooms = RoomRepository.filterAvailableRooms(checkIn, checkOut, roomType);
		if(filteredRooms != null ) {
			rooms = FXCollections.observableArrayList(filteredRooms);
			tableView.setItems(rooms);
			tableView.refresh();
		}else {
			ErrorPopupComponent.show("No room available");
		}


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
			if (selected == null)
				return;

			Rooms availableRoom = RoomRepository.findAvailableRoom(selected.getRoom_number(), checkIn, checkOut, type);

			if (availableRoom != null) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(setPath(MAKE_RESERVATION_VIEW)));
				Parent parent = loader.load();
				Scene scene = new Scene(parent);

				double price = availableRoom.getPrice();

				Date chInDate = DateHelper.fromSqlDate(checkIn);
				Date chOutDate = DateHelper.fromSqlDate(checkOut);
				if (chOutDate.compareTo(chInDate) < 0) {
					ErrorPopupComponent.show("CheckOut should be after checkIn ");
					return;
				}

				MakeReservationController controller = loader.getController();
				controller.setData(availableRoom.getRoom_number(), checkIn, checkOut, availableRoom.getRoom_type(),
						price);

				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);

			} else {
				ErrorPopupComponent.show("Room is reserved");
			}
		} catch (Exception ex) {
			if (checkIn == null || checkOut == null || type == null) {
				ErrorPopupComponent.show("Specific fields must be filled ");
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
		if (selected == null)
			return;

		try {
			String checkIn = checkInDate.getValue().toString();
			String checkOut = checkOutDate.getValue().toString();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../views/room-details.fxml"));
			Parent parent = loader.load();
			RoomDetailsController controller = loader.getController();
			controller.setReservationData(selected.getRoom_number(), selected.getFloor_number(), selected.getBed_number(),
					selected.getRoom_type(), selected.getPrice(), selected.getRoom_number()  ,checkIn, checkOut );

			Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			stage.setScene(new Scene(parent));

		}catch (Exception ex) {
			ErrorPopupComponent.show("Specify fields!");
		}

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
		if (repository.findAll() == null)
			return null;

		return repository.findAll();
	}

	private String setPath(String path) {
		return "../views/" + path + ".fxml";
	}

}

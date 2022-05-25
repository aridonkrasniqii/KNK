package admin.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import admin.controllers.rooms.EditRoomController;
import components.ErrorPopupComponent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Rooms;
import repositories.RoomRepository;
import utilities.I18N;

public class RoomController implements Initializable {
	@FXML
	public AnchorPane roomsPane;

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

	@FXML
	private Button addRoomBtn;
	@FXML
	private Button editRoomBtn;
	@FXML
	private Label roomsTitle;
	@FXML
	private Label roomTypeTitle;
	@FXML
	private Label bedNumTitle;
	@FXML
	private Label capacityTitle;

	@SuppressWarnings("rawtypes")
	private ObservableList roomNumberList, roomTypeSelectorList, roomBedNumberList;

	private ObservableList<Rooms> rooms;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initializeRooms();
			roomTypeFilter.setItems(roomTypeSelectorList);
			roomNumberFilter.setItems(roomNumberList);
			roomCapacityFilter.setItems(roomBedNumberList);
			rooms = FXCollections.observableArrayList(loadRooms());
			roomsTableView.setItems(rooms);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		roomNumber.textProperty().bind(I18N.createStringBinding("roomNumberCol"));
		floorNumber.textProperty().bind(I18N.createStringBinding("floorNumber"));
		capacity.textProperty().bind(I18N.createStringBinding("capacityCol"));
		bedNumber.textProperty().bind(I18N.createStringBinding("bedNumberCol"));
		roomType.textProperty().bind(I18N.createStringBinding("roomTypeCol"));
		price.textProperty().bind(I18N.createStringBinding("priceCol"));
		findRooms.textProperty().bind(I18N.createStringBinding("findRooms"));
		addRoomBtn.textProperty().bind(I18N.createStringBinding("addRoomBtn"));
		editRoomBtn.textProperty().bind(I18N.createStringBinding("editRoomBtn"));
		roomsTitle.textProperty().bind(I18N.createStringBinding("roomsButton"));
		capacityTitle.textProperty().bind(I18N.createStringBinding("capacityCol"));
		bedNumTitle.textProperty().bind(I18N.createStringBinding("bedNumberCol"));
		roomTypeTitle.textProperty().bind(I18N.createStringBinding("roomTypeCol"));

	}

	public void initializeRooms() {
		roomTypeSelectorList = FXCollections.observableArrayList("All", "Single", "Double", "Triple", "Quad", "Suite");
		roomBedNumberList = FXCollections.observableArrayList("All", "1", "2", "3", "4", "5");
		roomNumberList = FXCollections.observableArrayList("All", "1", "2", "3", "4", "5");
		this.roomNumber.setCellValueFactory(new PropertyValueFactory<>("room_number"));
		this.floorNumber.setCellValueFactory(new PropertyValueFactory<>("floor_number"));
		this.capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		this.bedNumber.setCellValueFactory(new PropertyValueFactory<>("bed_number"));
		this.roomType.setCellValueFactory(new PropertyValueFactory<>("room_type"));
		this.price.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	public ArrayList<Rooms> loadRooms() throws Exception {
		RoomRepository repository = new RoomRepository();
		return repository.findAll();
	}

	@FXML
	private void addRoomAction(ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/roomviews/add-new-room.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}

	@FXML
	private void editRoomAction(ActionEvent e) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/roomviews/edit-room.fxml"));
		Parent parent = loader.load();
		EditRoomController controller = loader.getController();
		Rooms selected = roomsTableView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}

		controller.setData(selected);

		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}

	@FXML
	private void onFindRoomAction(ActionEvent e) throws Exception {

		String type = null;
		String capacity = null;
		String bedNumber = null;
		try {
			type = roomTypeFilter.getValue().toString();
			;
			capacity = roomCapacityFilter.getValue().toString();
			bedNumber = roomNumberFilter.getValue().toString();
			;
		} catch (Exception ex) {
			ErrorPopupComponent.show("Specify fields");
			return;
		}

		if (type == null || capacity == null || bedNumber == null) {
			ErrorPopupComponent.show("Specify fields");
			return;
		}

		ArrayList<Rooms> filteredRooms = RoomRepository.filterAllRooms(type, bedNumber, capacity);

		if (filteredRooms == null)
			return;

		rooms = FXCollections.observableArrayList(filteredRooms);
		roomsTableView.setItems(rooms);
		roomsTableView.refresh();
	}

}

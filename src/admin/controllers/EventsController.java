package admin.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import admin.controllers.events.EditEventController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Events;
import repositories.EventsRepository;
import utilities.I18N;


public class EventsController implements Initializable {
	@FXML
	public AnchorPane eventsPane;
	@FXML
	private Button btnAddNewEvent;
	@FXML
	private Button btnEditEvent;
	@FXML
	private TableView<Events> eventsTableView;
	@FXML
	private TableColumn<Events, String> titleCol;
	@FXML
	private TableColumn<Events, String> organizerCol;
	@FXML
	private TableColumn<Events, String> categoryCol;
	@FXML
	private TableColumn<Events, Double> priceCol;
	@FXML
	private TableColumn<Events, Date> startDateCol;
	@FXML
	private TableColumn<Events, Date> endDateCol;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initializeEvents();
			ObservableList<Events> staffs = FXCollections.observableArrayList(loadEvents());
			eventsTableView.setItems(staffs);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		titleCol.textProperty().bind(I18N.createStringBinding("titleCol"));
		organizerCol.textProperty().bind(I18N.createStringBinding("organizerCol"));
		categoryCol.textProperty().bind(I18N.createStringBinding("categoryCol"));
		priceCol.textProperty().bind(I18N.createStringBinding("priceCol"));
		startDateCol.textProperty().bind(I18N.createStringBinding("startDateCol"));
		endDateCol.textProperty().bind(I18N.createStringBinding("endDateCol"));
		btnAddNewEvent.textProperty().bind(I18N.createStringBinding("btnAddNewEvent"));
		btnEditEvent.textProperty().bind(I18N.createStringBinding("btnEditEvent"));
	}

	public void initializeEvents() {
		this.titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		this.organizerCol.setCellValueFactory(new PropertyValueFactory<>("organizer"));
		this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		this.priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.startDateCol.setCellValueFactory(new PropertyValueFactory<>("start_date"));
		this.endDateCol.setCellValueFactory(new PropertyValueFactory<>("end_date"));

	}

	public ArrayList<Events> loadEvents() throws Exception {
		EventsRepository repository = new EventsRepository();
		return repository.findAll();
	}

	@FXML
	private void addEventAction(ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/eventviews/add-new-event.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);

	}

	@FXML
	private void editEventAction(ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../views/eventviews/edit-event.fxml"));
		Parent parent = loader.load();

		EditEventController controller = loader.getController();
		Events selected = eventsTableView.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		controller.setData(selected);
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
	}

}

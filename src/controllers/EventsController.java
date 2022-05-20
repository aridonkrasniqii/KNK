package controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import admin.controllers.events.*;
import models.Events;
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
import repositories.EventsRepository;

public class EventsController implements Initializable {
    @FXML
    public AnchorPane eventsPane;
    @FXML
    private Button btnAddNewEvent;
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
//            roomTypeFilter.setItems(roomTypeSelectorList);
//            roomNumberFilter.setItems(roomNumberList);
//            roomCapacityFilter.setItems(roomBedNumberList);
            ObservableList<Events> staffs = FXCollections.observableArrayList(loadEvents());
            eventsTableView.setItems(staffs);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void initializeEvents() {
//        roomTypeSelectorList = FXCollections.observableArrayList("All","Single","Double","Triple","Quad","Suite");
//        roomBedNumberList = FXCollections.observableArrayList("1","2","3","4");
//        roomNumberList = FXCollections.observableArrayList("1", "2" ,"3", "4", "5","6","7", "8", "9", "10");
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
        if(selected == null) {
            return;
        }

        controller.setData(selected);

        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


}

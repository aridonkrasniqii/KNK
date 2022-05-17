package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Events;
import repositories.EventsRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EventController implements Initializable {


    @FXML
    private TableView<Events> eventsTableView;

    @FXML

    private TableColumn<Events, String> titleCol;
    @FXML

    private TableColumn<Events , String> organizerCol;
    @FXML

    private TableColumn<Events , String> categoryCol;
    @FXML

    private TableColumn<Events , Double> priceCol;
    @FXML
    private TableColumn<Events , Date> startDateCol;

    ObservableList<Events> eventsList;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializeEvents();
            eventsList = FXCollections.observableArrayList(loadEvents());
            eventsTableView.setItems(eventsList);

        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }



    private void initializeEvents() {
        this.titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.organizerCol.setCellValueFactory(new PropertyValueFactory<>("organizer"));
        this.categoryCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.priceCol.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        this.startDateCol.setCellValueFactory(new PropertyValueFactory<>("end_date"));
    }

    private ArrayList<Events> loadEvents() throws  Exception
    {
        ArrayList<Events> events = EventsRepository.findAll();
        if(events != null){
            return  events;
        }
        return null;
    }



}




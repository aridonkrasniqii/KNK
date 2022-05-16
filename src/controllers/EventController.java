package controllers;

import com.mysql.cj.xdevapi.Table;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Events;

import java.util.Date;

public class EventController {


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









}




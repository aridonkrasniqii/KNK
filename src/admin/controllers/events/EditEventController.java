package admin.controllers.events;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import database.UpdateQueryBuilder;
import models.Events;
//import models.Rooms;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import processor.DateHelper;
import repositories.EventsRepository;
import repositories.RoomRepository;

import java.net.URL;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
public class EditEventController {
    @FXML
    private TextField titleCol;
    @FXML
    private TextField organizerCol;
    @FXML
    private TextField categoryCol;

    @FXML
    private TextField priceCol;
    
    @FXML
    private DatePicker startDateCol;
    
    @FXML
    private DatePicker endDateCol;



    @FXML
    private void updateEvent(ActionEvent e ) throws Exception{    	
    	try {
        	String titleStr = titleCol.getText();
        	String organizerStr = organizerCol.getText();
        	String categoryStr = categoryCol.getText();
            double price = Double.parseDouble(priceCol.getText());
            Date startDate = DateHelper.fromSqlDate(startDateCol.getValue().toString());
            Date endDate = DateHelper.fromSqlDate(endDateCol.getValue().toString());
            Events event = new Events(1, titleStr, organizerStr, categoryStr, price, startDate, endDate);
            
            if (EventsRepository.update(event) != null) {
                SuccessPopupComponent.show("Successfully edited " , "Edited");
            }
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }

    }

    public void setData(Events event){
    	titleCol.setText(event.getTitle());
    	organizerCol.setText(event.getOrganizer());
    	categoryCol.setText(event.getCategory());
    	priceCol.setText(Double.toString(event.getPrice()));
    	startDateCol.setValue(null);
    	endDateCol.setValue(null);
    }
    @FXML
    private void cancleButton(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../views/admin-screen.fxml"));
        Parent parent = loader.load();
        MainController controller = loader.getController();
        controller.setView(MainController.EVENTS_DASHBOARD);

        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
}

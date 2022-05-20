package admin.controllers.events;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import database.UpdateQueryBuilder;
import helpers.Events;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import processor.DateHelper;
import repositories.EventsRepository;
import repositories.RoomRepository;

import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;
public class EditEventController implements Initializable {
    @FXML
    private TextField titleCol;
    @FXML
    private TextField organizerCol;
    @FXML
    private TextField categoryCol;
//    @FXML
//    private TextField bedNumber;
//    @FXML
//    private ChoiceBox roomType;
    @FXML
    private TextField priceCol;
    
    @FXML
    private DatePicker startDateCol;
    
    @FXML
    private DatePicker endDateCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTypes();
    }
    private void loadTypes() {
//        ObservableList roomTypeSelectorList = FXCollections.observableArrayList("All","Single","Double","Triple","Quad","Suite");
//        roomType.setItems(roomTypeSelectorList);
//        roomType.setValue("None");
    }

    @FXML
    private void updateEvent(ActionEvent e ) throws Exception{
//        int title = Integer.parseInt(roomNumber.getText());
//        int floorNum = Integer.parseInt(floorNumber.getText());
//        int roomCpc = Integer.parseInt(roomCapacity.getText());
//        int bedNum = Integer.parseInt(bedNumber.getText());
//        String roomTp = roomType.getValue().toString();
//        double prc = Double.parseDouble(price.getText());
//        Events event = new Events(roomNum, floorNum, roomCpc, bedNum, roomTp, prc);
//
//        if(EventsRepository.update(event) != null) {
//            SuccessPopupComponent.show("Successfully edited " , "Edited");
//        }else {
//            ErrorPopupComponent.show("Failed to edit");
//        }
    	
    	try {
        	String titleStr = titleCol.getText();
        	String organizerStr = organizerCol.getText();
        	String categoryStr = categoryCol.getText();
            double price = Double.parseDouble(priceCol.getText());
            String startDate = startDateCol.getValue().toString();
            String endDate = endDateCol.getValue().toString();
//            Rooms room = new Rooms(roomNum, floorNum, roomCpc, bedNum, roomTp, prc);
            Events event = new Events(titleStr, organizerStr, categoryStr, price, startDate, endDate);
            
            if (EventsRepository.update(event) != null) {
                EventsRepository.create(event);
                SuccessPopupComponent.show("Successfully edited " , "Edited");
            }
        } catch (Exception ex) {
        	ErrorPopupComponent.show("Failed to edit");
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
        controller.setView(MainController.ROOMS_DASHBOARD);

        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }
}

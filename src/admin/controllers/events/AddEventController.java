package admin.controllers.events;

import admin.controllers.MainController;

import com.mysql.cj.x.protobuf.MysqlxExpr.Identifier;
import com.sun.tools.javac.Main;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Events;
//import models.Events;
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
import repositories.EventsRepository;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
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
//        loadTypes();

    }

    private void loadTypes() {
//        roomType.getItems().add("First Class");
//        roomType.getItems().add("Second Class");
//        roomType.getItems().add("Third Class");
//        roomType.setValue("None");
    }

    @FXML
    private void addNewEvent(ActionEvent e)  {
        try {
        	String titleStr = titleCol.getText();
        	String organizerStr = organizerCol.getText();
        	String categoryStr = categoryCol.getText();
            double price = Double.parseDouble(priceCol.getText());
//            Date startDate = startDateCol;
//            Date endDate = endDateCol;
            String startDate = startDateCol.getValue().toString();
            String endDate = endDateCol.getValue().toString();
//            Rooms room = new Rooms(roomNum, floorNum, roomCpc, bedNum, roomTp, prc);
            Events event = new Events(titleStr, organizerStr, categoryStr, price, startDate, endDate);
            
            if (event != null) {
                EventsRepository.create(event);
                SuccessPopupComponent.show("Successfully created event 😃", "Created");
            }
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }
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

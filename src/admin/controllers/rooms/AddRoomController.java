package admin.controllers.rooms;


import admin.controllers.MainController;
import com.sun.tools.javac.Main;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repositories.RoomRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {
    @FXML
    private TextField roomNumber;
    @FXML
    private TextField floorNumber;
    @FXML
    private TextField roomCapacity;
    @FXML
    private TextField bedNumber;
    @FXML
    private ChoiceBox roomType;
    @FXML
    private TextField price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTypes();

    }

    private void loadTypes() {
        roomType.getItems().add("First Class");
        roomType.getItems().add("Second Class");
        roomType.getItems().add("Third Class");
        roomType.setValue("None");
    }

    @FXML
    private void addNewRoom(ActionEvent e)  {
        try {
            int roomNum = Integer.parseInt(roomNumber.getText());
            int floorNum = Integer.parseInt(floorNumber.getText());
            int roomCpc = Integer.parseInt(roomCapacity.getText());
            int bedNum = Integer.parseInt(bedNumber.getText());
            String roomTp = roomType.getValue().toString();

            double prc = Double.parseDouble(price.getText());
            Rooms room = new Rooms(roomNum, floorNum, roomCpc, bedNum, roomTp, prc);
            if (room != null) {
                RoomRepository.create(room);
                SuccessPopupComponent.show("Successfully created room 😃", "Created");
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
        controller.setView(MainController.OVERVIEW_DASHBOARD);

        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);

    }

}

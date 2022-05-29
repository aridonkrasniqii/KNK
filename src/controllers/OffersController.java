package controllers;

import components.ErrorPopupComponent;
import models.Rooms;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import repositories.RoomRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class OffersController implements Initializable {

    @FXML
    private Pane mainPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadOffers();
        }catch(Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    public void loadOffers() throws Exception {
        GridPane offersPane = new GridPane();
        ArrayList<Rooms> rooms = RoomRepository.getOffers();
        int size = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/offers-room-view.fxml"));
                Parent parent = loader.load();
                OffersDetailsController controller = loader.getController();
                Rooms r = rooms.get(size);
                controller.setDate(r.getRoom_number(), r.getFloor_number(), r.getBed_number(), r.getRoom_type(),
                        r.getPrice(), size + 1);
                offersPane.add(parent, i, j);
                size += 1;
            }
        }


        mainPane.getChildren().clear();
        mainPane.getChildren().add(offersPane);

    }
}

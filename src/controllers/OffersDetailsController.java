package controllers;

import components.ErrorPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;


public class OffersDetailsController{

    @FXML
    private Label roomNum;

    @FXML
    private Label roomFlr;

    @FXML
    private Label bedNum;

    @FXML
    private Label priceNum;

    @FXML
    private Label roomType;

    @FXML
    private ImageView roomImageView;

    @FXML
    private void onBookAction(ActionEvent e) throws Exception {
        int room_number = Integer.parseInt(roomNum.getText());
        double price = Double.parseDouble(priceNum.getText());
        String room_type = roomType.getText();

         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("../views/make-reservation-view.fxml"));
         Parent parent = loader.load();
             MakeReservationController controller = loader.getController();
             controller.setData(room_number, room_type, price);

         Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
         stage.setScene(new Scene(parent));

    }


    public void setDate(int roomNr, int floorNr, int bedNr, String roomType, double price, int image) {
        roomNum.setText(Integer.toString(roomNr));
        roomFlr.setText(Integer.toString(floorNr));
        bedNum.setText((Integer.toString(bedNr)));
        priceNum.setText(Double.toString(price));
        this.roomType.setText(roomType);

        try {
            FileInputStream file = new FileInputStream(setImagePath(image));
            Image img = new Image(file);
            roomImageView.setImage(img);
        } catch (Exception ex) {
            ErrorPopupComponent.show(ex);
        }

    }

    private String setImagePath(int image) {
        return "images/roomOffers/offers-room" + image + ".jpeg";
    }


}




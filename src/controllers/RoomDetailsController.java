package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import helpers.Rooms;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RoomDetailsController implements Initializable {


    @FXML
    private Label roomNum;

    @FXML
    private Label roomFlr;

    @FXML
    private Label bedNum;

    @FXML
    private Label priceNum;

    @FXML
    private ImageView roomImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void onBookAction(ActionEvent e) throws Exception {

    }


    public void setDate(int roomNr,int floorNr, int bedNr, double price , int image) {
        roomNum.setText(Integer.toString(roomNr));
        roomFlr.setText(Integer.toString(floorNr));
        bedNum.setText((Integer.toString(bedNr)));
        priceNum.setText(Double.toString(price));
        try{
            FileInputStream file = new FileInputStream(setImagePath(image));
            Image img = new Image(file);
            roomImageView.setImage(img);
        }catch (Exception ex){
            ErrorPopupComponent.show(ex);
        }


    }


    private String setImagePath(int image) {
        return "/home/teknikashi/Documents/GitHub/KNK/src/images/room-" + Integer.toString(image) + ".jpg";
    }
}
package controllers;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import models.Rooms;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import processor.DateHelper;
import repositories.RoomRepository;

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
	private Label roomType;

	@FXML
	private ImageView roomImageView;

	private String checkInDate;
	private String checkOutDate;

	private String type;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@FXML
	private void onBookAction(ActionEvent e) throws Exception {

		try {

			Rooms selected = RoomRepository.find(Integer.parseInt(roomNum.getText()));
			if (selected == null)
				return;

			Rooms availableRoom = RoomRepository.findAvailableRoom(selected.getRoom_number(), checkInDate, checkOutDate,
					type);

			if (availableRoom != null) {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../views/make-reservation-view.fxml"));
				Parent parent = loader.load();
				Scene scene = new Scene(parent);

				double price = selected.getPrice();

				Date chInDate = DateHelper.fromSqlDate(checkInDate);
				Date chOutDate = DateHelper.fromSqlDate(checkOutDate);
				if (chOutDate.compareTo(chInDate) < 0) {
					ErrorPopupComponent.show("CheckOut should be after checkIn ");
					return;
				}


				MakeReservationController controller = loader.getController();
				controller.setData(availableRoom.getRoom_number(), checkInDate, checkOutDate,
						availableRoom.getRoom_type(), availableRoom.getPrice());

				Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
				stage.setScene(scene);

			} else {
				ErrorPopupComponent.show("Room is reserved");
			}
		} catch (Exception ex) {

			ErrorPopupComponent.show("Specific fields must be filled go back and fill them :)");

		}

	}

	public void setReservationData(int roomNr, int floorNr, int bedNr, String roomType, double price, int image , String checkIn, String checkOut){
		this.checkInDate = checkIn;
		this.checkOutDate = checkOut;
		this.type = roomType;
		roomNum.setText(Integer.toString(roomNr));
		roomFlr.setText(Integer.toString(floorNr));
		bedNum.setText((Integer.toString(bedNr)));
		priceNum.setText(Double.toString(price));
		this.roomType.setText(roomType);

		try {
			FileInputStream file = new FileInputStream(setRoomImageView(image));
			Image img = new Image(file);
			roomImageView.setImage(img);
		} catch (Exception ex) {
			ErrorPopupComponent.show(ex);
		}
	}


	public void setDate(int roomNr, int floorNr, int bedNr, String roomType, double price, int image) {
		roomNum.setText(Integer.toString(roomNr));
		roomFlr.setText(Integer.toString(floorNr));
		bedNum.setText((Integer.toString(bedNr)));
		priceNum.setText(Double.toString(price));
		this.roomType.setText(roomType);

		try {
			FileInputStream file = new FileInputStream(setOfferImagePath(image));
			Image img = new Image(file);
			roomImageView.setImage(img);
		} catch (Exception ex) {
			ErrorPopupComponent.show(ex);
		}

	}

	public String setRoomImageView(int image){
		return "images/room-" + Integer.toString(image) + ".jpg";
	}
	private String setOfferImagePath(int image) {
		return "images/roomOffers/offers-room" + Integer.toString(image) + ".jpeg";
	}
}
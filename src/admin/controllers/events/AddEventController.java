package admin.controllers.events;

import java.util.Date;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Events;
import processor.DateHelper;
import repositories.EventsRepository;

public class AddEventController {
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
	private void addNewEvent(ActionEvent e) {
		try {
			String titleStr = titleCol.getText();
			String organizerStr = organizerCol.getText();
			String categoryStr = categoryCol.getText();
			double price = Double.parseDouble(priceCol.getText());

			Date startDate = DateHelper.fromSqlDate(startDateCol.getValue().toString());
			Date endDate = DateHelper.fromSqlDate(endDateCol.getValue().toString());

			Events event = new Events(0, titleStr, organizerStr, categoryStr, price, startDate, endDate);

			if (EventsRepository.create(event) != null) {
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
		controller.setView(MainController.EVENTS_DASHBOARD);

		Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);

	}

}

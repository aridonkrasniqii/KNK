package admin.controllers.services;

import admin.controllers.MainController;
import helpers.Service_Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditServiceController implements Initializable {


    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void setData(Service_Type service ) throws Exception {
        // maybe we need to add id field
        serviceField.setText(service.getService_name());
        priceField.setText(Double.toString(service.getPrice()));
        quantityField.setText(Integer.toString(service.getQuantity()));

    }



    @FXML
    private void onEditAction(ActionEvent e) throws Exception {

        System.out.println("On edit action is clicked ");
    }

    @FXML
    private void onCancleAction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../views/admin-screen.fxml"));
        Parent parent = loader.load();

        MainController controller = loader.getController();
        controller.setView(MainController.SERVICE_TYPES_DASHBOARD);

        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
    }

}

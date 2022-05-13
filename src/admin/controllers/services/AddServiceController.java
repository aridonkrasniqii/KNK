package admin.controllers.services;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import helpers.Service_Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.event.*;
import javafx.stage.Stage;
import repositories.ServicesTypeRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddServiceController implements Initializable {


    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    private void addServiceButton(ActionEvent e ) {

        try {
            String service = serviceField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            Service_Type service_type = new Service_Type(0, service, price, quantity);
            if(service_type != null ) {
                ServicesTypeRepository.create(service_type);
                SuccessPopupComponent.show("Succesfully created service " , "Created");
            }
        }
        catch(Exception ex) {
            ErrorPopupComponent.show(ex);
        }
    }

    @FXML
    private void cancleButton(ActionEvent e ) throws Exception {
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

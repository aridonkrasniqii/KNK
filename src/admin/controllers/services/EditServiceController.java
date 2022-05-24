package admin.controllers.services;

import admin.controllers.MainController;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import models.Service_Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repositories.ServicesTypeRepository;

public class EditServiceController {


    @FXML
    private TextField serviceField;
    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    private int id;




    public void setData(Service_Type service ) throws Exception {
        id = service.getId();
        serviceField.setText(service.getService_name());
        priceField.setText(Double.toString(service.getPrice()));
        quantityField.setText(Integer.toString(service.getQuantity()));

    }



    @FXML
    private void onEditAction(ActionEvent e) throws Exception {
        String serviceName = serviceField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        Service_Type service = new Service_Type(id, serviceName , price, quantity);

        if(ServicesTypeRepository.update(service) != null) {
            SuccessPopupComponent.show("Successfully edited " , "Edited");
        }else {
            ErrorPopupComponent.show("Failed to edit");
        }

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

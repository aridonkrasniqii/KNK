package admin.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import admin.controllers.services.EditServiceController;
import helpers.Service_Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repositories.ServicesTypeRepository;
import utilities.I18N;

public class ServiceTypesController implements Initializable {

    @FXML
    private AnchorPane servicesPane;
    @FXML
    private Button addNewServiceBtn;
    @FXML
    private TableView<Service_Type> servicesTableView;
    @FXML
    private TableColumn<Service_Type, Integer> serviceID;
    @FXML
    private TableColumn<Service_Type, String> serviceName;
    @FXML
    private TableColumn<Service_Type, Double> servicePrice;
    @FXML
    private TableColumn<Service_Type, Integer> serviceQuantity;
    public ObservableList<Service_Type> serviceObservableList = null;

    @FXML
    private Button updateServiceBtn;
    @FXML
    private Button btnDelete1;
    @FXML
    private Label servicesTitle;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // 5. load services types
            initializeServiceTypes();
            ObservableList<Service_Type> service_types = FXCollections.observableArrayList(loadServicesTypes());
            servicesTableView.setItems(service_types);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        serviceID.textProperty().bind(I18N.createStringBinding("idColumn"));
        serviceName.textProperty().bind(I18N.createStringBinding("serviceName"));
        servicePrice.textProperty().bind(I18N.createStringBinding("priceCol"));

        serviceQuantity.textProperty().bind(I18N.createStringBinding("quantity"));
        addNewServiceBtn.textProperty().bind(I18N.createStringBinding("addNewServiceBtn"));
        btnDelete1.textProperty().bind(I18N.createStringBinding("deleteBtn"));

        servicesTitle.textProperty().bind(I18N.createStringBinding("servicesButton"));

    }

    public void initializeServiceTypes() {
        this.serviceID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.serviceName.setCellValueFactory(new PropertyValueFactory<>("service_name"));
        this.servicePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.serviceQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public ArrayList<Service_Type> loadServicesTypes() throws SQLException, Exception {
        ServicesTypeRepository repository = new ServicesTypeRepository();
        return repository.findAll();
    }


    @FXML
    private void addServiceAction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/services/add-services.fxml"));

        Parent parent = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }


    @FXML
    private void updateServiceAction(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/services/edit-services.fxml"));
        Parent parent = loader.load();
        Service_Type selected = servicesTableView.getSelectionModel().getSelectedItem();
        if(selected == null )
            return;

        EditServiceController controller = loader.getController();
        controller.setData(selected);


        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
    }


    @FXML
    private void onRemoveAction(ActionEvent e) throws Exception {
        Service_Type selected = servicesTableView.getSelectionModel().getSelectedItem();
        int id = selected.getId();
        ServicesTypeRepository.remove(id);
        servicesTableView.getItems().remove(selected);
        servicesTableView.refresh();
    }

}

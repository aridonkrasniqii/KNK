package admin.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import helpers.Service_Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import repositories.ServicesTypeRepository;

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

}

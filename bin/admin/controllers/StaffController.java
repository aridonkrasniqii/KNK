
package admin.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import helpers.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import repositories.StaffRepository;

public class StaffController implements Initializable {
  // TODO:
  @FXML
  private ChoiceBox positionCB;
  @FXML
  private Button positionFilterBtn;

  @FXML
  private Button createMemberBtn;

  @FXML
  private AnchorPane showStaffPane;

  //
  @FXML
  private TableView<Staff> showStaffTable;
  @FXML
  private TableColumn<Staff, Integer> col_id;
  @FXML
  private TableColumn<Staff, String> col_fname;
  @FXML
  private TableColumn<Staff, String> col_lname;
  @FXML
  private TableColumn<Staff, String> col_prsNum;
  @FXML
  private TableColumn<Staff, String> col_position;
  @FXML
  private TableColumn<Staff, String> col_bday;
  @FXML
  private TableColumn<Staff, String> col_phone;
  @FXML
  private TableColumn<Staff, Double> col_salary;
  @FXML
  private TableColumn<Staff, String> col_gender;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      initializeStaff();
      ObservableList<Staff> staffs = FXCollections.observableArrayList(loadStaff());
      showStaffTable.setItems(staffs);
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public ArrayList<Staff> loadStaff() throws Exception {
    StaffRepository repository = new StaffRepository();
    return repository.findAll();
  }

  public void initializeStaff() {
    this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    this.col_fname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
    this.col_lname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
    this.col_prsNum.setCellValueFactory(new PropertyValueFactory<>("personal_number"));
    this.col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
    this.col_bday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    this.col_phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
    this.col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    this.col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
  }

}

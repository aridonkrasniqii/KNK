
package admin.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import admin.controllers.staff.EditStaffController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Staff;
import repositories.StaffRepository;

public class StaffController implements Initializable {

	private static final String ADD_STAFF_VIEW = "create-staff";
	private static final String EDIT_STAFF_VIEW = "edit-staff";
	private static final String STAFF_CARD_VIEW = "staff-card";

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox staffPosition;

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

	private ObservableList<Staff> staffs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initializeStaff();
			initPosition();
			staffs = FXCollections.observableArrayList(loadStaff());
			showStaffTable.setItems(staffs);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@SuppressWarnings("static-access")
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

	@FXML
	private void addStaffMemberBtn(ActionEvent e) throws Exception {
		setView(ADD_STAFF_VIEW, e);
	}

	@FXML
	private void editStaffMemberBtn(ActionEvent e) throws Exception {
		setView(EDIT_STAFF_VIEW, e);
	}

	@FXML
	private void cardUsersBtn(ActionEvent e) throws Exception {
		setView(STAFF_CARD_VIEW, e);
	}

	@FXML
	private void onPositionFilter(ActionEvent e) throws Exception {
		String position = staffPosition.getValue().toString();
		ArrayList<Staff> staffMembers = StaffRepository.filterPosition(position);
		staffs = FXCollections.observableArrayList(staffMembers);

		showStaffTable.setItems(staffs);
		showStaffTable.refresh();
	}

	@SuppressWarnings("static-access")
	@FXML
	private void onRefreshAction(ActionEvent e) throws Exception {
		StaffRepository repository = new StaffRepository();
		ArrayList<Staff> staffMembers = repository.findAll();
		staffs = FXCollections.observableArrayList(staffMembers);
		showStaffTable.setItems(staffs);
		showStaffTable.refresh();
	}

	@SuppressWarnings("unchecked")
	private void initPosition() {
		staffPosition.setValue("Filter Position");
		staffPosition.getItems().add("Software Engineer");
		staffPosition.getItems().add("Cyber Security Engineer");
		staffPosition.getItems().add("Front End Engineer");
		staffPosition.getItems().add("Back End Engineer");
	}

	private void setView(String path, ActionEvent e) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent parent = null;
		Stage stage = null;
		switch (path) {
		case ADD_STAFF_VIEW:
			loader.setLocation(getClass().getResource(setPath(ADD_STAFF_VIEW)));
			parent = loader.load();
			stage = initStage(e);
			stage.setScene(new Scene(parent));
			break;

		case EDIT_STAFF_VIEW:
			loader.setLocation(getClass().getResource(setPath(EDIT_STAFF_VIEW)));
			parent = loader.load();
			stage = initStage(e);
			Staff selected = showStaffTable.getSelectionModel().getSelectedItem();
			if (selected == null)
				return;

			EditStaffController controller = loader.getController();
			controller.setData(selected);
			stage.setScene(new Scene(parent));
			break;
		case STAFF_CARD_VIEW:
			loader.setLocation(getClass().getResource(setPath(STAFF_CARD_VIEW)));
			parent = loader.load();
			stage = initStage(e);
			stage.setScene(new Scene(parent));
			break;
		default:
			return;

		}

	}

	private String setPath(String path) {
		return "../views/staff/" + path + ".fxml";
	}

	private Stage initStage(ActionEvent e) throws Exception {
		return (Stage) ((Node) e.getSource()).getScene().getWindow();
	}

}

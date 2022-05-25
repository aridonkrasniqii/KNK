package admin.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import components.ErrorPopupComponent;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.User;
import models.UserRole;
import processor.DateHelper;
import repositories.UserRepository;
import utilities.I18N;

public class GuestController implements Initializable {
	private DBConnection connection;
	@FXML
	private TableView<User> tableView;
	@FXML
	private TableColumn<User, Integer> idColumn;
	@FXML
	private TableColumn<User, String> nameColumn;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<User, String> emailColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	@FXML
	private TableColumn<User, String> saltColumn;
	@FXML
	private TableColumn<User, UserRole> roleColumn;
	@FXML
	private TableColumn<User, Integer> isActiveColumn;
	@FXML
	private TableColumn<User, String> createdAtColumn;
	@FXML
	private TableColumn<User, String> updatedAtColumn;
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField usernameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField saltField;
	@FXML
	private TextField roleField;
	@FXML
	private TextField isActiveField;
	@FXML
	private TextField createdAtField;
	@FXML
	private TextField updatedAtField;
	@FXML
	private TextField searchField;
	@FXML
	private Label searchLbl;
	@FXML
	private Button createBtn;
	@FXML
	private Button updateBtn;
	@FXML
	private Button deleteBtn;

	@FXML
	private Label idLbl;
	@FXML
	private Label nameLbl;
	@FXML
	private Label usernameLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private Label passwordLbl;
	@FXML
	private Label saltLbl;
	@FXML
	private Label isActiveLbl;
	@FXML
	private Label updatedAtLbl;
	@FXML
	private Label createLbl;
	@FXML
	private Label roleLbl;

	SortedList<User> sortedData;
	ObservableList<User> users;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.connection = DBConnection.getConnection();
			initializeGuests();
			users = FXCollections.observableArrayList(loadGuests());

			tableView.setItems(users);

			tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			tableView.getSelectionModel().selectedItemProperty().addListener((observe, old, _new) -> {
				if (_new != null) {
					try {
						renderGuests(_new);
					} catch (Exception e) {
						ErrorPopupComponent.show(e);
					}
				}
			});

			filterData();
		} catch (Exception ex) {
			ErrorPopupComponent.show(ex);
		}

		nameField.promptTextProperty().bind(I18N.createStringBinding("nameField"));
		usernameField.promptTextProperty().bind(I18N.createStringBinding("usernameField"));
		emailField.promptTextProperty().bind(I18N.createStringBinding("emailField"));
		passwordField.promptTextProperty().bind(I18N.createStringBinding("passwordField"));
		createBtn.textProperty().bind(I18N.createStringBinding("createBtn"));
		updateBtn.textProperty().bind(I18N.createStringBinding("updateBtn"));
		searchLbl.textProperty().bind(I18N.createStringBinding("searchLbl"));
		deleteBtn.textProperty().bind(I18N.createStringBinding("deleteBtn"));

		idColumn.textProperty().bind(I18N.createStringBinding("idColumn"));
		nameColumn.textProperty().bind(I18N.createStringBinding("nameColumn"));
		usernameColumn.textProperty().bind(I18N.createStringBinding("usernameColumn"));
		emailColumn.textProperty().bind(I18N.createStringBinding("emailColumn"));
		passwordColumn.textProperty().bind(I18N.createStringBinding("passwordColumn"));
		saltColumn.textProperty().bind(I18N.createStringBinding("saltColumn"));
		roleColumn.textProperty().bind(I18N.createStringBinding("roleColumn"));
		isActiveColumn.textProperty().bind(I18N.createStringBinding("isActiveColumn"));
		createdAtColumn.textProperty().bind(I18N.createStringBinding("createdAtColumn"));
		updatedAtColumn.textProperty().bind(I18N.createStringBinding("updatedAtColumn"));

		idLbl.textProperty().bind(I18N.createStringBinding("idColumn"));
		nameLbl.textProperty().bind(I18N.createStringBinding("nameColumn"));
		usernameLbl.textProperty().bind(I18N.createStringBinding("usernameColumn"));
		emailLbl.textProperty().bind(I18N.createStringBinding("emailColumn"));
		passwordLbl.textProperty().bind(I18N.createStringBinding("passwordColumn"));
		saltLbl.textProperty().bind(I18N.createStringBinding("saltColumn"));
		roleLbl.textProperty().bind(I18N.createStringBinding("roleColumn"));
		isActiveLbl.textProperty().bind(I18N.createStringBinding("isActiveColumn"));
		createLbl.textProperty().bind(I18N.createStringBinding("createdAtColumn"));
		updatedAtLbl.textProperty().bind(I18N.createStringBinding("updatedAtColumn"));
	}

	private void renderGuests(User model) throws Exception {
		idField.setText(Integer.toString(model.getId()));
		nameField.setText(model.getName());
		usernameField.setText(model.getUsername());
		emailField.setText(model.getEmail());
		passwordField.setText(model.getPassword());
		saltField.setText(model.getSalt());
		roleField.setText(model.getRole() == UserRole.Guest ? "Guest" : "Admin");
		isActiveField.setText(Boolean.toString(model.getIsActive()));
		createdAtField.setText(DateHelper.toSql(model.getCreatedAt()));
		updatedAtField.setText(DateHelper.toSql(model.getUpdatedAt()));
	}

	public void clearGuestFields() {
		idField.clear();
		nameField.clear();
		usernameField.clear();
		emailField.clear();
		passwordField.clear();
		saltField.clear();
		roleField.clear();
		isActiveField.clear();
		createdAtField.clear();
		updatedAtField.clear();
		tableView.getSelectionModel().clearSelection();
	}

	@FXML
	public void onCreateAction(ActionEvent e) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../views/add-guest.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(parent));
	}

	@FXML
	private void onUpdateAction(ActionEvent e) throws Exception {

		User user = new User(Integer.parseInt(idField.getText()), nameField.getText(), emailField.getText(),
				usernameField.getText(), passwordField.getText(), saltField.getText(),
				roleField.getText().equals("Guest") ? UserRole.Guest : UserRole.Admin,
				Boolean.parseBoolean(isActiveField.getText()), DateHelper.fromSql(createdAtField.getText()),
				DateHelper.fromSql(updatedAtField.getText()));

		UserRepository.update(user);

		User selected = tableView.getSelectionModel().getSelectedItem();
		selected.setName(nameField.getText());
		selected.setUsername(usernameField.getText());
		selected.setEmail(emailField.getText());
		selected.setPassword(passwordField.getText());
		selected.setSalt(saltField.getText());
		selected.setRole(roleField.getText().equals("Guest") ? UserRole.Guest : UserRole.Admin);
		selected.setIsActive(isActiveField.getText().equals("true") ? true : false);
		selected.setCreatedAt(DateHelper.fromSql(createdAtField.getText()));
		selected.setUpdatedAt(DateHelper.fromSql(updatedAtField.getText()));
		tableView.refresh();
		filterData();
		clearGuestFields();
	}

	@FXML
	public void onDeleteAction(ActionEvent e) throws Exception {
		int id = Integer.parseInt(idField.getText());
		if (!UserRepository.remove(id))
			throw new Exception();

		ArrayList<User> userRepo = UserRepository.getAll();
		users = FXCollections.observableArrayList(userRepo);
		clearGuestFields();
		filterData();
		tableView.refresh();
	}

	public void filterData() {

		FilteredList<User> filteredData = new FilteredList<>(users, b -> true);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(searchModel -> {
				if (newValue.isEmpty() || newValue.isBlank() || newValue == null)
					return true;

				String searchKeyword = newValue.toLowerCase();

				if (Integer.toString(searchModel.getId()).toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if (searchModel.getEmail().toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if (searchModel.getUsername().toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if (searchModel.getName().toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if (searchModel.getSalt().toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if (searchModel.getPassword().toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else if ((searchModel.getRole() == UserRole.Admin ? "A" : "G").toLowerCase()
						.indexOf(searchKeyword) != -1) {
					return true;
				} else if ((searchModel.getIsActive() ? "true" : "false").toLowerCase().indexOf(searchKeyword) != -1) {
					return true;
				} else
					try {
						if ((DateHelper.toSqlDate(searchModel.getCreatedAt())).toLowerCase()
								.indexOf(searchKeyword) != -1) {
							return true;
						} else {
							return false;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return false;

			});
		});

		sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(tableView.comparatorProperty());

		tableView.setItems(sortedData);

	}

	public void initializeGuests() {
		this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		this.passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		this.saltColumn.setCellValueFactory(new PropertyValueFactory<>("salt"));
		this.roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
		this.isActiveColumn.setCellValueFactory(new PropertyValueFactory<>("isActive"));
		this.createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
		this.updatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));
	}

	private ArrayList<User> loadGuests() throws Exception {
		return UserRepository.getAll();
	}

}
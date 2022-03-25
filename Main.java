package application;
	
import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import java.util.Random;

public class LoginRegisterForm extends Application {
	
	TextField usernameField;
	TextField passwordField;
	@Override
	public void start(Stage primaryStage) {
		
		TabPane tabPane = new TabPane();
		Tab loginTab = new Tab("Login",new LoginForm());
		Tab registerTab = new Tab("Register" , new RegisterForm());
		
		loginTab.setClosable(false);
		registerTab.setClosable(false);
		tabPane.getTabs().addAll(loginTab,registerTab);
	
		primaryStage.setMinWidth(900);
		primaryStage.setMinHeight(700);
		primaryStage.setScene(new Scene(tabPane,900,700));
		primaryStage.setTitle("Application");
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

class LoginForm extends GridPane {
	
	private TextField usernameField;
	private PasswordField passwordField;
	
	
	public LoginForm () {
		super();
		this.load();
	}
	
	public LoginForm load() {
		
		try {

			Label username = new Label("Username: ");
			username.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 18));
			Label password = new Label("Password: ");
			password.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 18));
			
			usernameField = new TextField();
			usernameField.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 18));
			passwordField = new PasswordField();
			passwordField.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 18));
			usernameField.setPromptText("Username..");
			passwordField.setPromptText("Password..");
			
			String css  = "-fx-background-color: darkslateblue; -fx-text-fill:white;";
			
			Button login = new Button("Submit");
			login.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 16));
			login.setPrefSize(80, 25);
			login.setStyle(css);
			Button clear = new Button("Clear");
			clear.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 16));
			clear.setPrefSize(80,25);
			clear.setStyle(css);
			
			HBox buttonPane = new HBox(10);
			buttonPane.getChildren().addAll(login,clear);
			
			// TODO:		
			login.setOnAction(e -> {

			});
			
			// TODO:
			clear.setOnAction(e -> {

			});
			
			super.setVgap(10);
			super.setHgap(10);
			super.add(username, 0, 0);
			super.add(usernameField,1, 0, 2 ,1 );
			super.add(password, 0, 1);
			super.add(passwordField, 1, 1 ,2 ,1 );
			super.add(buttonPane,1,2);
			super.setAlignment(Pos.CENTER);
			
		}catch(Exception e ) {
			System.out.println("An error happened in login form " + e );
		}
		return this;
		
	}
	
	// TODO: Validation .... 
	
	public void validate() {
		
	}
}

class RegisterForm extends GridPane {
	
	private TextField nameField;
	private TextField emailField;
	private PasswordField passwordField;
	private TextField usernameField;
	private PasswordField confirmPasswordField;
	private ToggleGroup genderGroup;
	private RadioButton female;
	private RadioButton male;
	private ComboBox<String>locationView;
	private Button registerButton;
	
	public RegisterForm() {
		super();
		this.load();
	}
	
	public RegisterForm load() {
		
		try {
			
			Label nameLabel = new Label("Name ");
			Label usernameLabel = new Label("Username");
			Label emailLabel = new Label("Email");
			Label passwordLabel = new Label("Password");
			Label confirmPasswordLabel = new Label("Confirm Password");
			Label birthdayLabel = new Label("Birthday");
			Label genderLabel = new Label("Gender");
			Label locationLabel = new Label("Location");
			
			nameField = new TextField();			
			usernameField = new TextField();
			emailField = new TextField();
			passwordField = new PasswordField();
			confirmPasswordField = new PasswordField();
			
			DatePicker birthdayPicker = new DatePicker();
			
			female = new RadioButton("Female");
			male = new RadioButton("Male");
			
			genderGroup = new ToggleGroup();
			female.setToggleGroup(genderGroup);
			male.setToggleGroup(genderGroup);
			HBox genderPane = new HBox(10);
			genderPane.getChildren().addAll(female,male);
			
			locationView = new ComboBox<String>();
			ObservableList<String> locationList = FXCollections.observableArrayList("Albania","Kosovo","Germany","France","Hungary");
			locationView.getItems().addAll(locationList);
			
			registerButton = new Button("Register");
			registerButton.setAlignment(Pos.BOTTOM_CENTER);
			
			HBox registerPane = new HBox();
			registerPane.getChildren().addAll(registerButton);
			
			super.add(nameLabel, 0, 0);
			super.add(nameField,1,0,3,1);
			super.add(usernameLabel, 0, 1);
			super.add(usernameField, 1, 1,3,1);
			super.add(emailLabel, 0, 2);
			super.add(emailField, 1, 2, 3, 1);
			super.add(passwordLabel, 0, 3);
			super.add(passwordField, 1, 3, 3 , 1 );
			super.add(confirmPasswordLabel, 0, 4);
			super.add(confirmPasswordField, 1, 4 ,3,1);
			super.add(birthdayLabel, 0, 5);
			super.add(birthdayPicker, 1, 5, 3,1 );
			super.add(genderLabel, 0, 6);
			super.add(genderPane, 2, 6);
			super.add(locationLabel, 0, 7);
			super.add(locationView, 1, 7, 3, 1);
			super.add(registerPane, 3, 8);
			super.setHgap(10);
			super.setVgap(15);
			super.setAlignment(Pos.CENTER);
			
		
			
			// TODO: Validation 
			String name = nameField.getText();
			String username = usernameField.getText();
			String password = passwordField.getText();
			String confirmPassword = confirmPasswordField.getText();
			String email = emailField.getText();
				
		}catch(Exception e ) {
			
			System.out.println("An error happend in register form " + e);
			
		}
			
		return this;
	}
	
	public boolean validate(String name,String username,String password, String email) {
	
		return true;
	}
	
	
}



























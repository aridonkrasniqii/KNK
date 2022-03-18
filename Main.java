package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

public class Main extends Application {
	
	
	TextField usernameField;
	TextField passwordField;
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Application");
	
		TabPane tabPane = new TabPane();
		
		Tab loginTab = new Tab("Login",getLoginTab());
		loginTab.setClosable(false);
		Tab registerTab = new Tab("Register" , getRegisterTab());
		registerTab.setClosable(false);
		
		tabPane.getTabs().addAll(loginTab,registerTab);
		
		
		
		
		primaryStage.setScene(new Scene(tabPane,1000,700));
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Node getLoginTab() {
		
		GridPane pane = new GridPane();
		pane.setHgap(15);
		pane.setVgap(15);
		

		Label username = new Label("Username: ");
		username.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		Label password = new Label("Password: ");
		password.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		
		
		usernameField = new TextField();
		usernameField.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		passwordField = new TextField();
		passwordField.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		
		usernameField.setPromptText("Username..");
		passwordField.setPromptText("Password..");
		
		
		
		// Css styling 
		String css  = "-fx-background-color: darkslateblue; -fx-text-fill:white;";
		
		
		Button submit = new Button("Submit");
		submit.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		submit.setPrefSize(100, 25);
		submit.setStyle(css);
		Button clear = new Button("Clear");
		clear.setFont(Font.font("Arial",FontWeight.SEMI_BOLD, 20));
		clear.setPrefSize(100,25);
		clear.setStyle(css);
		
		
		
		// Event 
		
		submit.setOnAction(e -> {
				Boolean result = validate();
				if(result) { 
					Alert alert = new Alert(AlertType.INFORMATION,"Data are  saved",ButtonType.OK);
					clear();
					alert.showAndWait();
				}else {
					Alert alert = new Alert(AlertType.ERROR,"Data are not saved",ButtonType.CANCEL);
					alert.showAndWait();
				}
		});
		
		
		clear.setOnAction(e -> {
			clear();
		});
		
		
		
		
		
		pane.add(username, 0, 0);
		pane.add(usernameField,1, 0, 2 ,1 );
		
		pane.add(password, 0, 1);
		pane.add(passwordField, 1, 1 ,2 ,1 );
		
		pane.add(submit, 1, 2);
		pane.add(clear, 2, 2);
		
		
		pane.setAlignment(Pos.CENTER);
		
		
		
		
		return pane;
	}
	
	
	public Node getRegisterTab() {
		
		GridPane pane = new GridPane();
		
		return pane;
	}
	
	public boolean validate() {
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		if(!username.isEmpty() && !password.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	
	public void clear() {
		
		passwordField.setText(" ");
		usernameField.setText(" ");
	}
	
	
	
}






























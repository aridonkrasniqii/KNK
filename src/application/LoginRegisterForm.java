package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginRegisterForm extends Application{
	
	
	
	public static void main(String[] args ) {		
		
		launch(args);
	}
		
	
	public void start(Stage primaryStage ) throws Exception  {
				
		
		try { 
			
			Parent parent = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
			
			Scene scene = new Scene(parent);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		}catch(Exception e ) { 
			System.out.println("Error : " + e);
		}
		
	}
	
}





















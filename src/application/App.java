package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	public static Stage stage = null;

	@Override
	public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

		Parent root = FXMLLoader.load(getClass().getResource("../views/Home.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("LAMALE HOTEL");
		App.stage = primaryStage;
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

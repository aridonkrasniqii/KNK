package application;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import java.io.IOException;

public class App extends Application {

  @Override
  public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
<<<<<<< Updated upstream
    Parent root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
=======
    Parent root = FXMLLoader.load(getClass().getResource("../views/main-view.fxml"));
>>>>>>> Stashed changes
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ErrorPopupComponent {
  public static void show(Exception ex) {
    String message = ex.getMessage();
    if (message == null || message.length() == 0) {
      message = ex.toString();
    }
    show(message);
  }

  public static void show(String message) {
    show(message, "Error");
  }

  public static void show(String message, String title) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(title);

    Label label = new Label(message);
    label.setWrapText(true);
    label.setMaxWidth(400);
    StackPane pane = new StackPane(label);
    alert.getDialogPane().setContent(pane);
    alert.showAndWait();
  }
}

package components;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class SuccessPopupComponent {

  public static void show(String message, String title) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setHeight(400);
    alert.setWidth(400);
    Label label = new Label(message);
    label.setWrapText(true);
    label.setMaxWidth(400);
    StackPane pane = new StackPane(label);
    pane.setMaxHeight(400);
    pane.setMaxWidth(400);
    alert.getDialogPane().setContent(pane);
    alert.showAndWait();
  }
}

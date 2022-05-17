package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AreYouSureController {

    private Stage stage;

    @FXML
    private void onCancelAction(ActionEvent e) throws  Exception {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        this.stage.close();
        stage.close();
    }
    @FXML
    private void onYesAction(ActionEvent e) throws  Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../views/login-view.fxml"));
        stage.setScene(new Scene(parent));
    }


    public void setStage(Stage stage ) throws Exception {
        this.stage = stage;
    }

}


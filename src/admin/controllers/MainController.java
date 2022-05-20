package admin.controllers;


import controllers.AreYouSureController;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static final String GUESTS_DASHBOARD = "guests";
    public static final String OVERVIEW_DASHBOARD = "overview";
    public static final String EVENTS_DASHBOARD = "events";
    public static final String PAYMENTS_DASHBOARD = "payments";
    public static final String ROOMS_DASHBOARD = "rooms";
    public static final String SERVICE_TYPES_DASHBOARD = "service_types";
    public static final String STAFF_DASHBOARD = "staff";
    public static final String ABOUT_VIEW = "about-view";

    private static final String VIEW_PATH = "../views";

    @FXML
    private Label sessionLabel;
    @FXML
    private VBox contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setView(String view) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(this.viewPath(view)));
            Pane pane = null;

            switch (view) {
                case GUESTS_DASHBOARD:
                    pane = loader.load();
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case OVERVIEW_DASHBOARD:
                    pane = loader.load();
                    System.out.println(" overview dashboard");
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case EVENTS_DASHBOARD:
                    pane = loader.load();
//                    System.out.println(" overview dashboard");
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case PAYMENTS_DASHBOARD:
                    pane = loader.load();
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case ROOMS_DASHBOARD:
                    pane = loader.load();
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case SERVICE_TYPES_DASHBOARD:
                    pane = loader.load();
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;
                case STAFF_DASHBOARD:
                    pane = loader.load();
                    contentPane.setAlignment(Pos.TOP_CENTER);
                    break;

                default:
                    throw new Exception("ERR_VIEW_NOT_FOUND");
            }


            // FIXME:
            try {
                // ChildController controller = loader.getController();
                // controller.setParentController(this);
            } catch (Exception ex) {
                System.out.println("Error in setView method: " + ex);
            }

            contentPane.getChildren().clear();
            contentPane.getChildren().add(pane);
            VBox.setVgrow(pane, Priority.ALWAYS);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }

    @FXML
    private void onOverViewNavClick(ActionEvent ev) {
        try {
            this.setView(OVERVIEW_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @FXML
    private void onEventViewNavClick(ActionEvent ev) {
        try {
            this.setView(EVENTS_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void onGuestsNavClick(ActionEvent ev) {
        try {
            this.setView(GUESTS_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void onStaffNavClick(ActionEvent ev) {
        try {
            this.setView(STAFF_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void onRoomsNavClick(ActionEvent ev) {
        try {
            this.setView(ROOMS_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void onPaymentsNavClick(ActionEvent ev) {
        try {
            this.setView(PAYMENTS_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void onServiceTypesNavClick(ActionEvent ev) {
        try {
            this.setView(SERVICE_TYPES_DASHBOARD);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void onLogoutNavClick(ActionEvent ev) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../views/login-view.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    @FXML
    private void onMenuExitAction(ActionEvent e) throws  Exception{
        Stage stage = (Stage) sessionLabel.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onMenuLogoutAction(ActionEvent e) throws  Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../views/are-you-sure.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onInsertUserMenuAction(ActionEvent e) throws  Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/add-guest.fxml"));
        Parent parent = loader.load();
        Stage stage = (Stage) sessionLabel.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void onInsertRoomMenuAction(ActionEvent e) throws  Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("../views/roomviews/add-new-room.fxml"));
        Stage stage = (Stage) sessionLabel.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    @FXML
    private void onAboutMenuAction(ActionEvent e) throws  Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("../../views/about-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }




}

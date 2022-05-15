//package controllers;
//
////import components.RoomDetails;
//import helpers.Person;
//import helpers.Rooms;
//import components.RoomDetails;
//import repositories.ReservationRepository;
//import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.event.ActionEvent;
//
//import java.io.File;
//import java.net.URL;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.stream.Collectors;
//
//public class ReservationController extends LanguageController implements Initializable{
//    @FXML private Label lbl_persNum;
//    @FXML private Label lbl_total;
//    @FXML private Label lbl_fName;
//    @FXML private Label lbl_lName;
//
//    private LocalDate checkin_date;
//    private LocalDate checkout_date;
//    private double total=0;
//    private int guestId=0;
//    private long days;
//
//    @FXML private ScrollPane roomsPane;
//    @FXML private VBox verticalBox;
//    @FXML private TextField idField;
//    @FXML private Label firstName;
//    @FXML private Label lastName;
//    @FXML private Label totalField;
//    @FXML private Button cancelButton;
//
//    ReservationRepository reservationRepository=new ReservationRepository();
//    List<Rooms> roomsSelected;
//    ObservableList<Rooms> roomsToBook;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadLangTexts(getLangBundle());
//        idField.setOnKeyPressed((event)->{
//            if(event.getCode().equals(KeyCode.ENTER)){
//                if(idField.getText().equals("")){
//                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
//                    alert.setContentText("A personal number is mandatory!");
//                    alert.showAndWait();
//                }else{
//                    try{
//                        String ID=idField.getText();
//                        Person person=reservationRepository.getGuest(ID);
//                        if(person==null){
//                            FXMLLoader loader=new FXMLLoader();
//                            URL url1 = new File("src/sample/Views/InsertGuest.fxml").toURI().toURL();
//                            loader.setLocation(url1);
//                            Pane pane=loader.load();
//                            Scene scene=new Scene(pane);
//                            Stage stage=new Stage();
//                            stage.initModality(Modality.APPLICATION_MODAL);
//                            stage.setScene(scene);
//                            stage.show();
//                        }else{
//                            String personName=person.getFirstName();
//                            String personSurname=person.getLastName();
//                            guestId=person.getId();
//                            firstName.setText(personName);
//                            lastName.setText(personSurname);
//                        }
//                    }catch(Exception ex){
//                        Alert alert=new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText(ex.getMessage());
//                        alert.showAndWait();
//                    }
//                }
//            }
//        });
//    }
//
//    @FXML
//    public void onReserveButtonClicked(ActionEvent actionEvent){
//        try{
//            if(guestId==0){
//                Alert alert=new Alert(Alert.AlertType.INFORMATION);
//                alert.setContentText("A guest must be specified to make a reservation!");
//                alert.showAndWait();
//            }else{
//                int paymentId=reservationRepository.createPayment(String.valueOf(guestId),String.valueOf(total));
//                for(Rooms room:roomsToBook){
//                    reservationRepository.createReservation(guestId,room.getRoom_number(),checkin_date.toString(),checkout_date.toString(),paymentId);
//                }
//                Alert completed=new Alert(Alert.AlertType.CONFIRMATION,"The reservation has been made.",ButtonType.OK);
//                completed.showAndWait();
//                if(completed.getResult().equals(ButtonType.OK)){
//                    returnToMain(actionEvent);
//                }
//            }
//        }catch(Exception e){
//            Alert alert=new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(e.getMessage());
//            alert.showAndWait();
//        }
//    }
//
//    @FXML
//    public void onCancelButtonClick(ActionEvent actionEvent) {
//        try{
//            returnToMain(actionEvent);
//        }catch(Exception e){
//            Alert alert=new Alert(Alert.AlertType.ERROR);
//            alert.setContentText(e.getMessage());
//            alert.showAndWait();
//        }
//    }
//
//    public void getRooms(ObservableList<Rooms> rooms, LocalDate checkin_date, LocalDate checkout_date){
//        this.checkin_date=checkin_date;
//        this.checkout_date=checkout_date;
//        this.roomsSelected=rooms.stream().collect(Collectors.toList());
//        this.roomsToBook=FXCollections.observableArrayList(roomsSelected);
//        this.days=getDaysOfStaying(checkin_date,checkout_date);
//
//        roomsToBook.addListener(new ListChangeListener<Rooms>() {
//            @Override
//            public void onChanged(Change<? extends Rooms> change) {
//                verticalBox.getChildren().clear();
//                printRoomsSelected();
//            }
//        });
//
//        printRoomsSelected();
//        totalField.setText(priceToPay(roomsToBook) +" €");
//    }
//
//    private void printRoomsSelected(){
//        for(Rooms room:roomsToBook){
//            try{
//                RoomDetails controller=new RoomDetails();
//                verticalBox.getChildren().add(controller.singleComponent(room,days,e->removeReservedRoom(room)));
//            }catch(Exception e){
//                System.out.println(e.getMessage());
//            }
//            roomsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            roomsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//            roomsPane.setContent(verticalBox);
//        }
//    }
//
//    private double priceToPay(ObservableList<Rooms> rooms){
//        for(Rooms room:rooms){
//            double price=room.getPrice()*days;
//            total+=price;
//        }
//        return total;
//    }
//
//    public void removeReservedRoom(Object roomToRemove){
//            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"A deshironi te largoni dhomen?",ButtonType.OK,ButtonType.CANCEL);
//            alert.showAndWait();
//
//            if(alert.getResult()==ButtonType.OK){
//                roomsToBook.remove(roomToRemove);
//                this.total=0;
//                totalField.setText(priceToPay(roomsToBook) +" €");
//            }else{
//                alert.close();
//            }
//
//    }
//
//    private void returnToMain(ActionEvent actionEvent) throws Exception{
////        URL url=new File("src/sample/Views/main-manager.fxml").toURI().toURL();
////        FXMLLoader loader=new FXMLLoader();
////        loader.setLocation(url);
////        Pane pane=loader.load();
////        MainViewController mainController=loader.getController();
////        mainController.viewLoader("Reservations");
////
////        Scene scene=new Scene(pane);
////        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
////        stage.setScene(scene);
////        stage.show();
//    }
//
//    private long getDaysOfStaying(LocalDate firstDate,LocalDate lastDate){
//        long days= Duration.between(firstDate.atStartOfDay(),lastDate.atStartOfDay()).toDays();
//        return days;
//    }
//
//    @Override
//    public void loadLangTexts(ResourceBundle langBundle) {
//        lbl_persNum.setText(langBundle.getString("personal_number"));
//        lbl_total.setText(langBundle.getString("total"));
//        lbl_fName.setText(langBundle.getString("first_name"));
//        lbl_lName.setText(langBundle.getString("last_name"));
//    }
//}

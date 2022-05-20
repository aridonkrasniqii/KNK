package admin.controllers;

import components.ErrorPopupComponent;
import helpers.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repositories.StaffRepository;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MembersController implements Initializable {


    @FXML
    private ImageView personImageView;

    @FXML
    private Label firstName;


    @FXML
    private Label lastName;

    @FXML
    private Label positionOfMember;


    ArrayList<Staff> staffs;


    private int member = 0;


    @FXML
    private void onNextAction(ActionEvent e) throws Exception {

        try {
            this.member += 1;
            if (this.member == 7) this.member = 1;
            firstName.setText(staffs.get(member - 1).getFirst_name());
            lastName.setText(staffs.get(member - 1).getLast_name());
            positionOfMember.setText(staffs.get(member - 1).getPosition());

            FileInputStream file = new FileInputStream(setImagePath(member));
            Image img = new Image(file);
            personImageView.setImage(img);


        } catch (Exception ex) {
            System.out.println(ex);
        }


    }

    @FXML
    private void onBackAction(ActionEvent e) throws Exception {
        try {
            this.member -= 1;
            if (this.member == 0) this.member = 6;
            firstName.setText(staffs.get(member - 1).getFirst_name());
            lastName.setText(staffs.get(member - 1).getLast_name());
            positionOfMember.setText(staffs.get(member - 1).getPosition());
            FileInputStream file = new FileInputStream(setImagePath(member));
            Image img = new Image(file);
            personImageView.setImage(img);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }


    private void loadStaff() throws Exception {
        staffs = StaffRepository.findAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadStaff();
            firstName.setText(staffs.get(2).getFirst_name());
            lastName.setText(staffs.get(2).getLast_name());
            positionOfMember.setText(staffs.get(2).getPosition());

            FileInputStream file = new FileInputStream(setImagePath(3));
            Image img = new Image(file);
            personImageView.setImage(img);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String setImagePath(int image) {
        return "/Users/lorikmustafa/Documents/GitHub/KNKMASTER/src/admin/images/person" + Integer.toString(image) + ".jpeg";
    }
}
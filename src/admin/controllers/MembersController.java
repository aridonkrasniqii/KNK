package admin.controllers;

import models.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repositories.StaffRepository;
import utilities.I18N;

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
	private Button nextBtn;
	@FXML
	private Button backBtn;



	@FXML
	private void onNextAction(ActionEvent e) throws Exception {

		try {
			this.member += 1;
			if (this.member == 6) this.member = 0;
			firstName.setText(staffs.get(member).getFirst_name());
			lastName.setText(staffs.get(member ).getLast_name());
			positionOfMember.setText(staffs.get(member ).getPosition());

			FileInputStream file = new FileInputStream(setImagePath(member));
			Image img = new Image(file);
			personImageView.setImage(img);


		} catch (Exception ex) {
			System.out.println(ex);
		}

		nextBtn.textProperty().bind(I18N.createStringBinding("nextBtn"));
		backBtn.textProperty().bind(I18N.createStringBinding("backBtn"));


	}

	@FXML
	private void onBackAction(ActionEvent e) throws Exception {
		try {
			this.member -= 1;
			if (this.member == -1) this.member = 5;
			firstName.setText(staffs.get(member).getFirst_name());
			lastName.setText(staffs.get(member).getLast_name());
			positionOfMember.setText(staffs.get(member ).getPosition());
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

			firstName.setText(staffs.get(0).getFirst_name());
			lastName.setText(staffs.get(0).getLast_name());
			positionOfMember.setText(staffs.get(0).getPosition());

			FileInputStream file = new FileInputStream(setImagePath(0));
			Image img = new Image(file);
			personImageView.setImage(img);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	private String setImagePath(int image) {
		return "admin/images/person" + Integer.toString(image) + ".jpeg";
	}
}

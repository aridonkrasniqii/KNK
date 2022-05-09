package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.view.PaymentModel;

public class PaymentsRepository {

  // private static Connection connection;
  // private static DBConnect dbConnection = new DBConnect();

  private DBConnection conn;

  public PaymentsRepository() {
    this.conn = DBConnection.getConnection();
  }

  public ArrayList<PaymentModel> findAll() throws SQLException {
    String query = "select * from paymentmodel";
    ResultSet res = this.conn.executeQuery(query);
    ArrayList<PaymentModel> paymentModel = new ArrayList<>();

    while (res.next()) {
      System.out.println("Room: " + res.getString("firstname"));
      paymentModel.add(PaymentModel.fromResultSet(res));
    }
    return paymentModel;

  }

}

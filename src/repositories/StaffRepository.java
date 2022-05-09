package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DBConnection;
import helpers.Staff;

public class StaffRepository {

  private DBConnection connection;

  public StaffRepository() {
    this.connection = DBConnection.getConnection();
  }

  public ArrayList<Staff> findAll() throws SQLException {
    String query = "select * from staff";
    ResultSet res = this.connection.executeQuery(query);
    ArrayList<Staff> staff = new ArrayList<>();
    while (res.next()) {
      System.out.println("Name: " + res.getString("last_name"));
      staff.add(Staff.fromResultSet(res));
    }
    return staff;
  }
}

package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterGuests {
  private int id;
  private String first_name;
  private String username;
  private String email;
  private String password;
  private String birthdate;
  private String registeredDate;
  private String gender;
  private String location;

  private RegisterGuests(int id, String first_name, String username, String email, String password, String birthdate,String registeredDate,
      String gender, String location) {
    this.id = id;
    this.first_name = first_name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.registeredDate = registeredDate;
    this.birthdate = birthdate;
    this.gender = gender;
    this.location = location;
  }

  public static RegisterGuests fromResultSet(ResultSet res) throws SQLException {

    if (res.next()) {
      try {
        int id = res.getInt("id");
        String first_name = res.getString("first_name");
        String username = res.getString("username");
        String email = res.getString("email");
        String password = res.getString("password");
        String birthdate = res.getString("birthdate");
        String gender = res.getString("gender");
        String registeredDate = res.getString("registered_date");
        String location = res.getString("location");
        return new RegisterGuests(id, first_name, username, email, password, birthdate,
            registeredDate ,gender, location);
      } catch (SQLException e) {
        System.out.println("RegisterGuests class exception method: fromResultSet : " + e);
        return null;
      }
    } else {
      return null;
    }
  }

  public static RegisterGuests createGuest(int id, String first_name, String username, String email, String password,
      String birthdate, String registeredDate, String gender, String location) {
        // change id to 0
    return new RegisterGuests(id, first_name, username, email, password, birthdate, registeredDate ,gender, location);
  }

  public int getId() {
    return id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getGender() {
    return gender;
  }

  public String getPassword() {
    return password;
  }

  public String getBirthdate() {
    return birthdate;
  }
  public String getRegisteredDate() {
    return registeredDate;
  }

  public String getLocation() {
    return location;
  }

}

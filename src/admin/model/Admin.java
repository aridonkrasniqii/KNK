package admin.model;
import java.sql.ResultSet;

public class Admin {
  private int id;
  private String first_name;
  private String username;
  private String email;
  private String password;
  private String birthdate;
  private String registeredDate;
  private String gender;
  private String location;

  private Admin(int id, String first_name, String username, String email, String password, String birthdate,
      String registeredDate, String gender, String location) {
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

  public static Admin getInstance(int id, String first_name, String username, String email, String password,
      String birthdate, String registeredDate, String gender, String location) {
    return new Admin(id, first_name, username, email, password, birthdate, registeredDate, gender, location);
  }

  public static Admin fromResultSet(ResultSet res) {

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

      return new Admin(id, first_name, username, email, password, birthdate, registeredDate, gender, location);
    } catch (Exception e) {
      System.out.println("Error in admin class " + e);
      return null;
    }
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

  public void setPassword(String password) {
    this.password = password;
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

  public String getRegisteredDate() {
    return registeredDate;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public String getLocation() {
    return location;
  }

  public void setName(String name) {
    this.first_name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setRegisteredDate(String date) {
    this.registeredDate = date;
  }

  public void setBirthdate(String date) {
    this.birthdate = date;
  }

  public void setGender(String g) {
    this.gender = g;
  }

  public void setLocation(String l) {
    this.location = l;
  }

}

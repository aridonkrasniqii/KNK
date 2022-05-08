package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Staff extends Person {
  private String position;
  private double salary;
  private String password;

  public Staff() {
    // default values;
    this(0, "firstName", "lastName", "personalNumber", "phoneNumber", "gender", "birthdate", "position", 0.0,
        "password");
  }

  public Staff(int id, String firstName, String lastName, String personalNumber, String phoneNumber, String gender,
      String birthdate, String position, double salary, String password) {
    super(id, firstName, lastName, personalNumber, phoneNumber, gender, birthdate);
    this.position = position;
    this.salary = salary;
    this.password = password;
  }

  public static Staff fromResultSet(ResultSet res) throws SQLException {

    int id = res.getInt("id");
    String first_name = res.getString("first_name");
    String last_name = res.getString("last_name");
    String personalNumber = res.getString("personal_number");
    String position = res.getString("position");
    String birthdate = res.getString("birthdate");
    String phone_number = res.getString("phone_number");
    double salary = res.getDouble("salary");
    String password = res.getString("passwordd");
    String gender = res.getString("gender");

    Staff staff = new Staff(id, first_name, last_name, personalNumber, phone_number, gender, birthdate, position,
        salary, password);
    return staff;

  }

  public String getFirst_name() {
    return super.getFirstName();
  }

  public String getLast_name() {
    return super.getLastName();
  }

  public String getPersonal_number() {
    return super.getPersonalNumber();
  }

  public String getBirthdate() {
    return super.getBirthdate();
  }

  public String getPhone_number() {
    return super.getPhoneNumber();
  }

  public static void print(String str) {
    System.out.println("str: " + str);
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public double getSalary() {
    return salary;
  }

  public String getGender() {
    return super.getGender();
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

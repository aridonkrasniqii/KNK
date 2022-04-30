package model;

import java.sql.ResultSet;

public class Admin extends RegisterGuests{


  private Admin(int id, String first_name, String username, String email, String password, String birthdate,String registeredDate,String gender, String location){
    super(id, first_name,username,email,password,birthdate,registeredDate,gender,location);
  }
  public static Admin getInstance(int id, String first_name, String username, String email, String password, String birthdate,String registeredDate,String gender, String location){
    return new Admin(id, first_name,username,email,password,birthdate,registeredDate,gender,location);
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

         return new Admin(id, first_name,username,email,password,birthdate,registeredDate,gender,location);
    }catch(Exception e ) {
      System.out.println("Error in admin class "+ e);
      return null;
    }
  }

  public int getId() {
    return super.getId();
  }

  public String getFirst_name() {
    return super.getFirst_name();
  }

  public String getUsername() {
    return super.getUsername();
  }

  public String getEmail() {
    return super.getEmail();
  }

  public String getGender() {
    return super.getGender();
  }

  public String getPassword() {
    return super.getPassword();
  }

  public String getRegisteredDate() {
    return super.getRegisteredDate();
  }

  public String getBirthdate() {
    return super.getBirthdate();
  }

  public String getLocation() {
    return super.getLocation();
  }

  public void setName(String name) {
    super.setName(name);
  }

  public void setUsername(String username) {
    super.setUsername(username);
  }

  public void setEmail(String email) {
    super.setEmail(email);
  }

  public void setRegisteredDate(String date) {
    super.setRegisteredDate(date);
  }

  public void setBirthdate(String date) {
    super.setBirthdate(date);
  }

  public void setGender(String g) {
    super.setGender(g);
  }

  public void setLocation(String l) {
    super.setLocation(l);
  }


}

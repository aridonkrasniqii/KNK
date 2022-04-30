package application;

// VALIDATION CLASS

import java.util.ArrayList;

public class RegisterUser {

  private String name;
  private String username;
  private String email;
  private String password;
  private String confirmPassword;
  private String birthday;
  private String gender;
  private String location;
  private ArrayList<String> data;

  public static RegisterUser fromValuesRegister(String name, String username,
      String password, String confirmPassword,
      String email, String birthday,
      String gender, String location) {
    return new RegisterUser(name, username, password, confirmPassword, email, birthday, gender, location);
  }

  private RegisterUser(String name, String username,
      String password, String confirmPassword,
      String email, String birthday,
      String gender, String location) {

    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.birthday = birthday;
    this.gender = gender;
    this.location = location;

    this.data = new ArrayList<String>() {

      private static final long serialVersionUID = 1L; // a universal version identifier for a Serializable class

      {
        add(name);
        add(username);
        add(password);
        add(confirmPassword);
        add(email);
        add(gender);
        add(location);
        add(new String(birthday));
      }
    };

  }

  public boolean validate() {

    for (int i = 0; i < this.data.size(); i++) {

      String value = data.get(i);

      if (value == null || value.equals("") || value.equals(" ") || value.length() == 0) {
        return false;
      }

    }

    if (!password.equals(confirmPassword) || password.length() < 8) {
      return false;
    }
    if (email.indexOf("@") < 0 || email.indexOf(".") < 0) {
      return false;
    }

    return true;
  }

  public String getName() {
    return name;
  }

  public String getUsername() {
    return username;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getGender() {
    return gender;
  }

  public String getLocation() {
    return location;
  }

}

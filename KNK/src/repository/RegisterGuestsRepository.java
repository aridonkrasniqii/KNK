package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBConnection;
import database.InsertQueryBuilder;
import database.UpdateQueryBuilder;
import model.RegisterGuests;

public class RegisterGuestsRepository {

  private DBConnection connection;

  public RegisterGuestsRepository() {
    this.connection = DBConnection.getConnection();
  }

  public ArrayList<RegisterGuests> findAll() throws SQLException {
    String query = "SELECT * FROM registerGuests;";
    ResultSet res = this.connection.executeQuery(query);

    ArrayList<RegisterGuests> guests = new ArrayList<>();

    while (res.next()) {
      guests.add(RegisterGuests.fromResultSet(res));

    }
    return guests;
  }

  public RegisterGuests create(RegisterGuests guest) throws Exception {
    InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("registerGuests")
        .add("id", 0, "i")
        .add("first_name", guest.getFirst_name(), "s")
        .add("username", guest.getUsername(), "s")
        .add("email", guest.getEmail(), "s")
        .add("password", guest.getPassword(), "s")
        .add("birthdate", guest.getBirthdate(), "s")
        .add("gender", guest.getGender(), "s")
        .add("location", guest.getLocation(), "s");

    int lastInsertedId = this.connection.execute(query);
    RegisterGuests createGuest = this.findById(lastInsertedId);

    if (createGuest != null) {
      System.out.println("Guest was created successfully ");
      return createGuest;
    }

    throw new Exception("Guest failed to create!");
  }

  public RegisterGuests findByUsernamePassword(String username, String password) throws Exception {
    String query = "select * from registerGuests where username = '" + username.trim() + "' and password = '"
        + password.trim() + "';";

    ResultSet res = this.connection.executeQuery(query);
    RegisterGuests guest = RegisterGuests.fromResultSet(res);

    if (guest != null) {
      return guest;
    }

    return null;

  }

  public RegisterGuests findById(int id) throws SQLException {
    String query = "SELECT * FROM registerGuests WHERE id = " + id;
    ResultSet res = this.connection.executeQuery(query);

    RegisterGuests guest = RegisterGuests.fromResultSet(res);
    res.next();
    return guest;

  }

  public RegisterGuests update(RegisterGuests guest) throws Exception {
    UpdateQueryBuilder query = (UpdateQueryBuilder) UpdateQueryBuilder.create("registerGuests")
        .add("first_name", guest.getFirst_name(), "s")
        .add("username", guest.getUsername(), "s")
        .add("email", guest.getEmail(), "s")
        .add("password", guest.getPassword(), "s")
        .add("birthdate", guest.getBirthdate(), "s")
        .add("gender", guest.getGender(), "s")
        .add("location", guest.getLocation(), "s");

    this.connection.execute(query);

    RegisterGuests updateGuest = this.findById(guest.getId());

    if (updateGuest != null) {
      return updateGuest;
    }

    throw new Exception("RegisterGuest failed to update!");

  }

}

package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import database.DBConnection;
import database.InsertQueryBuilder;
import database.UpdateQueryBuilder;
import helpers.DateHelper;
import models.User;
import models.UserRole;

public class UserRepository {

  static DBConnection connection = DBConnection.getConnection();

  private static User fromResultSet(ResultSet res) throws Exception {
    int id = res.getInt("id");
    String name = res.getString("name");
    String email = res.getString("email");
    String username = res.getString("username");
    String password = res.getString("password");
    String salt = res.getString("salt");
    UserRole role = res.getString("role").equals("A") ? UserRole.Admin : UserRole.Guest;
    boolean active = res.getInt("isActive") == 1;
    Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
    Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));

    return new User(id, name, username, email, password, salt, role, active, createdAt, updatedAt);
  }

  public static ArrayList<User> getAll() throws Exception {
    connection = DBConnection.getConnection();
    String query = "SELECT * FROM users";
    ResultSet res = connection.executeQuery(query);

    ArrayList<User> list = new ArrayList<User>();
    while (res.next()) {
      list.add(fromResultSet(res));
    }
    return list;
  }

  public static User find(int id) throws Exception {
    connection = DBConnection.getConnection();
    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1");
    stmt.setInt(1, id);

    ResultSet res = stmt.executeQuery();
    if (!res.next()) {
      return null;
    }
    return fromResultSet(res);
  }

  public static User find(String email) throws Exception {
    connection = DBConnection.getConnection();

    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ? OR username = ? LIMIT 1");

    stmt.setString(1, email);
    stmt.setString(2, email);
    ResultSet res = stmt.executeQuery();
    if (!res.next()) {
      return null;
    }
    return fromResultSet(res);
  }

  public static User update(User model) throws Exception {
    UpdateQueryBuilder query = (UpdateQueryBuilder) UpdateQueryBuilder.create("users")
        .add("id", 0, "i")
        .add("name", model.getName(), "s")
        .add("username", model.getUsername(), "s")
        .add("email", model.getEmail(), "s")
        .add("password", model.getSalt(), "s")
        .add("salt", model.getSalt(), "s")
        .add("role", model.getRole() == UserRole.Guest ? "G" : "A", "s")
        .add("isActive", model.getIsActive() ? 1 : 0, "i")
        .add("createdAt", DateHelper.toSql(model.getCreatedAt()), "s")
        .add("updatedAt", DateHelper.toSql(model.getUpdatedAt()), "s");

    connection.execute(query);

    User updateGuest = find(model.getId());

    if (updateGuest != null) {
      return updateGuest;
    }
    ErrorPopupComponent.show("Guest failed to update!");
    return null;
  }

  public static User create(User model) throws Exception {
    InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("users")
        .add("id", 0, "i")
        .add("name", model.getName(), "s")
        .add("username", model.getUsername(), "s")
        .add("email", model.getEmail(), "s")
        .add("password", model.getPassword(), "s")
        .add("salt", model.getSalt(), "s")
        .add("role", model.getRole() == UserRole.Guest ? "G" : "A", "s")
        .add("isActive", model.getIsActive() ? 1 : 0, "i")
        .add("createdAt", DateHelper.toSql(model.getCreatedAt()), "s")
        .add("updatedAt", DateHelper.toSql(model.getUpdatedAt()), "s");

    int lastInsertedId = connection.execute(query);
    User user = find(lastInsertedId);

    if (user != null) {
      SuccessPopupComponent.show("Successfully Created", "Register");
      return user;
    }

    ErrorPopupComponent.show("Failed to Register");
    return null;
  }

  public static boolean remove(int id) throws Exception {
    DBConnection connection = DBConnection.getConnection();
    String query = "DELETE FROM users WHERE id = ? ";
    PreparedStatement stmt = connection.prepareStatement(query);
    stmt.setInt(1, id);
    return stmt.executeUpdate() == 1;
  }
}
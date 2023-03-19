package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import database.DBConnection;
import database.InsertQueryBuilder;
import models.User;
import models.UserRole;
import processor.DateHelper;

public class UserRepository {

	private final static DBConnection connection = DBConnection.getConnection();

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
		String query = "SELECT * FROM users";
		ResultSet res = connection.executeQuery(query);

		ArrayList<User> list = new ArrayList<User>();
		while (res.next()) {
			list.add(fromResultSet(res));
		}
		return list;
	}

	public static User find(int id) throws Exception {

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1");
		stmt.setInt(1, id);

		ResultSet res = stmt.executeQuery();
		if (!res.next()) {
			return null;
		}
		return fromResultSet(res);
	}

	public static User find(String email) throws Exception {

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ? OR username = ? LIMIT 1");

		stmt.setString(1, email);
		stmt.setString(2, email);
		ResultSet res = stmt.executeQuery();
		if (!res.next()) {
			return null;
		}
		return fromResultSet(res);
	}

	public static boolean find(String email, String username) throws Exception {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ? OR username = ?");
		stmt.setString(1, email);
		stmt.setString(2, username);
		ResultSet res = stmt.executeQuery();
		return res.next();
	}

	public static User update(User model) throws Exception {

		String query = "update users set name = ? , username = ? , email = ?, password = ? , salt = ? "
				+ ", role = ? , isActive = ? ,createdAt = ? , updatedAt = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, model.getName());
		stmt.setString(2, model.getUsername());
		stmt.setString(3, model.getEmail());
		stmt.setString(4, model.getPassword());
		stmt.setString(5, model.getSalt());
		stmt.setString(6, model.getRole() == UserRole.Admin ? "A" : "G");
		stmt.setInt(7, model.getIsActive() ? 1 : 0);
		stmt.setString(8, DateHelper.toSql(model.getCreatedAt()));
		stmt.setString(9, DateHelper.toSql(model.getUpdatedAt()));
		stmt.setInt(10, model.getId());

		int affectedRows = stmt.executeUpdate();
		if (affectedRows != 1) {
			throw new Exception("ERR_NO_ROW_CHANGE");
		}

		return find(model.getId());

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
		return find(lastInsertedId);
	}

	public static boolean remove(int id) throws Exception {
		String query = "DELETE FROM users WHERE id = ? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		User user = find(id);
		return user == null;
	}
}

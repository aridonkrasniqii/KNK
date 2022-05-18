package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static String SERVER = "localhost:3306";
	private static String DATABASE = "DBHotel";
	private static String USERNAME = "aridon";
	private static String PASSWORD = "aridon123";
	private Connection connection;

	public static DBConnection getConnection() {
		return new DBConnection();
	}

	private DBConnection() {
		this.connection = this.initConnection();
	}

	private Connection initConnection() {
		try {
			Class.forName(DBConnection.DRIVER_NAME);
			connection = DriverManager.getConnection(
					"jdbc:mysql://" + DBConnection.SERVER + "/" + DBConnection.DATABASE, DBConnection.USERNAME,
					DBConnection.PASSWORD);
			return connection;
		} catch (Exception e) {
			System.out.println("DBConnection error : " + e.getMessage());
			return null;
		}

	}

	public ResultSet executeQuery(String query) {
		try {
			PreparedStatement sq = this.connection.prepareStatement(query);
			return sq.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Execute query error : " + e.getMessage());
			return null;
		}

	}

	public int execute(AbstractQueryBuilder queryBuilder) {

		try {
			PreparedStatement pst = this.connection.prepareStatement(queryBuilder.getQuery(),
					Statement.RETURN_GENERATED_KEYS);

			Object[] values = (Object[]) queryBuilder.getValues();
			char[] types = queryBuilder.getTypes().toCharArray();

			for (int i = 0; i < values.length; i++) {
				// vendosja e vlerave per parametrat perkates fillon nga indeksi 1
				switch (types[i]) {
				case 'i':
					pst.setInt(i + 1, (int) values[i]);
					break;
				case 's':
					pst.setString(i + 1, (String) values[i]);
					break;
				case 'f':
					pst.setFloat(i + 1, (float) values[i]);
					break;
				default:
					throw new Exception("Type not supported!");
				}
			}

			pst.execute();
			int lastInsertedId = 0;
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				// Id should be the first column, right ?
				lastInsertedId = rs.getInt(1);
			}
			rs.close();
			pst.close();
			return lastInsertedId;

		} catch (Exception e) {
			System.out.println("Error in class DBConnection execute method  : " + e);
			return 0;
		}
	}

	public Statement createStatement() throws SQLException {
		return this.connection.createStatement();
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return this.connection.prepareStatement(query);
	}

}

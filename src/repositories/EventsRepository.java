package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import database.DBConnection;
import database.InsertQueryBuilder;
import models.Events;
import models.charts.EventChart;
import processor.DateHelper;

public class EventsRepository {

	private final static DBConnection connection = DBConnection.getConnection();



	// never used
	private static boolean remove(int id) throws Exception {
		String query = "delete from events where id = ? ";

		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.executeQuery();

		return find(id) == null;

	}


	public static ArrayList<EventChart> findOrganizer() throws Exception {
		ArrayList<EventChart> events = new ArrayList<>();

		String query = "select count(*), organizer from events group by organizer";

		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			events.add(new EventChart(result.getInt("count(*)"), result.getString("organizer")));
		}
		return events;
	}

	public ArrayList<Events> findAll() throws Exception {

		String query = "select * from events";
		Statement stmt = connection.createStatement();

		ResultSet res = stmt.executeQuery(query);
		ArrayList<Events> events = new ArrayList<>();
		while (res.next()) {
			events.add(fromResultSet(res));
		}

		return events;
	}

	public static Events fromResultSet(ResultSet res) throws Exception {
		int id = res.getInt("id");
		String title = res.getString("title");
		String organizer = res.getString("organizer");
		String category = res.getString("category");
		double price = res.getDouble("price");
		String start_date = res.getString("start_date");
		String end_date = res.getString("end_date");

		return new Events(id, title, organizer, category, price, DateHelper.fromSqlDate(start_date),
				DateHelper.fromSqlDate(end_date));
	}

	public static Events update(Events model) throws Exception {

		String query = "update events set title = ? , organizer = ? , category = ? , price = ? "
				+ ", start_date = ? , end_date = ? where id = ?;";

		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, model.getTitle());
		stmt.setString(2, model.getOrganizer());
		stmt.setString(3, model.getCategory());
		stmt.setDouble(4, model.getPrice());
		stmt.setString(5, DateHelper.toSqlDate(model.getStart_date()));
		stmt.setString(6, DateHelper.toSqlDate(model.getEnd_date()));
		stmt.setInt(7, model.getId());

		int affectedRows = stmt.executeUpdate();
		if (affectedRows != 1) {
			throw new Exception("ERR_NO_ROW_CHANGE");
		}

		return find(model.getId());
	}

	public static Events find(int id) throws Exception {
		String query = "select * from events where id = ?";
		PreparedStatement stmt = connection.prepareStatement(query);

		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();

		if (!result.next()) {
			return null;
		}
		return fromResultSet(result);
	}

	public static Events create(Events model) throws Exception {
		InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("events")
				.add("title", model.getTitle(), "s").add("organizer", model.getOrganizer(), "s")
				.add("category", model.getCategory(), "s").add("price", (float) model.getPrice(), "f")
				.add("start_date", DateHelper.toSqlDate(model.getStart_date()), "s")
				.add("end_date", DateHelper.toSqlDate(model.getEnd_date()), "s");

		int lastInsertedId = connection.execute(query);
		return find(lastInsertedId);
	}
}

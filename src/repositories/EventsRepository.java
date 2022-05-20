package repositories;

import database.DBConnection;
import database.InsertQueryBuilder;
import helpers.Rooms;
import models.Events;
import models.charts.EventChart;
import processor.DateHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EventsRepository {


    private static DBConnection connection = DBConnection.getConnection();



    private static boolean remove(int id ) throws  Exception{
        String query = "delete from events where id = ? ";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet res = stmt.executeQuery();

        if(res.next()) return true;

        return false;

    }

    public static ArrayList<EventChart> findOrganizer() throws Exception {
        ArrayList<EventChart> events = new ArrayList<>();

        String query = "select count(*), organizer from events group by organizer";

        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            events.add(new EventChart(result.getInt("count(*)") , result.getString("organizer")));
        }
        if(events != null) return events;

        return null;
    }
    public ArrayList<helpers.Events> findAll()  throws Exception{

        String query = "select * from events";
        Statement stmt = connection.createStatement();

        ResultSet res = stmt.executeQuery(query);
        ArrayList<helpers.Events> events = new ArrayList<>();
        while(res.next()){
            events.add(fromResultSet(res));
        }

        return events;
    }


    public static helpers.Events fromResultSet(ResultSet res) throws  Exception{
        int id = res.getInt("id");
        String title = res.getString("title");
        String organizer = res.getString("organizer");
        String category = res.getString("category");
        double price = res.getDouble("price");
//        Date start_date = res.getDate("start_date");
//        Date end_date = res.getDate("end_date");
        String start_date = res.getString("start_date");
        String end_date = res.getString("end_date");

        return new helpers.Events(title, organizer, category, price, start_date, end_date);
    }


//    public static Events update(Events model) throws  Exception {
//            // TODO:
//        return null;
//    }
    
    public static helpers.Events update(helpers.Events model) throws Exception {
        String query = "update rooms set category = ? , capacity = ? , bed_number = ?,room_type = ?, price = ?  where room_number = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, model.getTitle());
        stmt.setString(2, model.getOrganizer());
        stmt.setString(3, model.getCategory());
        stmt.setDouble(4, model.getPrice());
//        stmt.setString(5 , DateHelper.toSql(model.getStart_date()));
//        stmt.setString(6 , DateHelper.toSql(model.getEnd_date()));
        stmt.setString(5 , model.getStart_date());
        stmt.setString(6 , model.getEnd_date());


        int affectedRows = stmt.executeUpdate();
        if (affectedRows != 1) {
            throw new Exception("ERR_NO_ROW_CHANGE");
        }

        return find(model.getId());
    }
    
    public static helpers.Events find(int id) throws Exception {
        String query = "select * from events where title = ?";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();

        if (!result.next()) {
            return null;
        }
        return fromResultSet(result);
    }
    

    public static helpers.Events create(helpers.Events model) throws  Exception {
    	InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("rooms")
                .add("title", model.getTitle(), "s")
                .add("organizer", model.getOrganizer(), "s")
                .add("category", model.getCategory(), "s")
                .add("price", model.getPrice(), "f")
                .add("start_date", model.getStart_date(), "s")
                .add("end_date", model.getEnd_date(), "s");

        int lastInsertedId = connection.execute(query);
        helpers.Events event = find(lastInsertedId);

        if (event != null)
            return event;
        return null;
    }
}

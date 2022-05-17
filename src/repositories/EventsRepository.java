package repositories;

import database.DBConnection;
import models.Events;

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
    public static ArrayList<Events> findAll()  throws Exception{

        String query = "select * from events";
        Statement stmt = connection.createStatement();

        ResultSet res = stmt.executeQuery(query);
        ArrayList<Events> events = new ArrayList<>();
        while(res.next()){
            events.add(fromResultSet(res));
        }

        if(events != null) return events;

        return null;
    }


    public static Events fromResultSet(ResultSet res) throws  Exception{
        int id = res.getInt("id");
        String title = res.getString("title");
        String organizer = res.getString("organizer");
        String category = res.getString("category");
        double price = res.getDouble("price");
        Date start_date = res.getDate("start_date");
        Date end_date = res.getDate("end_date");

        return new Events(id, title, organizer, category, price, start_date, end_date);
    }


    public static Events update(Events model) throws  Exception {
            // TODO:
        return null;
    }

    public static Events create(Events model ) throws  Exception {
        // TODO:
        return null;
    }
}

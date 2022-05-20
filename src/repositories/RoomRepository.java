package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import database.DBConnection;
import database.InsertQueryBuilder;
import helpers.Rooms;
import models.charts.RoomChart;

public class RoomRepository {

    static DBConnection connection = DBConnection.getConnection();


    public ArrayList<Rooms> findAll() throws Exception {
        String query = "select * from rooms";
        ResultSet res = connection.executeQuery(query);
        ArrayList<Rooms> rooms = new ArrayList<>();
        while (res.next()) {
            rooms.add(fromResultSet(res));
        }
        return rooms;
    }

    public static ArrayList<RoomChart> findType() throws  Exception {
        String query = "select count(*), room_type from rooms group by room_type";

        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);

        ArrayList<RoomChart> rooms = new ArrayList<>();

        while(result.next()){
            rooms.add(new RoomChart(result.getInt("count(*)"), result.getString("room_type")));
        }

        if(rooms != null) return rooms;

        return null;
    }


    public static Rooms findAvailableRoom(int room_number,String checkIn ,String checkOut,String type) throws Exception {
        String query = query = "select * from rooms ro where ro.room_number not in (\n" +
                "select ro.room_number from rooms ro inner join reservations re on re.room_id = ro.room_number \n" +
                "where (re.checkin_date between '' and '') and (re.checkout_date between '' and '') ) and ro.room_number = 1;";


        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);

        if (result.next()) {
            return fromResultSet(result);
        }

        return null;
    }


    public static Rooms fromResultSet(ResultSet result) throws Exception {
        int id = result.getInt("room_number");
        int floor_number = result.getInt("floor_number");
        int capacity = result.getInt("capacity");
        int bed_number = result.getInt("bed_number");
        String room_type = result.getString("room_type");
        double price = result.getDouble("price");

        return new Rooms(id, floor_number, capacity, bed_number, room_type, price);
    }


    public static Rooms find(int id) throws Exception {
        String query = "select * from rooms where room_number = ?";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();

        if (!result.next()) {
            return null;
        }
        return fromResultSet(result);
    }

    public static Rooms update(Rooms model) throws Exception {
        String query = "update rooms set floor_number = ? , capacity = ? , bed_number = ?,room_type = ?, price = ?  where room_number = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, model.getFloor_number());
        stmt.setInt(2, model.getCapacity());
        stmt.setInt(3, model.getBed_number());
        stmt.setString(4, model.getRoom_type());
        stmt.setDouble(5, model.getPrice());
        stmt.setInt(6, model.getRoom_number());


        int affectedRows = stmt.executeUpdate();
        if (affectedRows != 1) {
            throw new Exception("ERR_NO_ROW_CHANGE");
        }

        return find(model.getRoom_number());
    }


    public static Rooms create(Rooms rooms) throws Exception {

        InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("rooms")
                .add("room_number", rooms.getRoom_number(), "i")
                .add("floor_number", rooms.getFloor_number(), "i")
                .add("capacity", rooms.getCapacity(), "i")
                .add("bed_number", rooms.getBed_number(), "i")
                .add("room_type", rooms.getRoom_type(), "s")
                .add("price", (float) rooms.getPrice(), "f");

        int lastInsertedId = connection.execute(query);
        Rooms room = find(lastInsertedId);

        if (room != null)
            return room;

        return null;
    }


    public static ArrayList<Rooms> filterAvailableRooms(String checkIn, String checkOut, String roomType) throws
            Exception {

        String query;

        if (roomType.equals("All")) {
            query = "select * from rooms r where r.room_number not in(" +
                    "select r.room_number " +
                    "from reservations res inner join rooms r on res.room_id=r.room_number" +
                    "where (checkin_date between '" + checkIn + "' and '" + checkOut + "') and (checkout_date between '" + checkIn
                    + "' and '" + checkOut + "'))";
        } else {
            query = "select * from rooms r where r.room_type='" + roomType + "' and r.room_number not in(\n" +
                    "select r.room_number \n" +
                    "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                    "where (checkin_date between '" + checkIn + "' and '" + checkOut + "') and (checkout_date between '" + checkIn
                    + "' and '" + checkOut + "'))";
        }

        ArrayList<Rooms> rooms = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            rooms.add(fromResultSet(result));
        }
        if (rooms != null) return rooms;

        return null;
    }


    public static ArrayList<Rooms> filterAllRooms(String type, String bedNumber, String capacity) throws Exception {
        StringBuilder query = new StringBuilder();
        boolean where = false;
        ArrayList<Rooms> rooms = new ArrayList<>();
        query.append("select * from rooms ");

        if (!type.equals("All")) {
            query.append("where room_type = '" + type + "' ");
            where = true;
        }
        if (!bedNumber.equals("All")) {
            query.append(where ? "and" : "where");
            query.append("bedNumber = " + Integer.parseInt(bedNumber) + " ");
            where = true;
        }
        if (!capacity.equals("All")) {
            query.append(where ? "and" : "where");
            query.append("capactiy = " + Integer.parseInt(capacity) + "");
        }

        PreparedStatement stmt = connection.prepareStatement(query.toString());
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            rooms.add(fromResultSet(result));
        }
        if (rooms != null) return rooms;
        return null;

    }

    public ResultSet getAvailableRooms(String checkin, String checkout, String type) throws Exception {
        DBConnection connection = DBConnection.getConnection();
        String query;
        if (type == "All") {
            query = "select * from rooms r where r.room_number not in(\n" +
                    "select r.room_number \n" +
                    "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                    "where (checkin_date between '" + checkin + "' and '" + checkout + "') and (checkout_date between '" + checkin
                    + "' and '" + checkout + "'))";
        } else {
            query = "select * from rooms r where r.room_type='" + type + "' and r.room_number not in(\n" +
                    "select r.room_number \n" +
                    "from reservations res inner join rooms r on res.room_id=r.room_number\n" +
                    "where (checkin_date between '" + checkin + "' and '" + checkout + "') and (checkout_date between '" + checkin
                    + "' and '" + checkout + "'))";
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }


    public static Rooms findReservedRooms(int payment_id) throws  Exception {
        String query = "select ro.room_number as room_number , ro.floor_number as floor_number \n" +
                ",ro.capacity as capacity ,ro.bed_number ,ro.room_type,ro.price from reservations re \n" +
                "inner join payments pa  on pa.id = re.payment_id\n" +
                "inner join rooms ro on ro.room_number = re.room_id where re.payment_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1 , payment_id);
        ResultSet result = stmt.executeQuery();

        Rooms rooms = fromResultSet(result);
        if(rooms != null) return rooms;

        return null;
    }

    public static ArrayList<Rooms> getOffers() throws Exception  {
        String query = "select * from rooms where price > 100 limit 4";

        ArrayList<Rooms> rooms = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            rooms.add(fromResultSet(res));
        }
        if(rooms != null) return rooms;

        return null;
    }



}


package repositories;

import components.ErrorPopupComponent;
import components.SuccessPopupComponent;
import database.DBConnection;
import database.InsertQueryBuilder;
import helpers.Reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ReservationRepository {


    private static DBConnection connection = DBConnection.getConnection();


    public static Reservation find(int id) throws Exception {
        String query = "select * from reservations where id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            return fromResultSet(res);
        }
        return null;
    }


    public static Reservation fromResultSet(ResultSet res) throws Exception {
        int id = res.getInt("id");
        int guest_id = res.getInt("guest_id");
        int room_id = res.getInt("room_id");
        Date reservation_date = res.getDate("reservation_date");
        Date checkin_date = res.getDate("checkin_date");
        Date checkout_date = res.getDate("checkout_date");
        int adults = res.getInt("adults");
        int children = res.getInt("children");
        int payment_id = res.getInt("payment_id");

        return new Reservation(id, guest_id, room_id, reservation_date, checkin_date, checkout_date, adults, children, payment_id);
    }

    public static Reservation create(Reservation model) throws Exception {
        InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("reservations")
                .add("id", model.getId(), "i")
                .add("guest_id", model.getGuest_id(), "i")
                .add("room_id", model.getRoom_id(), "i")
                .add("reservation_date", model.getReservation_date(), "s")
                .add("checkin_date", model.getCheckInDate(), "s")
                .add("checkout_date", model.getCheckOutDate(), "s")
                .add("adults", model.getAdults(), "i")
                .add("children", model.getChildren(), "i")
                .add("payment_id", model.getPayment_id(), "i");

        int lastInsertedId = connection.execute(query);
        Reservation reservation = find(lastInsertedId);

        if (reservation != null) {
            SuccessPopupComponent.show("Successfully created", "Register");
            return reservation;
        }
        ErrorPopupComponent.show("Could not make reservation");
        return null;
    }


    public static Reservation update(Reservation model) throws Exception {
        // TODO:


        return null;
    }

    public static boolean remove(int id) throws  Exception{
        String query = "delete from reservations where id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);

        stmt.setInt(1 , id);

        Reservation reservation = find(id);

        if(reservation == null) {
            return true;
        }
        return false;
    }

}



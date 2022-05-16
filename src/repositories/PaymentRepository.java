package repositories;

import database.DBConnection;
import database.InsertQueryBuilder;
import models.Payments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class PaymentRepository {


    private static DBConnection connection = DBConnection.getConnection();


    public static Payments find(int id) throws Exception {
        String query = "select * from payments where id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);

        ResultSet result = stmt.executeQuery();
        if(result.next()) return fromResulSet(result);
        return null;
    }


    public static Payments fromResulSet(ResultSet result) throws Exception {

        int id = result.getInt("id");
        int guest_id = result.getInt("guest_id");
        int staff_id = result.getInt("staff_id");
        double price = result.getDouble("price");
        String payment_method = result.getString("payment_method");
        int is_payed = result.getInt("is_payed");
        Date pay_date = result.getDate("pay_date");

        return new Payments(id, guest_id, staff_id, price, payment_method, is_payed, pay_date);
    }

    public static Payments create(Payments model) throws Exception {
        InsertQueryBuilder query = (InsertQueryBuilder) InsertQueryBuilder.create("payments")
                .add("id", model.getId(), "i")
                .add("guest_id", model.getGuest_id(), "i")
                .add("staff_id", model.getStaff_id(), "i")
                .add("price", (float) model.getPrice(), "f")
                .add("payment_method", model.getPayment_method(), "s")
                .add("is_payed",  model.getIs_payed(), "i")
                .add("pay_date",  model.getPay_date(), "s");

        int lastInsertedId = connection.execute(query);
        Payments created = find(lastInsertedId);

        if(created != null) return created;

        return null;
    }






}

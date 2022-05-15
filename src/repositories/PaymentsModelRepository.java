package repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;
import models.Payments;
import models.view.PaymentModel;

public class PaymentsModelRepository {


    private static DBConnection connection = DBConnection.getConnection();


    public ArrayList<PaymentModel> findAll() throws Exception {
        String query = "select * from paymentmodel";
        ResultSet res = this.connection.executeQuery(query);
        ArrayList<PaymentModel> paymentModel = new ArrayList<>();

        while (res.next()) {
            paymentModel.add(fromResultSet(res));
        }
        return paymentModel;

    }


    public static Payments create(Payments model ) throws Exception {

        return null;
    }

    public static ArrayList<PaymentModel> filterPayments(String date) throws Exception {
        ArrayList<PaymentModel> payments = new ArrayList<PaymentModel>();

        String query = "select * from paymentmodel where date like ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, date);


        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            payments.add(fromResultSet(result));
        }
        if (payments != null) return payments;

        return null;
    }

    public static PaymentModel fromResultSet(ResultSet result) throws Exception {
        int id = result.getInt("payment_id");
        String fname = result.getString("firstname");
        String lname = result.getString("lastname");
        Date date = result.getDate("date");
        double price = result.getDouble("price");
        int isPayed = result.getInt("ispayed");

        return new PaymentModel(id, fname, lname, date, price, isPayed);
    }


    public static ArrayList<PaymentModel> filterPayment(String date) throws Exception {
        String query = "select * from paymentmodel where date = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1 , date);
        ArrayList<PaymentModel> payments = new ArrayList<>();
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            payments.add(fromResultSet(result));
        }
        if (payments != null) return payments;
        return null;
    }

    public static ArrayList<PaymentModel> findSpecificPayments(int userId) throws Exception {

        ArrayList<PaymentModel> specificPayments = new ArrayList<>();
        String query = "select pm.payment_id as payment_id, pm.firstname as firstname, pm.lastname as lastname ,\n" +
                "                pm.date as date,pm.price as price,pm.ispayed as ispayed from paymentmodel pm inner join payments p on pm.payment_id = p.id\n" +
                "                where p.guest_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);

        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            specificPayments.add(fromResultSet(result));
        }
        if (specificPayments != null) return specificPayments;

        return null;
    }
}

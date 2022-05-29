package repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.DBConnection;
import models.PaymentModel;
import models.Payments;

public class PaymentsModelRepository {

	private static final DBConnection connection = DBConnection.getConnection();

	public ArrayList<PaymentModel> findAll() throws Exception {
		String query = "select * from paymentmodel";
		ResultSet res = connection.executeQuery(query);
		ArrayList<PaymentModel> paymentModel = new ArrayList<>();

		while (res.next()) {
			paymentModel.add(fromResultSet(res));
		}
		return paymentModel;

	}

	public static ArrayList<PaymentModel> filterPayments(String date) throws Exception {
		ArrayList<PaymentModel> payments = new ArrayList<PaymentModel>();
		String query = "select * from paymentmodel where date = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, date);
		ResultSet result = stmt.executeQuery();
		while (result.next()) {
			payments.add(fromResultSet(result));
		}
		return payments;
	}

	public static PaymentModel fromResultSet(ResultSet result) throws Exception {
		int id = result.getInt("payment_id");
		String fname = result.getString("name");
		String lname = result.getString("lastname");
		Date date = result.getDate("date");
		double price = result.getDouble("price");
		int isPayed = result.getInt("ispayed");

		return new PaymentModel(id, fname, lname, date, price, isPayed);
	}

	@SuppressWarnings("unused")
	public static ArrayList<PaymentModel> filterPayment(String date) throws Exception {
		String query = "select * from paymentmodel where date = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, date);
		ArrayList<PaymentModel> payments = new ArrayList<>();
		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			payments.add(fromResultSet(result));
		}
		return payments;
	}

	public static ArrayList<PaymentModel> findSpecificPayments(int userId) throws Exception {

		ArrayList<PaymentModel> specificPayments = new ArrayList<>();
		String query = "select * from paymentmodel ,payments where payment_id = id and is_payed = ? and user_id = ?";

		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, 0);
		stmt.setInt(2, userId);

		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			specificPayments.add(fromResultSet(result));
		}
		return specificPayments;

	}
}

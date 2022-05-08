package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import models.view.PaymentModel;

public class PaymentsRepository {

  // private static Connection connection;
  // private static DBConnect dbConnection = new DBConnect();

  private DBConnection conn;

  public PaymentsRepository() {
    this.conn = DBConnection.getConnection();
  }

  public ArrayList<PaymentModel> findAll() throws SQLException {
    String query = "select * from paymentmodel";
    ResultSet res = this.conn.executeQuery(query);
    ArrayList<PaymentModel> paymentModel = new ArrayList<>();

    while (res.next()) {
      System.out.println("Room: " + res.getString("firstname"));
      paymentModel.add(PaymentModel.fromResultSet(res));
    }
    return paymentModel;

  }
  // private static PaymentModel parseFromRes(ResultSet res) throws Exception {

  // int payment_id = res.getInt("id");
  // String firstname = res.getString("first_name");
  // String lastname = res.getString("last_name");
  // Date date = res.getDate("checkin_date");
  // double price = res.getDouble("price");
  // int is_payed = res.getInt("is_payed");
  // String isPayed = null;

  // if (is_payed == 0) {
  // isPayed = "Jo e paguar";
  // } else if (is_payed == 1) {
  // isPayed = "E paguar";
  // }

  // // return new PaymentModel(payment_id, firstname, lastname, date, price,
  // // isPayed);
  // return null;

  // }

  // public static List<PaymentModel> selectAll() throws Exception {
  // ArrayList<PaymentModel> pModel = new ArrayList<>();
  // connection = dbConnection.getConnection();
  // PreparedStatement stmt = connection.prepareStatement("SELECT * FROM " +
  // "((reservations INNER JOIN payments ON reservations.payment_id = payments.id)
  // " +
  // "INNER JOIN guests ON reservations.guest_id = guests.id) " +
  // "WHERE checkin_date = CURRENT_DATE");
  // ResultSet res = stmt.executeQuery();
  // while (res.next()) {
  // pModel.add(parseFromRes(res));
  // }
  // return pModel;
  // }

  // public static List<PaymentModel> selectAllByDate(Date date) throws Exception
  // {
  // Connection connection = dbConnection.getConnection();

  // ArrayList<PaymentModel> pModel = new ArrayList<>();

  // PreparedStatement stmt = connection.prepareStatement(
  // "SELECT
  // r.payment_id,g.first_name,g.last_name,r.checkin_date,r.checkout_date,p.price,p.is_payed
  // \n" +
  // "FROM reservations r INNER JOIN payments p ON r.payment_id = p.id\n" +
  // "INNER JOIN guests g ON r.guest_id = g.id \n" +
  // "WHERE r.checkin_date = '" + date + "';");

  // ResultSet res = stmt.executeQuery();

  // while (res.next()) {
  // pModel.add(parseFromRes(res));
  // }
  // return pModel;
  // }

  // public static void guestInfo(int user, Label personalNr, Label nameSurname)
  // throws Exception {

  // connection = DBConnect.getConnection();
  // ResultSet guestNameId = connection.createStatement()
  // .executeQuery("select first_name, last_name, personal_number from guests\n" +
  // " where id=" + user);

  // while (guestNameId.next()) {
  // String rezultati = guestNameId.getString("first_name") + " " +
  // guestNameId.getString("last_name");
  // personalNr.setText(guestNameId.getString("personal_number"));
  // nameSurname.setText(rezultati);
  // }

  // }

  // public static double roomsBill(int user, int payment_id, ObservableList
  // oblist) throws Exception {
  // connection = DBConnect.getConnection();
  // double total = 0;
  // ResultSet tabela = connection.createStatement()
  // .executeQuery("select dh.room_number, dh.room_type, dh.price from rooms dh
  // \n" +
  // "inner join reservations r on r.room_id=dh.room_number " +
  // "inner join payments p on p.id = r.payment_id " +
  // "where r.guest_id = " + user + " and r.payment_id = " + payment_id + " and
  // p.is_payed = 0;");
  // while (tabela.next()) {

  // oblist.add(new Rooms(tabela.getInt("room_number"),
  // tabela.getString("room_type"), tabela.getDouble("price")));
  // total += tabela.getDouble("price");
  // }
  // return total;
  // }

  // public static double servicesBill(int user, int payment_id, ObservableList
  // oblist1) throws Exception {
  // connection = DBConnect.getConnection();
  // double total = 0;
  // ResultSet services = connection.createStatement()
  // .executeQuery("select st.service_name, st.price from services_type st \n" +
  // "inner join services s on s.service_id = st.id " +
  // "inner join payments p on p.id = s.payment_id " +
  // "where s.guest_id = " + user + " and s.payment_id = " + payment_id + " and
  // p.is_payed = 0;");
  // while (services.next()) {

  // oblist1.add(new Service_Type(services.getString("service_name"),
  // services.getDouble("price")));
  // total += services.getDouble("price");
  // }
  // return total;
  // }

  // public static void updatePayments(int user, int payment_id, String
  // metodaEzgjedhur) throws Exception {
  // String PaymentQuery = "update payments p \n" +
  // "left join reservations r on r.payment_id = p.id \n" +
  // "left join services s on s.payment_id = p.id \n" +
  // "set p.payment_method = '" + metodaEzgjedhur + "', p.is_payed = 1, p.pay_date
  // = now() \n" +
  // "where p.guest_id=" + user + " and p.id = " + payment_id;
  // Statement statement = connection.createStatement();
  // statement.executeUpdate(PaymentQuery);
  // }

  // public static ResultSet getUnpaidByDate(String date) throws Exception {
  // connection = DBConnect.getConnection();

  // String query = "SELECT DISTINCT
  // p.id,g.first_name,g.last_name,r.checkout_date,p.price \n" +
  // "FROM reservations r INNER JOIN payments p ON r.payment_id = p.id\n" +
  // "INNER JOIN guests g ON r.guest_id = g.id \n" +
  // "WHERE r.checkout_date = '" + date + "' and is_payed=0;";

  // Statement stmt = connection.createStatement();
  // ResultSet rs = stmt.executeQuery(query);

  // return rs;
  // }

  // public static ResultSet getDefaultUnpaid() throws Exception {
  // connection = DBConnect.getConnection();

  // String query = "SELECT DISTINCT
  // p.id,g.first_name,g.last_name,r.checkout_date,p.price \n" +
  // "FROM reservations r INNER JOIN payments p ON r.payment_id = p.id\n" +
  // "INNER JOIN guests g ON r.guest_id = g.id \n" +
  // "WHERE is_payed=0;";

  // Statement stmt = connection.createStatement();
  // ResultSet rs = stmt.executeQuery(query);

  // return rs;
  // }

}

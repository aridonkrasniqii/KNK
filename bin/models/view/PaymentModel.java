package models.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentModel {
  private int payment_id;
  private String firstname;
  private String lastname;
  private Date date;
  private double price;
  private int isPayed;

  public PaymentModel() {
  }

  public PaymentModel(int payment_id, String firstname, String lastname, Date date, double price, int isPayed) {
    this.payment_id = payment_id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.date = date;
    this.price = price;
    this.isPayed = isPayed;
  }

  public static PaymentModel fromResultSet(ResultSet res) throws SQLException {
    int id = res.getInt("payment_id");
    String firstname = res.getString("firstname");
    String lastname = res.getString("lastname");
    Date date = res.getDate("date");
    double price = res.getDouble("price");
    int isPayed = res.getInt("ispayed");
    PaymentModel paymentModel = new PaymentModel(id, firstname, lastname, date, price, isPayed);

    return paymentModel;
  }

  public int getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(int payment_id) {
    this.payment_id = payment_id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getIspayed() {
    return this.isPayed;
  }

  public void setIspayed(int isPayed) {
    this.isPayed = isPayed;
  }

}

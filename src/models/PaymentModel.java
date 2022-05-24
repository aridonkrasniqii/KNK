package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PaymentModel {
  private int payment_id;
  private String name;
  private String username;
  private Date date;
  private double price;
  private int isPayed;

  public PaymentModel() {
  }

  public PaymentModel(int payment_id, String name, String username, Date date, double price, int isPayed) {
    this.payment_id = payment_id;
    this.name = name;
    this.username = username;
    this.date = date;
    this.price = price;
    this.isPayed = isPayed;
  }

  public static PaymentModel fromResultSet(ResultSet res) throws SQLException {
    int id = res.getInt("payment_id");
    String name = res.getString("name");
    String username = res.getString("username");
    Date date = res.getDate("date");
    double price = res.getDouble("price");
    int isPayed = res.getInt("ispayed");
    PaymentModel paymentModel = new PaymentModel(id, name, username, date, price, isPayed);

    return paymentModel;
  }

  public int getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(int payment_id) {
    this.payment_id = payment_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

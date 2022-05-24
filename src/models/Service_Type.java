package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Service_Type {

  private int id;
  private String service_name;
  private double price;
  private int quantity;

  public Service_Type() {
  };

  public static Service_Type fromResultSet(ResultSet res) throws SQLException, Exception {
    int id = res.getInt("id");
    String service_name = res.getString("service_name");
    double price = res.getDouble("price");
    int quantity = res.getInt("quantity");
    Service_Type service_type = new Service_Type(id, service_name, price, quantity);
    return service_type;
  }

  public Service_Type(int id, String service_name, double price, int quantity) {
    this.id = id;
    this.service_name = service_name;
    this.price = price;
    this.quantity = quantity;
  }

  public Service_Type(String service_name, double price) {
    this.service_name = service_name;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public String getService_name() {
    return this.service_name;
  }

  public double getPrice() {
    return this.price;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setService_name(String service_name) {
    this.service_name = service_name;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}

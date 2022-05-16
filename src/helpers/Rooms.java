package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rooms {
  private int room_number;
  private int floor_number;
  private int capacity;
  private int bed_number;
  private String room_type;
  private double price;

  public Rooms() {
  }

  public Rooms(int room_number, int floor_number, int capacity, int bed_number, String room_type, double price) {
    this.room_number = room_number;
    this.floor_number = floor_number;
    this.capacity = capacity;
    this.bed_number = bed_number;
    this.room_type = room_type;
    this.price = price;
  }

  public static Rooms fromResultSet(ResultSet res) throws Exception {

    int room_number = res.getInt("room_number");
    int floor_number = res.getInt("floor_number");
    int capacity = res.getInt("capacity");
    int bed_number = res.getInt("bed_number");
    String room_type = res.getString("room_type");
    double price = res.getDouble("price");
    Rooms rooms = new Rooms(room_number, floor_number, capacity, bed_number, room_type, price);
    return rooms;
  }


  public Rooms(int room_number, String room_type, double price) {
    this.room_number = room_number;
    this.room_type = room_type;
    this.price = price;
  }

  public int getRoom_number() {
    return this.room_number;
  }

  public void setRoom_number(int room_number) {
    this.room_number = room_number;
  }

  public int getFloor_number() {
    return this.floor_number;
  }

  public void setFloor_number(int floor_number) {
    this.floor_number = floor_number;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getBed_number() {
    return bed_number;
  }

  public void setBed_number(int bed_number) {
    this.bed_number = bed_number;
  }

  public String getRoom_type() {
    return room_type;
  }

  public void setRoom_type(String room_type) {
    this.room_type = room_type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}

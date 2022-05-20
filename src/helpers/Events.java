package helpers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import helpers.Events;
import java.util.Locale.Category;

public class Events {
	private int id;
	private String title;
	private String organizer;
	private String category;
	private Double price;
	private String start_date;
	private String end_date;

  public Events() {
  }

  public Events(String title, String organizer, String category, Double price, String start_date, String end_date) {
//    this.id = id;
	this.title = title;
    this.organizer = organizer;
    this.category = category;
    this.price = price;
    this.start_date = start_date;
    this.end_date = end_date;
  }

  public static Events fromResultSet(ResultSet res) throws Exception {
	  int id = res.getInt("id");
	  String title = res.getString("title");
	  String organizer = res.getString("organizer");
	  String category = res.getString("category");
	  Double price = res.getDouble("price");
	  String start_date = res.getString("start_date");
	  String end_date = res.getString("end_date");
    
    Events events = new Events(title, organizer, category, price, start_date, end_date);
    return events;
  }

  public int getId() {
	return this.id;
  }
  
  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public String getOrganizer() {
      return organizer;
  }

  public void setOrganizer(String organizer) {
      this.organizer = organizer;
  }

  public String getCategory() {
      return category;
  }

  public void setCategory(String category) {
      this.category = category;
  }

  public Double getPrice() {
      return price;
  }

  public void setPrice(Double price) {
      this.price = price;
  }

  public String getStart_date() {
      return start_date;
  }

  public void setStart_date(String start_date) {
      this.start_date = start_date;
  }

  public String getEnd_date() {
      return end_date;
  }

  public void setEnd_date(String end_date) {
      this.end_date = end_date;
  }


//  public Rooms(int room_number, String room_type, double price) {
//    this.room_number = room_number;
//    this.room_type = room_type;
//    this.price = price;
//  }

  
}

package models;


import java.util.Date;

public class Events {
    private int id;
    private String title;
    private String organizer;
    private String category;
    private double price;
    private Date start_date;
    private Date end_date;


    public Events(int id, String title, String organizer, String category, double price, Date start_date,
                  Date end_date) {
        this.id = id;
        this.title = title;
        this.organizer = organizer;
        this.category = category;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    public int getId() {
        return id;
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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStart_date() {
        return start_date;
    }


    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }


    public Date getEnd_date() {
        return end_date;
    }


    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

}

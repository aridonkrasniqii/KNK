package models;


import java.util.Date;

public class Events {
    private int id;
    private String emri;
    private String organizer;
    private String category;
    private String location;
    private Date start_date;
    private Date end_date;


    public Events(int id, String emri, String organizer, String category, String location, Date start_date,
                  Date end_date) {
        super();
        this.id = id;
        this.emri = emri;
        this.organizer = organizer;
        this.category = category;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
    }


    public int getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }


    public void setEmri(String emri) {
        this.emri = emri;
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


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
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

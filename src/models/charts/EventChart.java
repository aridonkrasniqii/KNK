package models.charts;

public class EventChart {

    private int count;
    private String organizer;

    public EventChart(int count , String organizer){
        this.count = count;
        this.organizer = organizer;
    }

    public int getCount() {
        return count;
    }

    public String getOrganizer() {
        return organizer;
    }
}



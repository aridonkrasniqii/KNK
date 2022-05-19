package models.charts;

public class StaffChart {
    private int count;
    private String position;



    public StaffChart(int count, String position){
        this.count = count;
        this.position = position;
    }

    public int getCount() {
        return count;
    }

    public String getPosition() {
        return position;
    }
}


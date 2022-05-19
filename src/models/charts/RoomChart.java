package models.charts;

import repositories.RoomRepository;

public class RoomChart {

    private int count;
    private String roomType;

    public RoomChart(int count , String roomType) {
        this.count = count;
        this.roomType = roomType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}

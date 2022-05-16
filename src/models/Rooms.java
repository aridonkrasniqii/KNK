package models;

public class Rooms {
	private int room_number;
	private int floor_number;
	private int capacity;
	private int bed_number;
	private String room_type;
	private double price;

	Rooms(int room_number, int floor_number, int capacity, int bed_number, String room_type, double price){
		this.room_number = room_number;
		this.floor_number = floor_number;
		this.capacity = capacity;
		this.bed_number = bed_number;
		this.room_type = room_type;
		this.price = price;
	}

	public int getFloor_number() {
		return floor_number;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getRoom_number() {
		return room_number;
	}

	public String getRoom_type() {
		return room_type;
	}
}
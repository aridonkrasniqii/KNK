package model;

public class reservations {
	private int id;
	private int guest_id;
	private int room_id;
	private Date reservation_date;
	private Date checkin_date;
	private Date checkout_date;
	private int adults;
	private int children;
	private int payment_id;
	
	reservations(int id, int guest_id, int room_id, Date reservation_date, Date checkin_date, Date checkout_date, int adults, int children, int payment_id){
		this.id = id;
		this.guest_id = guest_id;
		this.room_id = room_id;
		this.reservation_date = reservation_date;
		this.checkin_date = checkin_date;
		this.checkout_date = checkout_date;
		this.adults = adults;
		this.children = children;
		this.payment_id = payment_id;
	}
	
	public int getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(int guest_id) {
		this.guest_id = guest_id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public Date getCheckout_date() {
		return checkout_date;
	}
	public void setCheckout_date(Date checkout_date) {
		this.checkout_date = checkout_date;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getId() {
		return id;
	}
	public Date getCheckin_date() {
		return checkin_date;
	}
	
}

/*
CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `adults` int(11) DEFAULT '1',
  `children` int(11) DEFAULT '1',
  `payment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  KEY `room_id` (`room_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_number`),
  CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
);
*/ 

package model;

public class payments {
   private int id;
   private int guest_id;
   private int staff_id;
   private double price;
   private String payment_method;
   private int is_payed;
   private String pay_date;
public payments(int id, int guest_id, int staff_id, double price, String payment_method, int is_payed,
		String pay_date) {
	super();
	this.id = id;
	this.guest_id = guest_id;
	this.staff_id = staff_id;
	this.price = price;
	this.payment_method = payment_method;
	this.is_payed = is_payed;
	this.pay_date = pay_date;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getPayment_method() {
	return payment_method;
}
public void setPayment_method(String payment_method) {
	this.payment_method = payment_method;
}
public int getIs_payed() {
	return is_payed;
}
public void setIs_payed(int is_payed) {
	this.is_payed = is_payed;
}
public String getPay_date() {
	return pay_date;
}
public void setPay_date(String pay_date) {
	this.pay_date = pay_date;
}
public int getId() {
	return id;
}
public int getGuest_id() {
	return guest_id;
}
public int getStaff_id() {
	return staff_id;
}



}

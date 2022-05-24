package models;

import java.util.Date;

public class Reservation {
  private int id;
  private int guest_id;
  private int room_id;
  private Date reservation_date;
  private Date checkInDate;
  private Date checkOutDate;
  private int adults;
  private int children;
  private int payment_id;

  public Reservation() {
  }

  public Reservation(int id, int guest_id, int room_id,Date reservation_date, Date checkInDate, Date checkOutDate, int adults, int children,
      int payment_id) {
    this.id = id;
    this.guest_id = guest_id;
    this.room_id = room_id;
    this.reservation_date = reservation_date;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.adults = adults;
    this.children = children;
    this.payment_id = payment_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public Date getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public Date getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
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
}

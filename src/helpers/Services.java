package helpers;

public class Services {
  private int id;
  private int guest_id;
  private int service_id;
  private int quantity;
  private int payment_id;

  public Services() {
  }

  public Services(int guest_id, int service_id, int quantity, int payment_id) {
    this.guest_id = guest_id;
    this.service_id = service_id;
    this.quantity = quantity;
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

  public int getService_id() {
    return service_id;
  }

  public void setService_id(int service_id) {
    this.service_id = service_id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getPayment_id() {
    return payment_id;
  }

  public void setPayment_id(int payment_id) {
    this.payment_id = payment_id;
  }
}

package model;

public class Services {
	private int id;
	private int guest_id;
	private int service_id;
	private int quantity;
	private int payment_id;

	public Services(int id, int guest_id, int service_id, int quantity, int payment_id) {
		this.id = id;
		this.guest_id = guest_id;
		this.service_id = service_id;
		this.quantity = quantity;
		this.payment_id = payment_id;
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
	public int getId() {
		return id;
	}
	public int getPayment_id() {
		return payment_id;
	}
	
	

}

/*CREATE TABLE `services` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`guest_id` int(11) NOT NULL,
`service_id` int(11) NOT NULL,
`quantity` int(11) NOT NULL,
`payment_id` int(11) NOT NULL,
PRIMARY KEY (`id`),
KEY `guest_id` (`guest_id`),
KEY `service_id` (`service_id`),
KEY `payment_id` (`payment_id`),
CONSTRAINT `services_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
CONSTRAINT `services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services_type` (`id`),
CONSTRAINT `services_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
CONSTRAINT `services_chk_1` CHECK ((`quantity` > 0))
) ;
*/

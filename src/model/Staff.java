package model;

public class Staff {
	private int id;
	private String last_name;
	private String first_name;
	private int personal_number;
	private String position;

	private String birthdate;
	private int phone_number;
	private double salary;
	private String password;
	private String gender;
	public Staff(int id, String last_name, String first_name, int personal_number, String position, String birthdate,
			int phone_number, double salary, String password, String gender) {
		super();
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.personal_number = personal_number;
		this.position = position;
		this.birthdate = birthdate;
		this.phone_number = phone_number;
		this.salary = salary;
		this.password = password;
		this.gender = gender;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public int getPersonal_number() {
		return personal_number;
	}
	public String getBirthdate() {
		return birthdate;
	}
	
}

package model;

public class Guest {
	private int id;
	private String last_name;
	private String first_name;
	private int personal_number;
	private String birthdate;
	private int phone_number;
	private String registred_date;
	private String gender;
	
	Guest(int id , String last_name , String first_name, int personal_number , String birthdate, int phone_number, String registred_date, String gender){
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.personal_number = personal_number;
		this.birthdate = birthdate;
		this.phone_number = phone_number;
		this.registred_date = registred_date;
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

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
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

	public String getRegistred_date() {
		return registred_date;
	}
	
	
	
	
}

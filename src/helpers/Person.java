package helpers;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String personalNumber;
	private String phoneNumber;
	private String gender;
	private String birthdate;

	public Person() {
	}

	public Person(int id, String firstName, String lastName, String personalNumber, String phoneNumber, String gender,
			String birthdate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personalNumber = personalNumber;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}

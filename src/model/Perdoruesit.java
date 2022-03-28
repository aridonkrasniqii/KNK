package model;

public class Perdoruesit {
 private String username;
 private String hashedPassword;
public Perdoruesit(String username, String hashedPassword) {
	super();
	this.username = username;
	this.hashedPassword = hashedPassword;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getHashedPassword() {
	return hashedPassword;
}
 
}

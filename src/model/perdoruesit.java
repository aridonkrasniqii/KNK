package model;

public class perdoruesit {
 private String username;
 private String hashedPassword;
public perdoruesit(String username, String hashedPassword) {
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

package com.kukvar.model;

public class SignedUser {
	public int id;
	public String firstName, lastName, email, picture;
	private boolean verified_email;
	
	public SignedUser() {
		super();
	}

	public SignedUser(int id, String firstName, String lastName, String email, String picture, boolean verified_email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.picture = picture;
		this.verified_email = verified_email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isVerified_email() {
		return verified_email;
	}

	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

	@Override
	public String toString() {
		return "SignedUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
	
	
/*
	public SignedUser(String email) {
		super();
		this.email = email;
	}
*/



/*
	public SignedUser(String id, String email, String picture, boolean verified_email) {
		super();
		this.id = id;
		this.email = email;
		this.picture = picture;
		this.verified_email = verified_email;
	}
*/

	
	
}

package com.kukvar.hibernate.entity;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@SuppressWarnings("unused")
	private String street;
	@SuppressWarnings("unused")
	private String city;
	@SuppressWarnings("unused")
	private String zipcode;
	
	public Address() {}
	public Address(String street, String city, String zipcode) {
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}	
	
}

package com.kukvar.hibernate.entity;

import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="user_info")
@Table(name="user_info")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="email", nullable=false)
	private String email;	
	@Column(name="first_name")
	private String first_name;	
	@Column(name="last_name")
	private String last_name;	
	@Column(name="date_birth")
	private LocalDate dateBirth;
	@Column(name="phone")
	private String phone;	

	@Embedded
	@AttributeOverrides( {
		@AttributeOverride(name="street", column=@Column(name="home_street")),
		@AttributeOverride(name="city", column=@Column(name="home_city")),
		@AttributeOverride(name="zipcode", column=@Column(name="home_zipcode"))
	} )
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides( {
		@AttributeOverride(name="street", column=@Column(name="billing_street")),
		@AttributeOverride(name="city", column=@Column(name="billing_city")),
		@AttributeOverride(name="zipcode", column=@Column(name="billing_zipcode"))
	} )
	private Address billingAddress;

	public UserInfo() {	}

	/**
	 * @param email
	 * @param first_name
	 * @param last_name
	 * @param dateBirth
	 * @param phone
	 * @param homeAddress
	 * @param billingAddress
	 */
	public UserInfo(String email, String first_name, String last_name, LocalDate dateBirth, String phone,
			Address homeAddress, Address billingAddress) {
		super();
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dateBirth = dateBirth;
		this.phone = phone;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", email=" + email + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", dateBirth=" + dateBirth + ", phone=" + phone + ", homeAddress=" + homeAddress + ", billingAddress="
				+ billingAddress + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	

	
	
}

package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kukvar.hibernate.entity.Customers;

class UsersDAOTest {
	private static final String TESTED_EMAIL = "balda1@yahoo.com";
	private static Customers testedCustomer;
	private static final String TESTED_EMAIL_DELETE = "balda2@yahoo.com";
	private static Customers testedCustomerDelete;
	int id, id_delete;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
	}
	
	@BeforeEach
	void setUp() throws Exception {
		testedCustomer = new Customers(TESTED_EMAIL,"Peter Balda","balda");
		testedCustomerDelete = new Customers(TESTED_EMAIL_DELETE,"Peter Balda Young","balda");				
		try {
			new UsersDAO().addCustomersDetails(testedCustomer);
			new UsersDAO().addCustomersDetails(testedCustomerDelete);
		} catch (Exception e) {
			System.out.println("BeforeEach: "+e);
		}
		id = new UsersDAO().getCustomer(TESTED_EMAIL).getId();
		id_delete = new UsersDAO().getCustomer(TESTED_EMAIL_DELETE).getId();
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			new UsersDAO().deleteCustomer(id);
			new UsersDAO().deleteCustomer(id_delete);
		} catch (Exception e) {
			System.out.println("AfterEach: "+e);
		}
	}
	
	@Test
	final void testAddCustomersDetails() {
		String email = "TESTED_EMAIL";
		Customers customer = new Customers(email,"Peter Balda","balda");
		new UsersDAO().addCustomersDetails(customer);
		assertNotNull(new UsersDAO().getCustomer(email), "The customer not added.");
		int id = new UsersDAO().getCustomer(email).getId();
		new UsersDAO().deleteCustomer(id);
	}

	@Test
	final void testListCustomers() {
		List<Customers> customers = new UsersDAO().listCustomers();
		assertNotNull(customers, "The customers list do not returned.");
	}

	@Test
	final void testUpdateInformation() {
		
		new UsersDAO().updateInformation(id, TESTED_EMAIL, "Maria Balda");
		assertEquals("Maria Balda", new UsersDAO().getCustomer(id).getUsername(),"The user's name do not updated.");
	}

	@Test
	final void testGetCustomer() {
		assertNotNull(new UsersDAO().getCustomer(id), "Do not return class Customers.");
		assertEquals(id, new UsersDAO().getCustomer(id).getId(),"Return wrong Customers id");
	}
	
	@Test
	final void testGetCustomerViaEmail() {
		assertNotNull(new UsersDAO().getCustomer(TESTED_EMAIL), "Do not return class Customers.");
		assertEquals(TESTED_EMAIL, new UsersDAO().getCustomer(TESTED_EMAIL).getEmail(),"Return wrong Customer");		
			
	}
	
	@Test
	final void testIsExisted() {
		assertTrue(new UsersDAO().isExisted(TESTED_EMAIL),"A user existed but returned not existed result.");		
	}

	@Test
	final void testDeleteCustomer() {
		new UsersDAO().deleteCustomer(id_delete);
		testedCustomerDelete = new UsersDAO().getCustomer(id_delete);
		assertTrue(testedCustomerDelete == null, "The user do not deleted.");	
	}

}

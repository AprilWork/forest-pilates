package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.kukvar.hibernate.entity.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersDAOTest {
	private static final String EMAIL = "balda1@yahoo.com";
	private static User testedCustomer = new User(EMAIL,"Peter Balda","balda");
	int id;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {	
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	@Order(1)
	final void testAddUserDetails() {
		id = new UsersDAO().addUserDetails(testedCustomer);
		assertNotNull(new UsersDAO().getUser(id), "The user not added.");
	}

	@Test
	@Order(2)
	final void testListUsers() {
		List<User> users = new UsersDAO().listUsers();
		assertNotNull(users, "The users list do not returned.");
	}

	@Test
	@Order(3)
	final void testIsExisted() {
		assertTrue(new UsersDAO().isExisted(EMAIL),"A user existed but returned not existed result.");		
	}
	
	@Test
	@Order(4)
	final void testGetUserViaEmail() {
		assertNotNull(new UsersDAO().getUser(EMAIL), "User do not returned.");
		assertEquals(EMAIL, new UsersDAO().getUser(EMAIL).getEmail(),"Return wrong user");		
	}	

	@Test
	@Order(5)
	final void testGetCustomer() {
		assertNotNull(new UsersDAO().getUser(id), "User do not returned.");
		assertEquals(id, new UsersDAO().getUser(id).getId(),"Returned wrong user id");
	}	
	
	@Test
	@Order(6)
	final void testUpdateInformation() {
		new UsersDAO().updateInformation(id, EMAIL, "Maria Balda");
		assertEquals("Maria Balda", new UsersDAO().getUser(id).getUsername(),"The user's name do not updated.");
	}

	@Test
	@Order(7)
	final void testDeleteCustomer() {
		new UsersDAO().deleteUser(id);
		assertNull(new UsersDAO().getUser(id), "The user do not deleted.");	
	}

}

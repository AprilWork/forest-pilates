package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLIntegrityConstraintViolationException;
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
	private static final String EMAIL = "tester@yahoo.com";
	private static final String NAME = "Peter Tester";
	private static final String PASSWORD = "test";
	private User testedCustomer = new User(EMAIL,NAME,PASSWORD);
	static int id, id2;

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
		try {
			id = new UsersDAO().addUserDetails(testedCustomer);
		} catch (Throwable e) {
			System.out.println("I catch: "+e.getMessage());
			e.printStackTrace();
		}
		assertNotNull(new UsersDAO().getUser(id), "The user not added.");
		
		try {
			assertThrows(SQLIntegrityConstraintViolationException.class
					,() -> new UsersDAO().addUserDetails(testedCustomer)
					,"The wrong throwable in case of update to duplicate entry.");
		} catch (Throwable e) {
			System.out.println("I catch: "+e.getMessage());
			e.printStackTrace();
		}	
			
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
		try {
			new UsersDAO().updateInformation(id, EMAIL);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("I catch: "+e.getMessage());
			e.printStackTrace();
		}
		//assertEquals(NAME+1, new UsersDAO().getUser(id).getUsername(),"The user's name do not updated.");
	}

	@Test
	@Order(7)
	final void testDeleteCustomer() {
		try {
			new UsersDAO().deleteUser(id);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("I catch: "+e.getMessage());
			e.printStackTrace();
		}
		assertNull(new UsersDAO().getUser(id), "The user do not deleted.");	
	}

/*	
	@Test
	@Order(8)
	final void testDuplicateUserDetails() {
		final String DUPLICATE_MAIL = "duplicate@yahoo.com";
		testedCustomer.setEmail(DUPLICATE_MAIL);
		try {
			id = new UsersDAO().addUserDetails(testedCustomer);
			assertNotNull(new UsersDAO().getUser(id), "The user not added.");
			assertThrows(SQLIntegrityConstraintViolationException.class
					,() -> new UsersDAO().addUserDetails(testedCustomer)
					,"The wrong throwable in case of add a duplicate entry.");
			testedCustomer.setEmail(EMAIL);
			id2 = new UsersDAO().addUserDetails(testedCustomer);
			assertThrows(SQLIntegrityConstraintViolationException.class
					,() -> new UsersDAO().updateInformation(id2,DUPLICATE_MAIL,testedCustomer.getUsername())
					,"The wrong throwable in case of update to duplicate entry.");

		} catch (Throwable e1) {
			System.out.println("I catch: "+e1.getMessage());
			e1.printStackTrace();
		} finally {
			try {
				new UsersDAO().deleteUser(id);
				new UsersDAO().deleteUser(id2);
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("I catch : "+e.getMessage());
				e.printStackTrace();
			}
		}	
	}
*/	

}

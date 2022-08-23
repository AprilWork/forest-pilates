package com.kukvar.hibernate.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kukvar.hibernate.utils.HibernateUtil;

class UserTest {
	static Session session;
	Transaction txn;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if(session != null) { session.close();}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testAddUser() {
		//fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();
			User user = new User("testUser3@yahoo.com", "tested","1111111111");
			Address address = new Address("Green str", "Orange", "45678");
			//UserInfo userInfo = new UserInfo("John", "Doe", LocalDate.parse("2022-05-16"), address, address, user);
			UserInfo userInfo = new UserInfo(null, null, null, null, null, user);
			session.persist(userInfo);
			assertNotNull(userInfo.getUser(),"The user do not added");
			assertNotNull(session.get(UserInfo.class, user.getId()),"The user index do not equal to userInfo index.");
			txn.rollback();
			//txn.commit();
		} catch (Exception e) {
			if(txn != null) { txn.rollback(); }
			e.printStackTrace();
		}		
	}

}

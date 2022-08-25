package com.kukvar.hibernate.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.kukvar.hibernate.utils.HibernateUtil;

class InstructorTest {

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


	@Test
	final void testSomething() {
		fail("Not yet implemented"); // TODO
		
	}

	
	@Test
	final void testCreateInsctructor() {
		//fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();
			Instructor instructor1 = new Instructor("Instructor1");
			Instructor instructor2 = new Instructor("Instructor2");
			
			session.persist(instructor1);
			session.persist(instructor2);
			
			assertFalse("Instructor1".equals(instructor2.getName()),"Name of different instructors are equal.");
			
			txn.commit();
			//txn.rollback();
		} catch (Exception e) {
			if(txn != null) { txn.rollback();}
			e.printStackTrace();
		}
	}

	
	@Test
	final void testAddActivity() {
		fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();

			txn.rollback();
			//txn.commit();
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
		}
	}	
	
	@Test
	final void testRemoveActivity() {
		fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();

			txn.rollback();
			//txn.commit();
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
		}
	}

	@Test
	final void testSetName() {
		fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();
			
			txn.rollback();
			//txn.commit();			
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
			e.printStackTrace();
		}
	}
		
	
	

}

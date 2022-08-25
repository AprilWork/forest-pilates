package com.kukvar.hibernate.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kukvar.hibernate.utils.HibernateUtil;

class ActivityTest {


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
	final void testCreateActivity() {
		//fail("Not yet implemented"); // TODO
		txn = session.getTransaction();
		try {
			txn.begin();
			
			Category category1 = new Category("Category 001");
			Category category2 = new Category("Category 002");
			
			Group group1 = new Group("Best group", "We invited you to our group.", "image.jpg", category1);
			Group group2 = new Group("Second group", "Please, booking second group!", "image.jpg", category2);
			
			category1.addGroup(group1);
			category1.addGroup(group2);
			
			session.persist(category1);
			session.persist(category2);
			
			Instructor instructor1 = new Instructor("Instructor Fillip");
			Instructor instructor2 = new Instructor("Instructor Anna");
			
			Activity activity1 = new Activity(group1, instructor1, DayOfWeek.SUNDAY, LocalTime.of(10, 00), LocalTime.of(11, 00), 10, 0);
			Activity activity2 = new Activity(group1, instructor1, DayOfWeek.SATURDAY, LocalTime.of(14, 00), LocalTime.of(15, 00), 10, 0);
			
			instructor1.addActivitiy(activity1);
			instructor2.addActivitiy(activity2);
			
			group1.addActivitiy(activity1);
			group2.addActivitiy(activity2);
			
			session.persist(activity1);
			session.persist(activity2);
			
			
			//txn.rollback();
			txn.commit();
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
		}		
	}

	@Test
	final void testSetGroup() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetInstructor() {
		fail("Not yet implemented"); // TODO
	}

}

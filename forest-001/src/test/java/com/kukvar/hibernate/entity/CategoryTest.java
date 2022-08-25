package com.kukvar.hibernate.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.kukvar.hibernate.utils.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryTest {
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
	

	@Test
	@Order(1)
	final void testAddGroup() {
		txn = session.getTransaction();
		try {
			txn.begin();
			Category category1 = new Category("category 1");
			Category category2 = new Category("category 2");
		
			Group group1 = new Group("Hello 1", "Class of category 1", "default.jpg", category1);
			Group group2 = new Group("Hello 2", "Class of category 1", "default.jpg", category1);
			
			category1.addGroup(group1);
			category1.addGroup(group2);
			
			session.persist(category1);
			session.persist(category2);
			
			assertEquals(2, category1.getGroups().size(),"A groups do not added to category set.");
			
			//txn.commit();
			txn.rollback();
		} catch (Exception e) {
			if(txn != null) { txn.rollback();}
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	final void testRemoveGroup() {
		txn = session.getTransaction();
		try {
			txn.begin();
			Category category1 = new Category("category 21");
			Category category2 = new Category("category 22");
		
			Group group1 = new Group("Hello 21", "Class of category 21", "default.jpg", category1);
			Group group2 = new Group("Hello 22", "Class of category 21", "default.jpg", category1);
			
			category1.addGroup(group1);
			category1.addGroup(group2);
			
			session.persist(category1);
			session.persist(category2);
			
			assertEquals(2, category1.getGroups().size(),"A groups do not added to category set.");
			assertEquals(0, category2.getGroups().size(),"A groups do not added to category set.");
						
			category1.removeGroup(group1);
			session.persist(category1);
			
			category2.addGroup(group1);
			session.persist(category2);
			
			assertEquals(1, category1.getGroups().size(),"The group do not removed from category set.");
			assertEquals(1, category2.getGroups().size(),"The removed group do not added to category set.");
							
			txn.rollback();
			//txn.commit();
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
		}
	}

	@Test
	final void testSetName() {
		txn = session.getTransaction();
		try {
			txn.begin();
			Category category1 = new Category("category 11");
			Category category2 = new Category("category 12");
		
			Group group1 = new Group("Hello 11", "Class of category 11", "default.jpg", category1);
			Group group2 = new Group("Hello 12", "Class of category 11", "default.jpg", category1);
			
			category1.addGroup(group1);
			category1.addGroup(group2);
			
			session.persist(category1);
			session.persist(category2);
			
			assertEquals(2, category1.getGroups().size(),"A groups do not added to category set.");
			
			category1.setName("category 13");
			session.persist(category1);
			assertTrue("category 13".equals(category1.getName()),"Category name do not changed");
			assertTrue("category 13".equals(group1.getCategory().getName()),"Category name in group do not changed.");
			
			category1.removeGroup(group1);
			session.persist(category1);
			
			category2.addGroup(group1);
			session.persist(category2);
			
			category2.setName("category 213");
			session.persist(category2);
			assertTrue("category 213".equals(category2.getName()),"Category name do not changed");
			assertTrue("category 213".equals(group1.getCategory().getName()),"Category name in group do not changed.");
			
			txn.rollback();
			//txn.commit();			
		} catch (Exception e) {
			if (txn != null) { txn.rollback();}
			e.printStackTrace();
		}
	}
	


}

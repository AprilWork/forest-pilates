package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.entity.Group;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryDAOTest {
	private static final String NAME = "Tested Category";
	private Category testedCategory = new Category(NAME);
	static int id;

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
	final void testListCategory() {
		List<Category> categories = new CategoryDAO().listCategory();
		assertNotNull(categories, "The categories list do not returned.");
	}

	@Test
	@Order(2)
	final void testAddCategoryDetails() {
		try {
			id = new CategoryDAO().addCategoryDetails(testedCategory);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(new CategoryDAO().getCategory(id), "The category not added.");
	}

	@Test
	@Order(3)
	final void testIsExisted() {
		assertTrue(new CategoryDAO().isExisted(NAME), "Category existed  but return false");
	}

	@Test
	@Order(4)
	final void testGetCategoryString() {
		assertNotNull(new CategoryDAO().getCategory(NAME), "Do not return class Category.");
		assertEquals(NAME, new CategoryDAO().getCategory(NAME).getName(), "Return wrong category via name");
	}

	@Test
	@Order(5)
	final void testGetCategoryInt() {
		assertNotNull(new CategoryDAO().getCategory(id), "Do not return class Category.");
		assertEquals(NAME, new CategoryDAO().getCategory(id).getName(), "Return wrong category via name");
	}

	@Test
	@Order(6)
	final void testUpdateInformation() {
		try {
			new CategoryDAO().updateInformation(id, NAME + 1);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(NAME + 1, new CategoryDAO().getCategory(id).getName(), "The category's name do not updated.");
	}

	@Test
	@Order(7)
	final void testDeleteCategory() {
		try {
			new CategoryDAO().deleteCategory(id);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(new CategoryDAO().getCategory(id), "The class category do not deleted.");
		
		 assertFalse( new GroupsDAO().listGroups() .stream().anyMatch(t ->
		 t.getCategory().getId() == id) );
		
	}
	
	 @Test 
	 @Order(8) 
	 final void testDeleteCascade() { try {
		id = new
		 CategoryDAO().addCategoryDetails(testedCategory);
	} catch (SQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	 @SuppressWarnings("unused") 
	 List<Integer> groups = Arrays.asList( new
	 GroupsDAO().addGroupDetails(new Group(NAME+"Group1", NAME, "",
	 testedCategory)), new GroupsDAO().addGroupDetails(new Group(NAME+"Group2",
	 NAME, "", testedCategory)), new GroupsDAO().addGroupDetails(new
	 Group(NAME+"Group3", NAME, "", testedCategory)) ); try {
		new
		 CategoryDAO().deleteCategory(id);
	} catch (SQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} assertFalse( new GroupsDAO().listGroups()
	 .stream().anyMatch(t -> t.getCategory().getId() == id) ); }
	 
}

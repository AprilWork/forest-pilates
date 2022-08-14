package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kukvar.hibernate.entity.Category;

class CategoryDAOTest {
	private static final String TESTED_CATEGORY_NAME = "Tested";
	private static final String TESTED_CATEGORY_DELETE = "Tested_delete";
	private Category testedCategoryDelete;
	private Category testedCategory;
	private int id, id_delete;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		id = 20;
		id_delete = 120;
		testedCategory = new Category(id,TESTED_CATEGORY_NAME);
		testedCategoryDelete = new Category(id_delete,TESTED_CATEGORY_DELETE);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		try {
			new CategoryDAO().deleteCategory(id);
			new CategoryDAO().deleteCategory(id_delete);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Test
	final void testAddCategoryDetails() {
		new CategoryDAO().addCategoryDetails(testedCategory);
		assertNotNull(new CategoryDAO().getCategory(id), "The category not added.");
	}

	@Test
	final void testListCategory() {
		List<Category> categories = new CategoryDAO().listCategory();
		assertNotNull(categories, "The categories list do not returned.");
	}

	@Test
	final void testUpdateInformation() {
		new CategoryDAO().addCategoryDetails(testedCategory);
		new CategoryDAO().updateInformation(id, TESTED_CATEGORY_DELETE);
		assertEquals(TESTED_CATEGORY_DELETE, new CategoryDAO().getCategory(id).getName(),"The category's name do not updated.");
	}

	@Test
	final void testGetCategoryInt() {
		new CategoryDAO().addCategoryDetails(testedCategory);
		assertNotNull(new CategoryDAO().getCategory(id), "Do not return class Category.");
		assertEquals(TESTED_CATEGORY_NAME,new CategoryDAO().getCategory(id).getName(), "Return wrong category via name");
	}

	@Test
	final void testGetCategoryString() {
		new CategoryDAO().addCategoryDetails(testedCategory);
		assertNotNull(new CategoryDAO().getCategory(TESTED_CATEGORY_NAME), "Do not return class Category.");
		assertEquals(TESTED_CATEGORY_NAME,new CategoryDAO().getCategory(TESTED_CATEGORY_NAME).getName(), "Return wrong category via name");
	}

	@Test
	final void testIsExisted() {
		new CategoryDAO().addCategoryDetails(testedCategory);
		assertTrue(new CategoryDAO().isExisted(TESTED_CATEGORY_NAME), "Category existed  but return false");
	}

	@Test
	final void testDeleteCategory() {
		new CategoryDAO().addCategoryDetails(testedCategoryDelete);
		new CategoryDAO().deleteCategory(id_delete);
		assertNull(new CategoryDAO().getCategory(id_delete), "The class category do not deleted.");		
	}

}

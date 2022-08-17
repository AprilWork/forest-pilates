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

import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.entity.Group;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GroupsDAOTest {
static final String NAME = "Tested name";
static final String DESCRIPTION = "Wonderfull yoga class for beginners. All age invited!";
static final String IMAGE = "image.jpg";
static final String TYPE = "testedSuper";
static int id, id_category; 
private static Category category = null; 
private Group testedGroup = null;  

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		try {
			category = new Category(TYPE);
			id_category =  new CategoryDAO().addCategoryDetails(category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			new CategoryDAO().deleteCategory(id_category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(1)
	final void testAddGroupDetails() {
		assertNotNull(new CategoryDAO().getCategory(id_category), "The category not existed.");
		testedGroup = new Group(NAME, DESCRIPTION, IMAGE,category);
		id = new GroupsDAO().addGroupDetails(testedGroup);
		assertNotNull(new GroupsDAO().getGroup(id), "The group not added.");
	}
	
	@Test
	@Order(2)
	final void testGetGroupViaName() {
		assertNotNull(new GroupsDAO().getGroup(NAME), "Do not return class Group.");
		assertEquals(NAME, new GroupsDAO().getGroup(NAME).getName(),"Return wrong Group");		
	}	
	
	@Test
	@Order(3)
	final void testListGroups() {
		List<Group> groups = new GroupsDAO().listGroups();
		assertNotNull(groups, "The groups list do not returned.");		
	}

	@Test
	@Order(4)
	final void testIsExisted() {
		assertTrue(new GroupsDAO().isExisted(NAME),"A user existed but returned not existed result.");
	}
	
	@Test
		@Order(5)
	final void testGetGroup() {
		assertNotNull(new GroupsDAO().getGroup(id), "Do not return class Group.");
		assertEquals(id, new GroupsDAO().getGroup(id).getId(),"Return wrong Group id");
	}	
	
	@Test
	@Order(6)
	final void testUpdateInformation() {
		new CategoryDAO().updateInformation(id_category, TYPE+1);
		category = new CategoryDAO().getCategory(id_category);
		new GroupsDAO().updateInformation(id, NAME, DESCRIPTION, IMAGE, category);
		assertEquals(TYPE+1, new GroupsDAO().getGroup(id).getCategory().getName(),"The group's category do not updated.");
		
		new GroupsDAO().updateInformation(id, NAME, DESCRIPTION+1, IMAGE, category);
		assertEquals(DESCRIPTION+1, new GroupsDAO().getGroup(id).getDescription(),"The group's description do not updated.");
		
		new GroupsDAO().updateInformation(id, NAME+1, DESCRIPTION, IMAGE, category);
		assertEquals(NAME+1, new GroupsDAO().getGroup(id).getName(),"The group's name do not updated.");
		
		new GroupsDAO().updateInformation(id, NAME, DESCRIPTION, "updated"+IMAGE, category);
		assertEquals("updated"+IMAGE, new GroupsDAO().getGroup(id).getNameImageFile(),"The group's image do not updated.");
		
		new GroupsDAO().updateInformation(id, NAME, DESCRIPTION, null,category);
		assertEquals(null, new GroupsDAO().getGroup(id).getNameImageFile(),"The group's image do not updated.");	
	}

	@Test
	@Order(7)
	final void testDeleteGroup() {
		new GroupsDAO().deleteGroup(id);
		assertNull(new GroupsDAO().getGroup(id), "The group do not deleted.");			
	}

}

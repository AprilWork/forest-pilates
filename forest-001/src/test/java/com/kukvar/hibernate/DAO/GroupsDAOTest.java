package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.NullAndEmptySource;
//import org.junit.jupiter.params.provider.ValueSource;

import com.kukvar.hibernate.entity.Group;

class GroupsDAOTest {
static final String NAME_1 = "Basic Yoga";
static final String NAME_2 = "Basic Yoga with image";
static final String DESCRIPTION = "Wonderfull yoga class for beginners. All age invited!";
static final String IMAGE = "image.jpg";
int idGroup1, id_delete; 
Group testedGroup1, testedGroupDelete;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		idGroup1 = 20;
		id_delete = 100;		
		testedGroup1 = new Group(idGroup1,NAME_1, DESCRIPTION);
		testedGroupDelete = new Group(id_delete,NAME_1, DESCRIPTION, null);
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			new GroupsDAO().deleteGroup(idGroup1);
			new GroupsDAO().deleteGroup(id_delete);
		} catch (Exception e) {
		}
	}


	@Test
	final void testGetGroupViaName() {
		new GroupsDAO().addGroupDetails(testedGroup1);
		assertNotNull(new GroupsDAO().getGroup(NAME_1), "Do not return class Group.");
		assertEquals(NAME_1, new GroupsDAO().getGroup(NAME_1).getName(),"Return wrong Group");		
	}	
	
	@Test
	final void testAddGroupDetails() {
		new GroupsDAO().addGroupDetails(testedGroup1);
		assertNotNull(new GroupsDAO().getGroup(idGroup1), "The category not added.");
	}

	@Test
	final void testListGroups() {
		List<Group> groups = new GroupsDAO().listGroups();
		assertNotNull(groups, "The groups list do not returned.");		
	}


	@Test
	final void testUpdateInformation() {
		new GroupsDAO().addGroupDetails(testedGroup1);
		
		new GroupsDAO().updateInformation(idGroup1, NAME_1, "updated"+DESCRIPTION, IMAGE);
		assertEquals("updated"+DESCRIPTION, new GroupsDAO().getGroup(idGroup1).getDescription(),"The group's description do not updated.");
		
		new GroupsDAO().updateInformation(idGroup1, NAME_2, DESCRIPTION, IMAGE);
		assertEquals(NAME_2, new GroupsDAO().getGroup(idGroup1).getName(),"The group's name do not updated.");
		
		new GroupsDAO().updateInformation(idGroup1, NAME_1, DESCRIPTION, "updated"+IMAGE);
		assertEquals("updated"+IMAGE, new GroupsDAO().getGroup(idGroup1).getNameImageFile(),"The group's image do not updated.");
		
		new GroupsDAO().updateInformation(idGroup1, NAME_1, DESCRIPTION, null);
		assertEquals(null, new GroupsDAO().getGroup(idGroup1).getNameImageFile(),"The group's image do not updated.");	
	}

	@Test
	final void testGetGroup() {
		new GroupsDAO().addGroupDetails(testedGroup1);
		assertNotNull(new GroupsDAO().getGroup(idGroup1), "Do not return class Group.");
		assertEquals(idGroup1, new GroupsDAO().getGroup(idGroup1).getId(),"Return wrong Group id");
	}

	@Test
	final void testIsExisted() {
		new GroupsDAO().addGroupDetails(testedGroup1);
		assertTrue(new GroupsDAO().isExisted(NAME_1),"A user existed but returned not existed result.");
	}

	@Test
	final void testDeleteGroup() {
		new GroupsDAO().addGroupDetails(testedGroupDelete);
		new GroupsDAO().deleteGroup(id_delete);
		testedGroupDelete = new GroupsDAO().getGroup(id_delete);
		assertTrue(testedGroupDelete == null, "The group do not deleted.");			
	}

}

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
static final String NAME_3 = "Basic Yoga with empty image";
static final String NAME_4 = "Basic Yoga for delete test";
static final String DESCRIPTION = "Wonderfull yoga class for beginners. All age invited!";
static final String IMAGE = "image.jpg";
int idGroup1, idGroup2, idGroup3, id_delete; 
Group testedGroup1, testedGroup2, testedGroup3, testedGroupDelete;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testedGroup1 = new Group(NAME_1, DESCRIPTION);
		testedGroup2 = new Group(NAME_2, DESCRIPTION, "yoga.jpg");
		testedGroup3 = new Group(NAME_3, DESCRIPTION, "");
		testedGroupDelete = new Group(NAME_4, DESCRIPTION, null);
		
		try {
			new GroupsDAO().addGroupDetails(testedGroup1);
			new GroupsDAO().addGroupDetails(testedGroup2);
			new GroupsDAO().addGroupDetails(testedGroup3);
			new GroupsDAO().addGroupDetails(testedGroupDelete);
		} catch (Exception e) {
			System.out.println("BeforeEach: "+e);
		} 
		try {
			idGroup1 = new GroupsDAO().getGroup(NAME_1).getId();
			idGroup2 = new GroupsDAO().getGroup(NAME_2).getId();
			idGroup3 = new GroupsDAO().getGroup(NAME_3).getId();
			id_delete = new GroupsDAO().getGroup(NAME_4).getId();			
		} catch (Exception e) {
			System.out.println("BeforeEach id: "+e);
		}
		

	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			new GroupsDAO().deleteGroup(idGroup1);
			new GroupsDAO().deleteGroup(idGroup2);
			new GroupsDAO().deleteGroup(idGroup3);
			new GroupsDAO().deleteGroup(id_delete);
		} catch (Exception e) {
			System.out.println("AfterEach: "+e);
		}
	}


	@Test
	final void testGetGroupViaName() {
		String name = NAME_1;
		assertNotNull(new GroupsDAO().getGroup(name), "Do not return class Group.");
		assertEquals(name, new GroupsDAO().getGroup(name).getName(),"Return wrong Group");		
	}	
	
	// TODO: parametrized test?
	//@ParameterizedTest
	//@NullAndEmptySource
	//@ValueSource(strings = { " ", "   ", NAME_1, "tested name" })
	//final void testAddGroupDetails(String name) {
	final void testAddGroupDetails() {
		String name = "tested name";
		String newDescription ="tested decription";
		String newImage = "tested_image.jpg";
		Group newGroup = new Group(name, newDescription, newImage);
		new GroupsDAO().addGroupDetails(newGroup);
		int id = new GroupsDAO().getGroup(name).getId();
		new GroupsDAO().deleteGroup(id);
	}

	@Test
	final void testListGroups() {
		List<Group> groups = new GroupsDAO().listGroups();
		assertNotNull(groups, "The groups list do not returned.");		
	}


	@Test
	final void testUpdateInformation() {
		// TODO: parametrized test?
		String newDescription = DESCRIPTION+" updated";
		String newName = NAME_1+" updated";
		String newImage = "updated.jpg";
		new GroupsDAO().updateInformation(idGroup1, NAME_1, newDescription, IMAGE);
		assertEquals(newDescription, new GroupsDAO().getGroup(idGroup1).getDescription(),"The group's description do not updated.");
		new GroupsDAO().updateInformation(idGroup1, newName, newDescription, IMAGE);
		assertEquals(newName, new GroupsDAO().getGroup(idGroup1).getName(),"The group's name do not updated.");
		new GroupsDAO().updateInformation(idGroup1, newName, newDescription, newImage);
		assertEquals(newImage, new GroupsDAO().getGroup(idGroup1).getNameImageFile(),"The group's image do not updated.");	
	}

	@Test
	final void testGetGroup() {
		assertNotNull(new GroupsDAO().getGroup(idGroup1), "Do not return class Group.");
		assertEquals(idGroup1, new GroupsDAO().getGroup(idGroup1).getId(),"Return wrong Group id");
	}

	@Test
	final void testIsExisted() {
		assertTrue(new GroupsDAO().isExisted(NAME_1),"A user existed but returned not existed result.");
	}

	@Test
	final void testDeleteGroup() {
		new GroupsDAO().deleteGroup(id_delete);
		testedGroupDelete = new GroupsDAO().getGroup(id_delete);
		assertTrue(testedGroupDelete == null, "The group do not deleted.");			
	}

}

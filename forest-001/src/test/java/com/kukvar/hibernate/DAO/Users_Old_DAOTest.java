package com.kukvar.hibernate.DAO;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.kukvar.hibernate.entity.Address;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.entity.UserInfo;

@Disabled("Disabled until bug #99 has been fixed")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Users_Old_DAOTest {
	private static final String EMAIL = "tester@yahoo.com";
	private static final String FIRST_NAME = "Peter";
	private static final String LAST_NAME = "Tester";
	private static final LocalDate DATE_BIRTH = LocalDate.of(1975, 07, 15);
	private static final String PHONE = "1111111111";
	private static final String STREET = "12 Tested st.";
	private static final String CITY = "TestCity";
	private static final String ZIPCODE = "34567";
	private static Address testedHomeAddress;
	private static Address testedBillingAddress;
	private static UserInfo testedUserInfo;
	static int id, id2;	

	
	
	
/*	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		User user = new User(EMAIL, "hello", PHONE);
		testedHomeAddress = new Address(STREET, CITY, ZIPCODE);
		testedBillingAddress = new Address(STREET, CITY, ZIPCODE);
		testedUserInfo = new UserInfo(FIRST_NAME,LAST_NAME,DATE_BIRTH,testedHomeAddress,testedBillingAddress, user);
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
	final void testAdduserInfoDetails() {
		try {
			id = new UserInfoDAO().adduserInfoDetails(testedUserInfo);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		assertNotNull(id, "The userInfo do not added");
		//assertNotNull(new UserInfoDAO().getUser(id), "The user do not added.");		
	}

	@Test
	@Order(2)
	final void testGetUserInfo() {
		assertNotNull(new UserInfoDAO().getUserInfo(id), "UserInfo do not returned.");
		assertEquals(id, new UserInfoDAO().getUserInfo(id).getId(),"Returned wrong userInfo id");
	}		
	
	@Test
	@Order(3)
	final void testListUserInfo() {
		List<UserInfo> users = new UserInfoDAO().listUserInfo();
		assertNotNull(users, "The users list do not returned.");
	}	
	
	@Test
	@Order(4)
	final void testGetUserInfoViaEmail() {
		//assertNotNull(new UserInfoDAO().getUserInfo(EMAIL), "The userInfo do not returned.");
		//assertEquals(EMAIL, new UserInfoDAO().getUserInfo(EMAIL).getEmail(),"Return wrong userInfo");		
	}	
	
	@Test
	@Order(5)
	final void testIsExisted() {
		//assertTrue(new UserInfoDAO().isExisted(EMAIL),"A userInfo existed but returned not existed result.");		
	}
	
	@Test
	@Order(6)
	final void testUpdateInformation() {
		try {
			new UserInfoDAO().updateInformation(id, FIRST_NAME, LAST_NAME+" Jr", DATE_BIRTH,
					testedHomeAddress, testedBillingAddress);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
		assertEquals(LAST_NAME+" Jr", new UserInfoDAO().getUserInfo(id).getLast_name(),"The last user's name do not updated.");
	}	
	
	@Test
	@Order(7)
	final void testDeleteUser() {
		try {
			new UserInfoDAO().deleteUser(id);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
		assertNull(new UserInfoDAO().getUserInfo(id), "The userInfo do not deleted.");			
	}
*/
}

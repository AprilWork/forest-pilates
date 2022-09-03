package com.kukvar.hibernate.DAO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.Address;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.entity.UserInfo;
import com.kukvar.hibernate.utils.HibernateUtil;
import com.kukvar.model.SignedUser;

public class UsersDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();

	public int registerUser(String first_name, String last_name
			, LocalDate dateBirth
			, String email, String password, String phone
			, Address homeAddress, Address billingAddress) {	
		
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		int id = 0;
		try {
			txn.begin();
			User user = new User(email, password, phone);
			UserInfo userInfo = new UserInfo(first_name, last_name, dateBirth, email, phone, homeAddress, billingAddress, user);
			id = (int) session.save(userInfo);
			//session.persist(userInfo);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }
		}
		return id;
	}		
	
	
	public int addUser(UserInfo userInfo) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		int id = -1;
		try {
			txn.begin();
			id = (int) session.save(userInfo);
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return id;
	}


	public List<UserInfo> listUsers() {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		List<UserInfo> users = null;
		try {
			session.beginTransaction();
			users = session.createQuery("from user_info").getResultList();
			session.getTransaction().commit();			
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return users;
	}	

	public void updateUserInformation(int id, String first_name, String last_name
			, LocalDate dateBirth
			, String email, String phone
			, Address homeAddress, Address billingAddress) {		
		
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			UserInfo userInfo = session.get(UserInfo.class, id);
			userInfo.setFirst_name(first_name);
			userInfo.setLast_name(last_name);
			userInfo.setDateBirth(dateBirth);
			userInfo.setEmail(email);
			userInfo.setPhone(phone);
			userInfo.setHomeAddress(homeAddress);
			userInfo.setBillingAddress(billingAddress);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }
		}
	}	

	public UserInfo getUserInfo(String email) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		UserInfo userInfo = null;
		try {
			txn.begin();
			String queryString = "from users where email = '"+email+"'";
			userInfo = (UserInfo) session.createQuery(queryString).getSingleResult();
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return userInfo;		
	}	

	public UserInfo getUserInfo(int id) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		UserInfo userInfo = null;
		try {
			txn.begin();
			userInfo = session.get(UserInfo.class, id);
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return userInfo;		
	}	
	
	public SignedUser getSignedUserFromUserInfo(int id) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		UserInfo userInfo = null;
		SignedUser signedUser = null;
		try {
			txn.begin();
			userInfo = session.get(UserInfo.class, id);
			String email = userInfo.getUser().getEmail();
			String firstName = userInfo.getFirst_name();
			String lastName = userInfo.getLast_name();
			signedUser = new SignedUser(id, firstName, lastName, email, "", true) ;
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return signedUser;		
	}		

	public User getUser(int id) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		User user = null;
		try {
			txn.begin();
			user = session.get(UserInfo.class, id).getUser();
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return user;	
	}
	
	
	public int validatePassword(String email, String password) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		//boolean valid = false;
		int id = 0;
		try {
			txn.begin();
			String queryString = "from users where email = '"+email+"'";
			User user = ((User) session.createQuery(queryString).getSingleResult());
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				//valid = true;
				id = user.getId();
			}
			session.detach(user);
			user.setPassword("*************************");
			txn.commit(); 
		} catch (Exception e) {
			if(txn != null) { txn.rollback(); }
			e.printStackTrace();
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		return id;
	}		
	
	public void updateUser(int id, String email, String password, String phone) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		User user = null;
		try {
			txn.begin();
			user = session.get(User.class, id);
			user.setEmail(email);
			user.setPhone(phone);
			user.setPassword(password);
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}	
	}	

	public boolean isExisted(String email) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		int size = 0;
		try {
			txn.begin();
			String queryString = "from users where email = '"+email+"'";
			size =  session.createQuery(queryString).getResultList().size();
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}	

	public void deleteUser(UserInfo userInfo){
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			if (userInfo != null) {
				session.delete(userInfo);	
				}	
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
	}

	public void deleteUser(int id){
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);	
				}	
			txn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }	
		}
	}


	public void updateUserHomeAddress(int id, Address homeAddress, Address billingAddress) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			UserInfo userInfo = session.get(UserInfo.class, id);
			userInfo.setHomeAddress(homeAddress);
			userInfo.setBillingAddress(billingAddress);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }
		}		
	}

}

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

public class UsersDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	static Session session;
	Transaction txn;
	
	
	public void registerUser(String first_name, String last_name, LocalDate dateBirth,
			Address homeAddress, Address billingAddress, String email, String password, String phone) {
		session = factory.openSession();
		txn = session.getTransaction();
		try {
			txn.begin();
			User user = new User(email, password, phone);
			UserInfo userInfo = new UserInfo(first_name, last_name, dateBirth,
			homeAddress, billingAddress, user);
			session.save(userInfo);
			//session.persist(userInfo);
			txn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(txn != null) { txn.rollback(); }
		} finally {
			if(session != null) { 
				session.close();  }
		}
	}		
	
	
	public int addUser(UserInfo userInfo) {
		session = factory.openSession();
		txn = session.getTransaction();
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
		session = factory.openSession();
		txn = session.getTransaction();
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

	public void updateUserInformation(int id, String first_name, String last_name, LocalDate dateBirth,
			Address homeAddress, Address billingAddress) {
		session = factory.openSession();
		txn = session.getTransaction();
		try {
			txn.begin();
			UserInfo userInfo = session.get(UserInfo.class, id);
			userInfo.setFirst_name(first_name);
			userInfo.setLast_name(last_name);
			userInfo.setDateBirth(dateBirth);
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
		session = factory.openSession();
		txn = session.getTransaction();
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
		session = factory.openSession();
		txn = session.getTransaction();
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

	public User getUser(int id) {
		session = factory.openSession();
		txn = session.getTransaction();
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
	
	public void updateUser(int id, String email, String password, String phone) {
		session = factory.openSession();
		txn = session.getTransaction();
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
		session = factory.openSession();
		txn = session.getTransaction();
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
		session = factory.openSession();
		txn = session.getTransaction();
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
		session = factory.openSession();
		txn = session.getTransaction();
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

}

package com.kukvar.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.utils.HibernateUtil;

public class UsersDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public int addUserDetails(User user) {
		Session session = factory.openSession();
		session.beginTransaction();
		int id = (int) session.save(user);
		session.getTransaction().commit();
		session.close();
		return id;
	}	
	
	public List<User> listUsers() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = session.createQuery("from users").getResultList();
		session.getTransaction().commit();
		session.close();
		return users;
	}	
	
	public void updateInformation(int id, String email, String username) {
		Session session = factory.openSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		user.setEmail(email);
		user.setUsername(username);
		session.getTransaction().commit();
		session.close();
	}	
	
	public User getUser(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		session.getTransaction().commit();
		session.close();
		return user;
	}	
	
	public User getUser(String email) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from users where email = '"+email+"'";
		User user;
		try {
			user = (User) session.createQuery(queryString).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}
	
	public boolean isExisted(String email) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from users where email = '"+email+"'";
		int size =  session.createQuery(queryString).getResultList().size();
		session.close();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}	
	
	public void deleteUser(int id) {
		Session session = factory.openSession();
		session.beginTransaction();		
		User user = session.get(User.class, id);
		if (user != null) {
			session.delete(user);
		}
		session.getTransaction().commit();
		session.close();
	}
	
}

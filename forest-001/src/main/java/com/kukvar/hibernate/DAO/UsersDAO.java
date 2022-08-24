package com.kukvar.hibernate.DAO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.utils.HibernateUtil;

public class UsersDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();

	public int addUserDetails(User user) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		int id = -1;
		try {
			txn.begin();
			id = (int) session.save(user);
			txn.commit(); 
		} catch (Exception e) {
			if(txn != null) { txn.rollback(); }

			if(e.getCause() != null 
					&& e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause();
				throw sql_violation_exception;
			} else {
				throw e;
				//e.printStackTrace();
			}
		} finally {
			if(session != null) { 
				session.close();  }			
		}
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

	public void updateInformation(int id, String email) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			User user = session.get(User.class, id);
			user.setEmail(email);
			txn.commit();
		} catch (Exception e) {
			if(txn != null) { txn.rollback(); }
			if(e.getCause() != null 
					&& e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause();
				throw sql_violation_exception;
			} else {
				//e.printStackTrace();
				throw e;
			}
		} finally {
			if(session != null) { 
				session.close();  }
		}

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

	public void deleteUser(int id) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
			}			
			txn.commit();
		} catch (Exception e) {
			if(txn != null) { txn.rollback(); }
			if(e.getCause() != null 
					&& e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause();
				throw sql_violation_exception;
			} else {
				//e.printStackTrace();
				throw e;
			}
		} finally {
			if(session != null) { 
				session.close();  }
		}			

	}

}

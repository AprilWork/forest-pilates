package com.kukvar.hibernate.DAO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.Address;
import com.kukvar.hibernate.entity.UserInfo;
import com.kukvar.hibernate.utils.HibernateUtil;

public class UserInfoDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();

	public int adduserInfoDetails(UserInfo userInfo) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		int id = -1;
		try {
			txn.begin();
			id = (int) session.save(userInfo);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}

			if (e.getCause() != null && e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e
						.getCause();
				throw sql_violation_exception;
			} else {
				e.printStackTrace();
				throw e;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	public void deleteUser(int id) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			UserInfo userInfo = session.get(UserInfo.class, id);
			if (userInfo != null) {
				session.delete(userInfo);
			}
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e
						.getCause().getCause();
				throw sql_violation_exception;
			} else {
				e.printStackTrace();
				throw e;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public UserInfo getUserInfo(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		UserInfo userInfo = session.get(UserInfo.class, id);
		session.getTransaction().commit();
		session.close();
		return userInfo;
	}

	public List<UserInfo> listUserInfo() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UserInfo> users = session.createQuery("from user_info").getResultList();
		session.getTransaction().commit();
		session.close();
		return users;
	}

	public UserInfo getUserInfo(String email) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from user_info where email = '" + email + "'";
		UserInfo userInfo;
		try {
			userInfo = (UserInfo) session.createQuery(queryString).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		session.getTransaction().commit();
		session.close();
		return userInfo;
	}

	public boolean isExisted(String email) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from user_info where email = '" + email + "'";
		int size = session.createQuery(queryString).getResultList().size();
		session.close();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void updateInformation(int id, String email, String first_name, String last_name, LocalDate dateBirth,
			String phone, Address homeAddress, Address billingAddress) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			UserInfo userInfo = session.get(UserInfo.class, id);
			userInfo.setEmail(email);
			userInfo.setFirst_name(first_name);
			userInfo.setLast_name(last_name);
			userInfo.setDateBirth(dateBirth);
			userInfo.setPhone(phone);
			userInfo.setHomeAddress(homeAddress);
			userInfo.setBillingAddress(billingAddress);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e
						.getCause().getCause();
				throw sql_violation_exception;
			} else {
				e.printStackTrace();
				throw e;
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}

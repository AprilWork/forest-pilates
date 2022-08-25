package com.kukvar.hibernate.DAO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.utils.HibernateUtil;

public class CategoryDAO {
	private SessionFactory factory = HibernateUtil.getSessionFactory();

	public int addCategoryDetails(Category category) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		int id = -1;
		try {
			txn.begin();
			id = (int) session.save(category);
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
				throw e;
				// e.printStackTrace();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	public List<Category> listCategory() {
		Session session = factory.openSession();
		List<Category> category = null;
		try {
			session.beginTransaction();
			category = session.createQuery("from class_type").getResultList();
			session.getTransaction().commit();			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return category;	
	}

	public void updateInformation(int id, String name) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			Category Category = session.get(Category.class, id);
			Category.setName(name);
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

	public Category getCategory(int id) {
		Session session = factory.openSession();
		Category category = null;
		try {
			session.beginTransaction();
			category = session.get(Category.class, id);
			session.getTransaction().commit();			
		} finally {
			if (session != null) {
				session.close();
			}	
		}
		return category;	
	}

	public Category getCategory(String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from class_type where name = '" + name + "'";
		Category category;
		try {
			category = (Category) session.createQuery(queryString).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		session.getTransaction().commit();
		session.close();
		return category;
	}

	public boolean isExisted(String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from class_type where name = '" + name + "'";
		int size = session.createQuery(queryString).getResultList().size();
		session.getTransaction().commit();
		session.close();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void deleteCategory(int id) throws SQLIntegrityConstraintViolationException {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			Category category = session.get(Category.class, id);
			if (category != null) {
				session.delete(category);
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

}

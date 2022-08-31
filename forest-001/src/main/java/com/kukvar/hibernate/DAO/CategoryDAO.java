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
		Session session = factory.getCurrentSession();
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Category> listCategory() {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		List<Category> category = null;
		try {
			txn.begin();
			category = session.createQuery("from class_type").getResultList();
			txn.commit();			
		}catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
		} finally {
			if (session != null) {
				session.close(); 
			}
		}
		return category;	
	}

	public void updateInformation(int id, String name) throws SQLIntegrityConstraintViolationException {
		Session session = factory.getCurrentSession();
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Category getCategory(int id) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		Category category = null;
		try {
			txn.begin();
			category = session.get(Category.class, id);
			txn.commit();	
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}			
		} finally {
			if (session != null) {
				session.close();
			}	
		}
		return category;	
	}

	public Category getCategory(String name) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		String queryString = "from class_type where name = '" + name + "'";
		Category category = null;
		try {
			txn.begin();
			category = (Category) session.createQuery(queryString).getSingleResult();
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}	
		} finally {
			if (session != null) {
				session.close();
			}	
		}
		return category;
	}

	public boolean isExisted(String name) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		String queryString = "from class_type where name = '" + name + "'";
		int size = 0;
		try {
			txn.begin();
			size = session.createQuery(queryString).getResultList().size();
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}	
		} finally {
			if (session != null) {
				session.close();
			}				
		}
		return size != 0;
	}

	public void deleteCategory(int id) throws SQLIntegrityConstraintViolationException {
		Session session = factory.getCurrentSession();
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}

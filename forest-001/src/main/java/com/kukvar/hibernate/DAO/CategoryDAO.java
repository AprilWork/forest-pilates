package com.kukvar.hibernate.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.utils.HibernateUtil;

public class CategoryDAO {
	private SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public int addCategoryDetails(Category category) {
		Session session = factory.openSession();
		session.beginTransaction();
		int id = (int) session.save(category);
		session.getTransaction().commit();
		session.close();
		return id;
	}	
	
	public List<Category> listCategory() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Category> category = session.createQuery("from class_type").getResultList();
		session.getTransaction().commit();
		session.close();
		return category;
	}	
	
	public void updateInformation(int id, String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		Category Category = session.get(Category.class, id);
		Category.setName(name);
		session.getTransaction().commit();
		session.close();
	}	
	
	public Category getCategory(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Category category = session.get(Category.class, id);
		session.getTransaction().commit();
		session.close();
		return category;
	}	
	
	public Category getCategory(String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from class_type where name = '"+name+"'";
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
		String queryString = "from class_type where name = '"+name+"'";
		int size =  session.createQuery(queryString).getResultList().size();
		session.getTransaction().commit();
		session.close();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}	
	
	public void deleteCategory(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Category category = session.get(Category.class, id);
		if (category != null) {
			session.delete(category);
		}
		session.getTransaction().commit();
		session.close();
	}
	
}

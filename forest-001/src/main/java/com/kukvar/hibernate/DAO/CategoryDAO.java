package com.kukvar.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kukvar.hibernate.entity.Category;

public class CategoryDAO {
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Category.class)
			.buildSessionFactory();
	
	public void addCategoryDetails(Category category) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(category);
		session.getTransaction().commit();
	}	
	
	public List<Category> listCategory() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Category> category = session.createQuery("from class_type").getResultList();
		//session.getTransaction().commit();
		return category;
	}	
	
	public void updateInformation(int id, String name) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Category Category = session.get(Category.class, id);
		Category.setName(name);
		session.getTransaction().commit();
	}	
	
	public Category getCategory(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Category category = session.get(Category.class, id);
		session.getTransaction().commit();		
		return category;
	}	
	
	public Category getCategory(String name) {
		Session session = factory.getCurrentSession();
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
		return category;
	}
	
	public boolean isExisted(String name) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String queryString = "from class_type where name = '"+name+"'";
		int size =  session.createQuery(queryString).getResultList().size();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}	
	
	public void deleteCategory(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();		
		Category category = session.get(Category.class, id);
		if (category != null) {
			session.delete(category);
			session.getTransaction().commit();
		}
	}
	
}

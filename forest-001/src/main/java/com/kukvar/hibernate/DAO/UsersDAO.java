package com.kukvar.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kukvar.hibernate.entity.Customers;

public class UsersDAO {
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Customers.class)
			.buildSessionFactory();
	
	public void addCustomersDetails(Customers customer) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(customer);
		session.getTransaction().commit();
	}	
	
	public List<Customers> listCustomers() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Customers> customers = session.createQuery("from users").getResultList();
		//session.getTransaction().commit();
		return customers;
	}	
	
	public void updateInformation(int id, String email, String username) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Customers customer = session.get(Customers.class, id);
		customer.setEmail(email);
		customer.setUsername(username);
		session.getTransaction().commit();
	}	
	
	public Customers getCustomer(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Customers customer = session.get(Customers.class, id);
		session.getTransaction().commit();		
		return customer;
	}	
	
	public Customers getCustomer(String email) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String queryString = "from users where email = '"+email+"'";
		Customers customer;
		try {
			customer = (Customers) session.createQuery(queryString).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		session.getTransaction().commit();		
		return customer;
	}
	
	public boolean isExisted(String email) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String queryString = "from users where email = '"+email+"'";
		int size =  session.createQuery(queryString).getResultList().size();
		if (size == 0) {
			return false;
		} else {
			return true;
		}
	}	
	
	public void deleteCustomer(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();		
		Customers customer = session.get(Customers.class, id);
		if (customer != null) {
			session.delete(customer);
			session.getTransaction().commit();
		}
	}
	
}

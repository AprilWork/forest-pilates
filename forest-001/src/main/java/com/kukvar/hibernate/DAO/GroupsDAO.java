package com.kukvar.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.kukvar.hibernate.entity.Group;


public class GroupsDAO {
	SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Group.class)
			.buildSessionFactory();
	
	public void addGroupDetails(Group group) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(group);
		session.getTransaction().commit();
	}	
	
	public List<Group> listGroups() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Group> groups = session.createQuery("from classes").getResultList();
		//session.getTransaction().commit();
		return groups;
	}	
	
	public void updateInformation(int id, String name, String description, String nameImageFile) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Group group = session.get(Group.class, id);
		group.setId(id);
		group.setName(name);
		group.setDescription(description);
		group.setNameImageFile(nameImageFile);
		session.getTransaction().commit();
	}	
	
	public Group getGroup(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Group group = session.get(Group.class, id);
		session.getTransaction().commit();		
		return group;
	}	
	
	public boolean isExisted(String name) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String queryString = "from classes where name = '"+name+"'";
		List<Group> groups = session.createQuery(queryString).getResultList();
		if (groups.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}	
	
	public void deleteGroup(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();		
		Group group = session.get(Group.class, id);
		session.delete(group);
		session.getTransaction().commit();
	}
	
}

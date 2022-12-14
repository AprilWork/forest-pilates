package com.kukvar.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.entity.Group;
import com.kukvar.hibernate.utils.HibernateUtil;


public class GroupsDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();

	public int addGroupDetails(Group group) {
		Session session = factory.openSession();
		session.beginTransaction();
		int id = (int) session.save(group);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	public List<Group> listGroups() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Group> groups = session.createQuery("from classes").getResultList();
		session.getTransaction().commit();
		session.close();
		return groups;
	}

	public void updateInformation(int id, String name, String description, String nameImageFile, Category category) {
		Session session = factory.openSession();
		session.beginTransaction();
		Group group = session.get(Group.class, id);
		group.setId(id);
		group.setName(name);
		group.setDescription(description);
		group.setNameImageFile(nameImageFile);
		group.setCategory(category);
		session.getTransaction().commit();
		session.close();
	}

	public Group getGroup(int id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Group group = session.get(Group.class, id);
		session.getTransaction().commit();
		session.close();
		return group;
	}

	public Group getGroup(String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from classes where name = '" + name + "'";
		Group group;
		try {
			group = (Group) session.createQuery(queryString).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception GroupsDAO
			return null;
		}
		session.getTransaction().commit();
		session.close();
		return group;
	}

	public boolean isExisted(String name) {
		Session session = factory.openSession();
		session.beginTransaction();
		String queryString = "from classes where name = '" + name + "'";
		@SuppressWarnings("unchecked")
		List<Group> groups = session.createQuery(queryString).getResultList();
		session.getTransaction().commit();
		session.close();
		if (groups.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void deleteGroup(int id) {
		Session session = factory.openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			Group group = session.get(Group.class, id);
			if (group != null) {
				session.delete(group);
			}
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				e.printStackTrace();
				txn.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}

package com.kukvar.hibernate.DAO;

import java.util.ArrayList;
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
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		int id = 0;
		try {
			txn.begin();
			id = (int) session.save(group);
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
		return id;		
	}

	@SuppressWarnings("unchecked")
	public List<Group> listGroups() {
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		List<Group> groups = new ArrayList<>();
		try {
			txn.begin();
			groups = session.createQuery("from classes").getResultList();
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
		return groups;
	}

	public void updateInformation(int id, String name, String description, String nameImageFile, Category category) {
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			Group group = session.get(Group.class, id);
			group.setId(id);
			group.setName(name);
			group.setDescription(description);
			group.setNameImageFile(nameImageFile);
			group.setCategory(category);			
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

	public Group getGroup(int id) {
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		Group group = null;
		try {
			txn.begin();
			group = session.get(Group.class, id);
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
		return group;			
	}

	public Group getGroup(String name) {		
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		String queryString = "from classes where name = '" + name + "'";
		Group group = null;
		try {
			txn.begin();
			group = (Group) session.createQuery(queryString).getSingleResult();
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
		return group;			
	}

	public boolean isExisted(String name) {			
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		String queryString = "from classes where name = '" + name + "'";
		Group group = null;
		try {
			txn.begin();
			group = (Group) session.createQuery(queryString).getSingleResult();
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
		return group != null;		
	}

	public void deleteGroup(int id) {
		//Session session = factory.openSession();
		Session session = factory.getCurrentSession();
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

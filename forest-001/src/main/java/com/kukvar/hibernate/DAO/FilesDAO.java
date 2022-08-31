package com.kukvar.hibernate.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.kukvar.hibernate.entity.Files;
import com.kukvar.hibernate.utils.HibernateUtil;

public class FilesDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public void addFileDetails(Files file) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			// place your code here
			session.save(file);
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
	
	@SuppressWarnings("unchecked")
	public List<Files> listFiles() {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		List<Files> files = new ArrayList<>();
		try {
			txn.begin();
			// place your code here
			files = session.createQuery("from files").getResultList();
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
		return files;
	}

	public void updateInformation(int id, String label, String caption) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		Files file = null;
		try {
			txn.begin();
			// place your code here
			file = session.get(Files.class, id);
			file.setLabel(label);
			file.setCaption(caption);
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

	public Files getFile(int fileId) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		Files file = null;
		try {
			txn.begin();
			// place your code here
			file = session.get(Files.class, fileId);
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
		return file;
	}

	public void deleteFile(int fileId) {
		Session session = factory.getCurrentSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();
			// place your code here
			Files file = session.get(Files.class, fileId);
			session.delete(file);			
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

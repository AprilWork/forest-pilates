package com.kukvar.hibernate.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.kukvar.hibernate.entity.Files;
import com.kukvar.hibernate.utils.HibernateUtil;

public class FilesDAO {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public void addFileDetails(Files file) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(file);
		session.getTransaction().commit();
		session.close();
		System.out.println(file.getFileName()+" Got added");
	}
	
	public List<Files> listFiles() {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Files> files = session.createQuery("from files").getResultList();
		session.getTransaction().commit();
		session.close();
		return files;
	}

	public void updateInformation(int id, String label, String caption) {
		Session session = factory.openSession();
		session.beginTransaction();
		Files file = session.get(Files.class, id);
		file.setLabel(label);
		file.setCaption(caption);
		session.getTransaction().commit();
		session.close();
	}

	public Files getFile(int fileId) {
		Session session = factory.openSession();
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);
		session.getTransaction().commit();
		session.close();
		return file;
	}

	public void deleteFile(int fileId) {
		Session session = factory.openSession();
		session.beginTransaction();		
		Files file = session.get(Files.class, fileId);
		session.delete(file);
		session.getTransaction().commit();
		session.close();
	}
}

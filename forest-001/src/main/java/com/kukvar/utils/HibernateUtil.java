package com.kukvar.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.entity.Files;
import com.kukvar.hibernate.entity.Group;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {    
        	 // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration()
        			.configure("hibernate.cfg.xml")
        			.addAnnotatedClass(Group.class)
        			.addAnnotatedClass(Category.class)
        			.addAnnotatedClass(User.class)
        			.addAnnotatedClass(Files.class);
          return configuration.buildSessionFactory();  
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
}




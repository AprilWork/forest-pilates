package com.kukvar.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.kukvar.hibernate.entity.Category;
import com.kukvar.hibernate.entity.User;
import com.kukvar.hibernate.entity.Files;
import com.kukvar.hibernate.entity.Group;

public class HibernateUtil {
	
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {    
          // for Hibernate 5.x users
          // Create the SessionFactory from hibernate.cfg.xml
          
          StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
          Metadata metadata = new MetadataSources(serviceRegistry)
          		.addAnnotatedClass(Group.class)
        			.addAnnotatedClass(Category.class)
        			.addAnnotatedClass(User.class)
        			.addAnnotatedClass(Files.class)        		
          		.getMetadataBuilder().build();
          return metadata.getSessionFactoryBuilder().build();
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




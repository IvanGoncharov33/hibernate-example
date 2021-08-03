package org.example.utils;


import org.example.entity.CoreEntity;
import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class JpaUtils {

    private static SessionFactory sessionFactory;
    private static EntityManagerFactory emFactory;

    private JpaUtils(){
    }

    /**
     * Конфигурация через Hibernate
     */
    public static SessionFactory getSessionFactory(AppProperties properties){
        if (sessionFactory == null) {
            Configuration configuration = getHibernateConfiguration(properties);
            StandardServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static Session getSession(AppProperties properties){
      return  getSessionFactory(properties).openSession();
    }

    /**
     * Правильная конфигурация через JPA API
     */
    public static EntityManagerFactory getEntityManagerFactory(String entityName, AppProperties properties){
        if (sessionFactory == null) {
            Configuration configuration = getHibernateConfiguration(properties);
            emFactory = Persistence.createEntityManagerFactory("Person" , configuration.getProperties());
        }
        return sessionFactory;
    }

    private static Configuration getHibernateConfiguration(AppProperties properties){
        Configuration configuration = new Configuration();
        Properties settings = new Properties();

        settings.put(Environment.DRIVER, properties.getDriver());
        settings.put(Environment.URL, properties.getUrl());
        settings.put(Environment.USER, properties.getUser());
        settings.put(Environment.PASS, properties.getPassword());
        settings.put(Environment.DIALECT, properties.getDialect());
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.HBM2DDL_CREATE_SCRIPT_SOURCE, "/schema.sql");
        settings.put(Environment.AUTOCOMMIT, "true");
        settings.put("class", "org.example.entity.Person");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(Person.class);
        return configuration;
    }
}

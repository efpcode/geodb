package se.iths.java24;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    /*
    A SessionFactory is very expensive to create, so, for any given database,
    the application should have only one associated SessionFactory.
    The SessionFactory maintains services that Hibernate uses across all Session(s)
    such as second level caches, connection pools, transaction system integrations, etc.
    SessionFactory is Thread safe while the Session objects isn't and should not be shared.
     */
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static StatelessSession getStatelessSession() {
        return sessionFactory.openStatelessSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

package se.iths.java24;

import org.hibernate.StatelessSession;

public class Main {
    public static void main(String[] args) {


        HibernateUtil.getSessionFactory().inTransaction(
                session -> {
                    session.createSelectionQuery("from Country", Country.class)
                            .list().forEach(System.out::println);
                }
        );
    }
}

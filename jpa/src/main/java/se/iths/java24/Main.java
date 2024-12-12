package se.iths.java24;

import jakarta.persistence.EntityManager;

import static se.iths.java24.JPAUtil.getEntityManager;
import static se.iths.java24.JPAUtil.inTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager em = getEntityManager();

//        System.out.print("Enter search term: ");
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//
//        // Validate user input
//        if (name == null || name.isEmpty()) {
//            System.out.println("Invalid input.");
//            return;
//        }
//
//        //JPQL
//        String queryStr = "SELECT c FROM Country c WHERE c.countryName =:name";
//        TypedQuery<Country> query = em.createQuery(queryStr, Country.class);
//        query.setParameter("name", name);
//        List<Country> countries = query.getResultList();
//        countries.forEach(System.out::println);

        //Create new country
        Country country = new Country();
        country.setCountryName("Poland");
        country.setCountryCode("pl");

//        var transaction = em.getTransaction();
//        transaction.begin();
//        em.persist(country);
//        transaction.commit();
//        em.close();

        //Create
        try {
            inTransaction(entityManager -> {
                entityManager.persist(country);
            });
        } catch (Exception e) {

        }

        //Update
        inTransaction(entityManager -> {
            Country poland = entityManager.find(Country.class, "pl");
            if (poland != null) {
                poland.setCountryName("Poland (PL)");
                poland.setCountryName("Test");
            }
        });

        //Delete
        inTransaction(entityManager -> {
            Country poland = entityManager.find(Country.class, "pl");
            if (poland != null)
                entityManager.remove(poland);
        });

        inTransaction(entityManager -> {
            var country1 = entityManager.find(Country.class, "se");
            System.out.println(country1.getThreeLetterName());
        });

        //Use JOIN FETCH to prevent N + 1 problem
        inTransaction(entityManager -> {
            var c = entityManager.createQuery("SELECT c FROM Country c JOIN FETCH c.cities", Country.class)
                    .getResultList();
            c.forEach(System.out::println);
        });

        //Named entity graph to prevent N + 1 problem, defined in Entity class
        inTransaction(entityManager -> {
            var eg = entityManager.getEntityGraph("Country.cities");

            var c = entityManager.createQuery("SELECT c FROM Country c", Country.class)
                    .setHint("jakarta.persistence.fetchgraph", eg)
                    .getResultList();
            c.forEach(System.out::println);
        });

        //Create entity graph using code.
        inTransaction(entityManager -> {
            var eg = entityManager.createEntityGraph(Country.class);
            eg.addAttributeNodes("cities");

            var c = entityManager.createQuery("SELECT c FROM Country c", Country.class)
                    .setHint("jakarta.persistence.fetchgraph", eg)
                    .getResultList();
            c.forEach(System.out::println);
        });

        //Only retrieve what we need
        inTransaction(entityManager -> {
           var c = entityManager.createQuery("SELECT c.countryName FROM Country c", String.class)
                   .getResultList();
           c.forEach(System.out::println);
        });


    }


}

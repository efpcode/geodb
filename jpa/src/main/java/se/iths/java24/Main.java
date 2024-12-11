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
    }


}

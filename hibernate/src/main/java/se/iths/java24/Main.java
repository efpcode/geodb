package se.iths.java24;

public class Main {
    public static void main(String[] args) {


        HibernateUtil.getSessionFactory().inTransaction(
                session -> {
                    session.createSelectionQuery("from Country", Country.class)
                            .list().forEach(System.out::println);
                });

        // Demonstrate various Hibernate operations
        try {
            // Get session factory
            var sessionFactory = HibernateUtil.getSessionFactory();

            // Demonstrate CRUD operations
            sessionFactory.inTransaction(session -> {
                // 1. Create (Persist)
                Country sweden = session.find(Country.class, "se");
                City uppsala = new City("Uppsala", 233839, sweden);
                City vasteras = new City("Västerås", 154049, sweden);
                session.persist(uppsala);
                session.persist(vasteras);

                // 2. Read (Find)
                System.out.println("Cities in Sweden:");
                session.createSelectionQuery("from City c where c.country.countryCode = 'se'", City.class)
                        .getResultList()
                        .forEach(System.out::println);

                // 3. Update
                // Retrieve the City entity for Copenhagen
                City copenhagen = session.createQuery("from City where cityName = :cityName", City.class)
                        .setParameter("cityName", "Copenhagen")
                        .uniqueResult();

                if (copenhagen != null) {
                    // Update the population
                    copenhagen.setPopulation(620000);
                    session.merge(copenhagen);
                } else {
                    System.out.println("Copenhagen not found in the database.");
                }

                // 4. Delete
                City cityToDelete = session.createSelectionQuery("FROM City c WHERE c.cityName = 'Aarhus'", City.class)
                        .getSingleResult();
                session.remove(cityToDelete);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

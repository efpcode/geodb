package se.iths.java24;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        HibernateUtil.getSessionFactory().inTransaction(
                session -> {
                    session.createSelectionQuery("from Country", Country.class)
                            .list().forEach(System.out::println);
                });

        // Get session factory
        var sessionFactory = HibernateUtil.getSessionFactory();

        // Demonstrate various Hibernate operations
        try {
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

        // Demonstrate Projection to DTO
        sessionFactory.inTransaction(session -> {
            List<CityDTO> cityDTOs = session.createSelectionQuery(
                            "SELECT new se.iths.java24.CityDTO(c.cityName, c.country.countryName, c.population) FROM City c",
                            CityDTO.class)
                    .getResultList();

            System.out.println("City DTOs:");
            cityDTOs.forEach(System.out::println);
        });


    }
}

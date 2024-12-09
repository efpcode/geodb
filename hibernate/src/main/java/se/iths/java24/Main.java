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
                session.createSelectionQuery("from City c where c.country.countryCode = :countryCode", City.class)
                        .setParameter("countryCode", "se")
                        .getResultList()
                        .forEach(System.out::println);

                // 3. Update
                // Retrieve the City entity for Copenhagen
                City copenhagen = session.createQuery("from City where cityName = :cityName", City.class)
                        .setParameter("cityName", "Copenhagen")
                        .uniqueResult();

                if (copenhagen != null) {
                    // Update the population
                    copenhagen.setPopulation(copenhagen.getPopulation() + 1);
                    session.merge(copenhagen);
                } else {
                    System.out.println("Copenhagen not found in the database.");
                }

                // 4. Delete what we created
                City cityToDelete = session.createSelectionQuery("FROM City c WHERE c.cityName = 'Västerås' OR c.cityName = 'Uppsala'", City.class)
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

        // Demonstrate Native SQL Query
        sessionFactory.inTransaction(session -> {
            List<City> nativeResults = session.createNativeQuery(
                            "SELECT c.id, c.city_name, c.population, c.country_code " +
                            "FROM city c " +
                            "WHERE c.country_code = :countryCode", City.class)
                    .setParameter("countryCode", "se")
                    .getResultList();

            System.out.println("Native SQL Query Results:");
            nativeResults.forEach(row ->
                    System.out.println("City: " + row.getCityName() +
                                       ", Population: " + row.getPopulation())
            );
        });

        // Demonstrate Entity Graph, avoid N+1 problem
        sessionFactory.inTransaction(session -> {
            // Create an entity graph
            var entityGraph = session.createEntityGraph(City.class);
            entityGraph.addAttributeNodes("country");

            List<City> citiesWithCountry = session.createSelectionQuery("FROM City c", City.class)
                    .setHint("jakarta.persistence.fetchgraph", entityGraph)
                    .getResultList();

            System.out.println("Cities with Eager Loaded Country:");
            citiesWithCountry.forEach(city ->
                    System.out.println(city.getCityName() + " in " + city.getCountry().getCountryName())
            );
        });

    }
}

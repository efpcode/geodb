package geodb.repository;

import static geodb.JPAUtil.inTransaction;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Continent;
import geodb.entity.Country;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CountryRepository implements Crudable {
    static private Scanner scanner = new Scanner(System.in);

    @Override
    public void insertToTable() {
        Country country = new Country();

        System.out.println("Please enter the new country you want to insert to the country table");
        String countryName = scanner.nextLine();

        if (countryName == null || countryName.isEmpty() || !isAlpha(countryName)) {
            throw new IllegalArgumentException("Value must be a valid country name and not empty");
        }

        System.out.println("Please enter the country code that correspond to the country");
        String countryCode = scanner.nextLine();

        if (countryCode == null || countryCode.isEmpty() || !isAlpha(countryCode)) {
            throw new IllegalArgumentException("Value must be a valid country code and not empty");
        }

        System.out.println("Please enter the country area in decimal");
        double countryArea = Double.parseDouble(scanner.nextLine());

        if (countryArea <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        System.out.println("Please enter the neighboring countries separated by a comma");
        String neighboringCountry = scanner.nextLine();

        if (neighboringCountry == null || neighboringCountry.isEmpty() || !isAlpha(neighboringCountry)) {
            throw new IllegalArgumentException("Value must be a valid country code and not empty");
        }

        System.out.println("Please enter the population size in whole numbers");
        Long population = Long.parseLong(scanner.nextLine());

        if (population <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        System.out.println("Please enter the continent name of the country");
        String continentName = scanner.nextLine();

        if (continentName == null || continentName.isEmpty() || !isAlpha(continentName)) {
            throw new IllegalArgumentException("Value must be a valid continent name and not empty");
        }

        var hasContinent = findContinent(continentName);
        if (hasContinent.isEmpty()) {
            throw new RuntimeException("Continent not found with name: " + continentName + ", please add the continent to the continent table or spell it correctly");
        }

        inputInsertToTable(country, countryName, countryCode, countryArea, neighboringCountry, population, hasContinent.get());
        Country countryNewlyAdded = getCountry(countryName).orElseThrow(() -> new RuntimeException("Country not found with name: " + countryName));

        int countryPK = countryNewlyAdded.getId();
        createCountryRelationShips(countryPK);


    }

    private void createCountryRelationShips(int countryPK) {
        List<Crudable> tables = List.of(
                new CityRepository(),
                new CurrencyRepository(),
                new LandMarkRepository(),
                new OceanRepository()
        );

        for (Crudable table : tables) {
            System.out.println("Friendly reminder that all connection are to be done with: " + countryPK);
            table.insertToTable();
        }

        displayTable();
        continuePrompt();


    }

    private void inputInsertToTable(Country country, String countryName, String countryCode, double countryArea, String neighboringCountry, Long population, Continent continent) {
        inTransaction(entityManager -> {
            country.setCountryName(countryName);
            country.setCountryCode(countryCode);
            country.setCountryArea(countryArea);
            country.setCountryNeighbor(neighboringCountry);
            country.setCountryPopulationSize(population);
            country.setCountryContinent(continent);
            entityManager.persist(country);
        });

    }

    private Optional<Country> getCountry(String countryName) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return Optional.of(entityManager.createQuery("SELECT c FROM Country c WHERE c.countryName = : countryName", Country.class)
                    .setParameter("countryName", countryName)
                    .getSingleResult()
            );

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<Continent> findContinent(String continentName) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return Optional.of(em.createQuery("SELECT c FROM Continent c WHERE c.continentName = :continentName", Continent.class)
                    .setParameter("continentName", continentName)
                    .getSingleResult()
            );

        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public void updateTable() {
        displayTable();
        System.out.println("Please select the Country ID to update corresponding row");
        int countryID = Integer.parseInt(scanner.nextLine());
        if (countryID <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        System.out.println("Please enter new country code");
        String countryCode = scanner.nextLine();
        if (countryCode == null || countryCode.isEmpty() || !isAlpha(countryCode)) {
            throw new IllegalArgumentException("Value must be a valid country code and not empty");
        }

        System.out.println("Please enter new country name");
        String countryName = scanner.nextLine();

        if (countryName == null || countryName.isEmpty() || !isAlpha(countryName)) {
            throw new IllegalArgumentException("Value must be a valid country name and not empty");
        }

        System.out.println("Please enter new country area");
        double countryArea = Double.parseDouble(scanner.nextLine());

        if (countryArea <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        System.out.println("Please update neighboring countries separated with comma for multiple entries");
        String neighboringCountry = scanner.nextLine();
        if (neighboringCountry == null || neighboringCountry.isEmpty() || !isAlpha(neighboringCountry)) {
            throw new IllegalArgumentException("Value must be a valid country code and not empty");
        }

        System.out.println("Please enter new population size");
        long population = Long.parseLong(scanner.nextLine());

        if (population <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        inputUpdateTable(countryID, countryName, countryCode, countryArea, neighboringCountry, population);

        displayTable();
        continuePrompt();
    }

    private void inputUpdateTable(int countryID, String countryCode, String countryName, double countryArea, String neighboringCountry, Long population) {

        inTransaction(entityManager -> {
            Country country = entityManager.find(Country.class, countryID);
            if (country != null) {
                country.setCountryName(countryName);
                country.setCountryCode(countryCode);
                country.setCountryArea(countryArea);
                country.setCountryNeighbor(neighboringCountry);
                country.setCountryPopulationSize(population);
                entityManager.merge(country);
            }
        });
    }

    @Override
    public void deleteRowInTable() {
        displayTable();
        System.out.println("Please enter the id of the country you want to delete");
        int removeCountry = Integer.parseInt(scanner.nextLine());
        if (removeCountry <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }

        inputDeleteRowInTable(removeCountry);
        continuePrompt();
        displayTable();

    }

    private void inputDeleteRowInTable(int removeCountry) {
        inTransaction(entityManager -> {
            var country = entityManager.find(Country.class, removeCountry);
            if (country != null) {
                entityManager.remove(country);
                entityManager.flush();
            }
        });
    }

    @Override
    public void displayTable() {
        System.out.println("\nCountry table\n");
        System.out.println("Country ID | Country Code | Country Name | Country Area | Country Neighbor | Country Population | Country Continent ID | Country Continent Name |");

        inTransaction(entityManager -> {
            var eg = entityManager.getEntityGraph("Continent.country");

            var countries = entityManager.createQuery("select c from Country c", Country.class)
                    .setHint("jakarta.persistence.fetchgraph", eg).getResultList();
            countries.stream()
                    .map(c ->
                            c.getId() + " | "
                                    + c.getCountryCode() + " | "
                                    + c.getCountryName() + " | "
                                    + c.getCountryArea() + " | "
                                    + c.getCountryNeighbor() + " | "
                                    + c.getCountryPopulationSize() + " | "
                                    + c.getCountryContinent().getId() + " | "
                                    + c.getCountryContinent().getContinentName() + " | "
                    ).forEach(System.out::println);
        });

    }

    private void continuePrompt() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    /**
     * isAlpha
     * <p> Method evaluates if currencyName parameter matches letters a to z or A to Z, space and comma </p>
     *
     * @param currencyName String passed for evaluation.
     * @return boolean 'true' if regex patten is match, otherwise 'false'
     */
    private boolean isAlpha(String currencyName) {
        Pattern isAlpha = Pattern.compile("[a-zA-Z /,]+");
        return isAlpha.matcher(currencyName).matches();

    }

}

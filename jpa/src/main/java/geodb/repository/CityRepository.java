package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.City;
import geodb.entity.Country;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static geodb.JPAUtil.getEntityManager;
import static geodb.JPAUtil.inTransaction;

public class CityRepository implements Crudable {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CityRepository cityRepository = new CityRepository();
        cityRepository.insertToTable();
    }

    @Override
    public void insertToTable() {
        City city = new City();
        System.out.println("Enter the name of the city: ");
        String cityName = scanner.nextLine();

        if (cityName.isEmpty()) {
            throw new RuntimeException("City name cannot be empty");
        }

        System.out.println("Enter the population of the city: ");
        long population = scanner.nextLong();
        scanner.nextLine();

        if (population <= 0) {
            throw new RuntimeException("Population must be larger than 0");
        }

        System.out.println("Enter the area of the city: ");
        double area = scanner.nextDouble();
        scanner.nextLine();

        if (area <= 0) {
            throw new RuntimeException("Area must be larger than 0");
        }
        System.out.println("Is this a capital (Y/N)? ");
        String capital = scanner.nextLine();

        boolean isCapital = capital.equals("Y");

        System.out.println("Enter the id of the country: ");
        int countryId = scanner.nextInt();
        scanner.nextLine();

        city.setCityName(cityName);
        city.setCityPopulationSize(population);
        city.setCityArea(area);
        city.setCityCapital(isCapital);
        inputInsertToTable(city, countryId);

    }

    private static void inputInsertToTable(City city, int countryId) {
        inTransaction
                (entityManager -> {
                    Country country = entityManager.find(Country.class, countryId);
                    if (country == null) {
                        throw new EntityNotFoundException("Country not found with ID: " + countryId);
                    }
                    city.setCityCountry(country);
                    entityManager.persist(city);});

    }

    @Override
    public void updateTable() {

        System.out.println("Enter the id of the city you want to update: ");
        int cityId = scanner.nextInt();
        scanner.nextLine();
        if (cityId <= 0) {
            throw new RuntimeException("Id must be larger than 0");
        }

        System.out.println("Enter the name of the city: ");
        String cityName = scanner.nextLine();
        validateCityName(cityName);

        System.out.println("Enter the population of the city: ");
        long population = scanner.nextLong();
        scanner.nextLine();
        validateCityPopulation(population);

        System.out.println("Enter the area of the city: ");
        double area = scanner.nextDouble();
        scanner.nextLine();
        validateCityArea(area);

        System.out.println("Enter it it's a capital(Y/N): ");
        String isCapitalInput = scanner.nextLine();
        validateIsCapital(isCapitalInput);
        boolean isCapital = isCapitalInput.equals("Y");

        inputUpdateTable(cityId, cityName, population, area, isCapital);
    }

    @Override
    public void deleteRowInTable() {

        System.out.println("Enter the id of the country you want to delete: ");
        int cityID = scanner.nextInt();
        scanner.nextLine();

        if (cityID <= 0) {
            throw new RuntimeException("ID must be a positive number greater than 0");
        }

        inTransaction(entityManager -> {
            City city = entityManager.find(City.class, cityID);
            if (city != null) {
                entityManager.remove(city);
            }
        });
    }

    @Override
    public void displayTable() {
        System.out.println("City Table\n\n");
        System.out.println("City ID | Country name | City name | City population | City area | City capital");
        inTransaction(entityManager -> {
            TypedQuery<City> query = entityManager.createQuery("SELECT c FROM City c", City.class);
            List<City> rows = query.getResultList();
            rows.stream()
                    .map(n -> n.getId() + ".  |  " + n.getCityCountry() + " | " + n.getCityName() + " | " + n.getCityPopulationSize() + " | " + n.getCityArea() + " | " + n.getCityCapital())
                    .forEach(System.out::println);
        });
    }

    private void validateCityArea(double area) {
        if (area < 0) {
            throw new RuntimeException("Area must not be negative");
        }
    }

    private void validateCityPopulation(long population) {
        if (population < 0) {
            throw new RuntimeException("Population must be not be negative");
        }
    }

    private void validateCityName(String cityName) {
        if (cityName.trim().isEmpty()) {
            throw new RuntimeException("City name cannot be empty");
        } else if (cityName.matches(".*\\d.*")) {
            throw new InputMismatchException("City name must not contain numbers");
        }
    }

    private void validateIsCapital(String isCapitalInput) {
        if (isCapitalInput.trim().isEmpty()) {
            throw new RuntimeException("Answer cannot be empty");
        } else if (!isCapitalInput.matches("^[yYnN]+$")) {
            throw new RuntimeException("Not a valid answer (Y/N)");
        }
    }

    private static void inputUpdateTable(int newCityId, String newCityName, long newPopulation, double newArea, boolean isCapital) {
        inTransaction(entityManager -> {
            City newCity = entityManager.find(City.class, newCityId);
            if (newCity != null) {
                newCity.setCityName(newCityName);
                newCity.setCityPopulationSize(newPopulation);
                newCity.setCityArea(newArea);
                newCity.setCityCapital(isCapital);
                entityManager.merge(newCity);
            }
        });
    }
}

package geodb.repository;

import static geodb.JPAUtil.inTransaction;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Continent;
import geodb.entity.Country;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CountryRepository implements Crudable {
    static private Scanner scanner = new Scanner(System.in);
    @Override
    public void insertToTable() {
        Country country = new Country();
        ContinentRepository continentRepository = new ContinentRepository();
        System.out.println("Please enter the new country you want to insert to the country table");
        String countryName = scanner.nextLine();
        boolean isCountryName = isAlpha(countryName);
        if (countryName == null || countryName.isEmpty() || !isCountryName) {
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

        System.out.println("Please enter the neighboring land countries separated by a comma");
        String neighboringCountry = scanner.nextLine();
        if (neighboringCountry == null || neighboringCountry.isEmpty() || !isAlpha(neighboringCountry)) {
            throw new IllegalArgumentException("Value must be a valid country code and not empty");
        }

        System.out.println("Please enter the population size in whole numbers");
        int population = Integer.parseInt(scanner.nextLine());
        if (population <= 0) {
            throw new IllegalArgumentException("Value must be a positive integer and greater than 0");
        }
        System.out.println("Please enter the continent name of the country");
        String continentName = scanner.nextLine();
        if (continentName == null || continentName.isEmpty() || !isAlpha(continentName)) {
            throw new IllegalArgumentException("Value must be a valid continent name and not empty");
        }
       // Add Continent object helper method.
       var hasContinent = findContinent(continentName);
        if(hasContinent.isEmpty()){
            System.out.println("Continent not found, please add the continent to the continent table");
            continentRepository.insertToTable();
            hasContinent = findContinent(continentName);

        }
       // Add find newly added country method.
       // Add methods that calls for insertToTable for different entities object.

    }

    private Optional<Continent> findContinent(String continentName) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try{
           return Optional.of( entityManager.find(Continent.class, continentName));
    }catch (Exception e){
        return Optional.empty();}
    }


    @Override
    public void updateTable() {

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
        inTransaction(entityManager ->{
            var country = entityManager.find(Country.class, removeCountry);
            if (country != null) {
                entityManager.remove(country);
                entityManager.flush();
            }
        });
    }

    @Override
    public void displayTable() {
        inTransaction(entityManager ->{

            var countries = entityManager.createQuery("select c from Country c", Country.class).getResultList();
            countries.stream()
                    .map(c -> c.getId() + "|" + c.getCountryName()).forEach(System.out::println);
        } );

    }

    private void continuePrompt() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    private boolean isAlpha(String currencyName) {
        Pattern isAlpha = Pattern.compile("[a-zA-Z /,]+");
        return isAlpha.matcher(currencyName).matches();

    }

    public static void main(String[] args) {
        CountryRepository repo = new CountryRepository();
        repo.deleteRowInTable();
    }
}

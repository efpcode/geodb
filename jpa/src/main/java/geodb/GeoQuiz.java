package geodb;

import geodb.entity.*;
import jakarta.persistence.EntityManager;

import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeoQuiz {

    private static final Scanner sc = new Scanner(System.in);

    public void startQuiz() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            System.out.println("Debug: Entering startQuiz method");

            List<Continent> continents = entityManager.createQuery("from Continent", Continent.class).getResultList();
            List<Country> countries = entityManager.createQuery("from Country", Country.class).getResultList();
            List<LandMark> landmarks = entityManager.createQuery("from LandMark", LandMark.class).getResultList();
            List<City> cities = entityManager.createQuery("from City", City.class).getResultList();
            List<Ocean> oceans = entityManager.createQuery("from Ocean", Ocean.class).getResultList();
            List<geodb.entity.Currency> currencies = entityManager.createQuery("from Currency", geodb.entity.Currency.class).getResultList();

            whichIsLargestByArea(countries);
            cityLargestByPopulation(cities);

        } finally {
            entityManager.close();
        }
    }

    public void whichIsLargestByArea(List<Country> countries) {
        Random random = new Random();

        Country randomCountry1 = countries.get(random.nextInt(countries.size()));
        Country randomCountry2;
        Country randomCountry3;

        do {
            randomCountry2 = countries.get(random.nextInt(countries.size()));
        } while (randomCountry2.equals(randomCountry1));

        do {
            randomCountry3 = countries.get(random.nextInt(countries.size()));
        } while (randomCountry3.equals(randomCountry1) || randomCountry3.equals(randomCountry2));

        Country largestCountry = randomCountry1;
        if (randomCountry2.getCountryArea() > largestCountry.getCountryArea()) {
            largestCountry = randomCountry2;
        }
        if (randomCountry3.getCountryArea() > largestCountry.getCountryArea()) {
            largestCountry = randomCountry3;
        }

        String question = """
                Which of these three countries is the largest by area?
                1: %s
                2: %s
                3: %s
                """.formatted(randomCountry1.getCountryName(), randomCountry2.getCountryName(), randomCountry3.getCountryName());

        System.out.println(question);

        System.out.print("Enter your choice (1, 2, or 3): ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please select 1, 2, or 3.");
        } else {
            Country chosenCountry = switch (choice) {
                case 1 -> randomCountry1;
                case 2 -> randomCountry2;
                case 3 -> randomCountry3;
                default -> null;
            };

            if (chosenCountry.equals(largestCountry)) {
                System.out.println("Correct! " + largestCountry.getCountryName() + " is the largest by area.");
            } else {
                System.out.println("Wrong! The correct answer is " + largestCountry.getCountryName() + ".");
            }
        }
    }

    public void cityLargestByPopulation(List<City> cities) {
        Random random = new Random();

        City randomCity1 = cities.get(random.nextInt(cities.size()));
        City randomCity2;
        City randomCity3;

        do {
            randomCity2 = cities.get(random.nextInt(cities.size()));
        } while (randomCity2.equals(randomCity1));

        do {
            randomCity3 = cities.get(random.nextInt(cities.size()));
        } while (randomCity3.equals(randomCity1) || randomCity3.equals(randomCity2));

        City largestCity = randomCity1;
        if (randomCity2.getCityPopulationSize() > largestCity.getCityPopulationSize()) {
            largestCity = randomCity2;
        }
        if (randomCity3.getCityPopulationSize() > largestCity.getCityPopulationSize()) {
            largestCity = randomCity3;
        }

        String question = """
                Which of these cities has the largest population?
                1: %s
                2: %s
                3: %s
                """.formatted(randomCity1.getCityName(), randomCity2.getCityName(), randomCity3.getCityName());

        System.out.println(question);

        System.out.print("Enter your choice (1, 2, or 3): ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please select 1, 2, or 3.");
        } else {
            City selectedCity = switch (choice) {
                case 1 -> randomCity1;
                case 2 -> randomCity2;
                case 3 -> randomCity3;
                default -> null;
            };

            if (selectedCity.equals(largestCity)) {
                System.out.println("Correct! " + largestCity.getCityName() + " has the largest population.");
            } else {
                System.out.println("Wrong answer. The correct answer is " + largestCity.getCityName() + ".");
            }
        }
    }

    public void whereIsLandmark(List<LandMark> landmarks) {

        Random random = new Random();
        LandMark randomLandMark1 = landmarks.get(random.nextInt(landmarks.size()));



    }

    public void whatCurrency(List<Currency> currencies) {

    }

    public void whichHasOceanNextTo(List<Ocean> oceans) {

    }

    public static void main(String[] args) {
        GeoQuiz geoQuiz = new GeoQuiz();
        geoQuiz.startQuiz();

    }
}



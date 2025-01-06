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

            List<Continent> continents = entityManager.createQuery("from Continent", Continent.class).getResultList();
            List<Country> countries = entityManager.createQuery("from Country", Country.class).getResultList();
            List<LandMark> landmarks = entityManager.createQuery("from LandMark", LandMark.class).getResultList();
            List<City> cities = entityManager.createQuery("from City", City.class).getResultList();
            List<Ocean> oceans = entityManager.createQuery("from Ocean", Ocean.class).getResultList();
            List<geodb.entity.Currency> currencies = entityManager.createQuery("from Currency", geodb.entity.Currency.class).getResultList();

            whichIsLargestByArea(countries);
            cityLargestByPopulation(cities);
            whereIsLandmark(landmarks);
            whatCurrency(currencies);

        } finally {
            entityManager.close();
        }
    }

    public void whichIsLargestByArea(List<Country> countries) {

        if (countries == null || countries.isEmpty()) {
            System.out.println("No countries available for the quiz.");
            return;
        }

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

        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities available for the quiz.");
            return;
        }

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

        if (landmarks == null || landmarks.isEmpty()) {
            System.out.println("No landmarks available for the quiz.");
            return;
        }

        Random random = new Random();
        LandMark randomLandMark = landmarks.get(random.nextInt(landmarks.size()));
        String expectedCountryName = randomLandMark.getLandMarkCountry().getCountryName();

        System.out.println("In which country is the landmark \"" + randomLandMark.getLandMarkName() + "\" located?");

        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        System.out.print("Your answer: ");
        String answer = sc.nextLine();

        if (answer == null || answer.trim().isEmpty()) {
            System.out.println("Please provide a valid answer.");
            return;
        }

        if (expectedCountryName.equalsIgnoreCase(answer.trim())) {
            System.out.println("Correct! \"" + randomLandMark.getLandMarkName() + "\" is in " + expectedCountryName + ".");
        } else {
            System.out.println("Incorrect. \"" + randomLandMark.getLandMarkName() + "\" is in " + expectedCountryName + ".");
        }
    }

    public void whatCurrency(List<geodb.entity.Currency> currencies) {

        if (currencies == null || currencies.isEmpty()) {
            System.out.println("No currencies available for the quiz.");
            return;
        }

        Random random = new Random();
        geodb.entity.Currency randomCurrency = currencies.get(random.nextInt(currencies.size()));
        String expectedCountryName = randomCurrency.getCurrencyCountry().getCountryName(); // Assuming Currency has a Country field

        System.out.println("What country has " + randomCurrency.getCurrencyName() + " as currency?");

        if (sc.hasNextLine()) {
            sc.nextLine();
        }

        System.out.print("Your answer: ");
        String answer = sc.nextLine();

        if (answer == null || answer.trim().isEmpty()) {
            System.out.println("Please provide a valid answer.");
            return;
        }

        if (expectedCountryName.equalsIgnoreCase(answer.trim())) {
            System.out.println("Correct! \"" + randomCurrency.getCurrencyName() + "\" belongs to " + expectedCountryName + ".");
        } else {
            System.out.println("Incorrect! \"" + randomCurrency.getCurrencyName() + "\" belongs to " + expectedCountryName + ".");
        }
    }

//     5.Which of these 2 Countries does NOT have the Indian Ocean next to them?
    public void whichHasOceanNextTo(List<Ocean> oceans, List<Country> countries) {

        if (oceans == null || oceans.isEmpty()) {
            System.out.println("No oceans available for the quiz.");
        }

        Random random = new Random();

        Country country1 = countries.get(random.nextInt(countries.size()));
        Country country2;

        do {
            country2 = countries.get(random.nextInt(countries.size()));
        } while (country2.equals(country1));

        boolean country1HasIndianOcean = country1.getOceans("Indian Ocean");
        boolean country2HasIndianOcean = country2.("Indian Ocean");

        if (country1HasIndianOcean == country2HasIndianOcean) {
            System.out.println("Both countries either have or do not have the Indian Ocean. Trying again...");
            whichHasOceanNextTo(oceans, countries);
            return;
        }

        System.out.printf("""
            Which of these two countries does NOT have the Indian Ocean next to them?
            1: %s
            2: %s
            """, country1.getCountryName(), country2.getCountryName());

        System.out.print("Enter your choice (1 or 2): ");
        int choice = sc.nextInt();

        Country chosenCountry = (choice == 1) ? country1 : country2;
        boolean correct = !chosenCountry.("Indian Ocean");

        System.out.println(correct
                ? "Correct! " + chosenCountry.getCountryName() + " does NOT have the Indian Ocean next to them."
                : "Wrong! The correct answer is " + (choice == 1 ? country2.getCountryName() : country1.getCountryName()) + ".");
    }


    public static void main(String[] args) {
        GeoQuiz geoQuiz = new GeoQuiz();
        geoQuiz.startQuiz();


    }
}



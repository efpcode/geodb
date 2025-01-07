package geodb;

import geodb.entity.*;
import jakarta.persistence.EntityManager;

import java.util.*;
import java.util.Currency;

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
            whichHasOceanNextTo(countries);

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

        int choice = -1;

        while (choice < 1 || choice > 3) {
            System.out.print("Enter your choice (1, 2, or 3): ");
            try {
                choice = sc.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                sc.next();
            }
        }

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

        int choice = -1;
        while (true) {
            System.out.print("Enter your choice (1, 2, or 3): ");
            try {
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                sc.nextLine();
            }
        }

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

    public void whereIsLandmark(List<LandMark> landmarks) {

        if (landmarks == null || landmarks.isEmpty()) {
            System.out.println("No landmarks available for the quiz.");
            return;
        }

        Random random = new Random();
        LandMark randomLandMark = landmarks.get(random.nextInt(landmarks.size()));
        String expectedCountryName = randomLandMark.getLandMarkCountry().getCountryName();

        System.out.println("In which country is the landmark \"" + randomLandMark.getLandMarkName() + "\" located?");

        String answer = null;
        while (true) {
            System.out.print("Your answer: ");
            answer = sc.nextLine().trim();

            if (answer.isEmpty()) {
                System.out.println("Invalid input. Blank input not accepted.");
            } else if (answer.matches("\\d+")) {
                System.out.println("Invalid input. Please do not enter numbers.");
            } else {
                break;
            }
        }

        if (expectedCountryName.equalsIgnoreCase(answer)) {
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
        String expectedCountryName = randomCurrency.getCurrencyCountry().getCountryName();

        System.out.println("What country has " + randomCurrency.getCurrencyName() + " as currency?");

        String answer = null;
        while (true) {
            System.out.print("Your answer: ");
            answer = sc.nextLine().trim();

            if (answer.isEmpty()) {
                System.out.println("Invalid input. Please provide a valid answer (letters only).");
            } else if (answer.matches("\\d+")) {
                System.out.println("Invalid input. Please do not enter numbers.");
            } else {
                break;
            }
        }

        if (expectedCountryName.equalsIgnoreCase(answer)) {
            System.out.println("Correct! \"" + randomCurrency.getCurrencyName() + "\" belongs to " + expectedCountryName + ".");
        } else {
            System.out.println("Incorrect! \"" + randomCurrency.getCurrencyName() + "\" belongs to " + expectedCountryName + ".");
        }
    }

    public void whichHasOceanNextTo(List<Country> countries) {
        if (countries == null || countries.size() < 2) {
            System.out.println("Not enough countries available for the quiz.");
            return;
        }

        Random random = new Random();

        Country country1 = countries.get(random.nextInt(countries.size()));
        Country country2;

        do {
            country2 = countries.get(random.nextInt(countries.size()));
        } while (country2.equals(country1));

        boolean country1HasIndianOcean = country1.getOceans().stream()
                .anyMatch(ocean -> "Indian Ocean".equalsIgnoreCase(ocean.getOceanName()));

        boolean country2HasIndianOcean = country2.getOceans().stream()
                .anyMatch(ocean -> "Indian Ocean".equalsIgnoreCase(ocean.getOceanName()));

        if (country1HasIndianOcean == country2HasIndianOcean) {
            System.out.println("Both countries either have or do not have the Indian Ocean. Trying again...");
            whichHasOceanNextTo(countries);
            return;
        }

        System.out.printf("""
                Which of these two countries does NOT have the Indian Ocean next to them?
                1: %s
                2: %s
                """, country1.getCountryName(), country2.getCountryName());

        int choice = -1;

        while (choice != 1 && choice != 2) {
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = sc.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                sc.next();
            }
        }

        Country chosenCountry = (choice == 1) ? country1 : country2;
        boolean correct = chosenCountry.getOceans().stream()
                .noneMatch(ocean -> "Indian Ocean".equalsIgnoreCase(ocean.getOceanName()));

        System.out.println(correct
                ? "Correct! " + chosenCountry.getCountryName() + " does not have the Indian Ocean next to them."
                : "Wrong! The correct answer is " + (choice == 1 ? country2.getCountryName() : country1.getCountryName()) + ".");
    }

    public static void main(String[] args) {
        GeoQuiz geoQuiz = new GeoQuiz();
        geoQuiz.startQuiz();

    }
}
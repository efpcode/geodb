package geodb;

import geodb.entity.*;
import jakarta.persistence.EntityManager;

import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeoQuiz {

    private Scanner sc = new Scanner(System.in);

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

        } finally {
            entityManager.close();
        }

    }

    public void whichIsLargestByArea(List<Country> countries) {

        Random random = new Random();
        Scanner answer = new Scanner(System.in);

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
        int userChoice = Integer.parseInt(answer.nextLine());

        Country chosenCountry;
        switch (userChoice) {
            case 1:
                chosenCountry = randomCountry1;
                break;
            case 2:
                chosenCountry = randomCountry2;
                break;
            case 3:
                chosenCountry = randomCountry3;
                break;
            default:
                System.out.println("Invalid choice! Please enter 1, 2, or 3.");
                return;
        }

        if (chosenCountry.equals(largestCountry)) {
            System.out.println("Correct! " + largestCountry.getCountryName() + " is the largest by area.");
        } else {
            System.out.println("Wrong! The correct answer is " + largestCountry.getCountryName() + ".");
        }
    }


    public void cityLargestByPopulation(List<City> cities) {

    }

    public void whereIsLandmark(List<LandMark> landmarks) {

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



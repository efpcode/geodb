package geodb;

import geodb.entity.City;
import geodb.repository.CityRepository;

import java.util.Scanner;

public class CityService implements Crudable {

    private static final Scanner scanner = new Scanner(System.in);
    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertToTable() {

        System.out.println("Creat new city\n==============");

        System.out.print("Please enter the name of the city: ");
        String cityName = scanner.nextLine();

        System.out.print("Please enter the population: ");
        long cityPopulationSize = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Please enter the area of the city: ");
        Double cityArea = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Please enter if the city is a capital (Y/N): ");
        String isCapital = scanner.nextLine();
        // TODO: Add error handling, loop until acceptable value:
        boolean cityCapital = isCapital.equals("Y");

        repository.save(new City(cityName, cityPopulationSize, cityArea, cityCapital));
    }

    @Override
    public void updateTable() {
        System.out.println("Update city\n===========");
        System.out.print("Please enter the id of the city to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        City city = repository.findById(id);

        System.out.printf("Please enter the city name (%s): ", city.getCityName());
        String cityName = scanner.nextLine();

        System.out.printf("Please enter the new population size (%d): ", city.getCityPopulationSize());
        long cityPopulationSize = scanner.nextLong();
        scanner.nextLine();

        System.out.printf("Please enter the new area of the city (%f): ", city.getCityArea());
        Double cityArea = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("Please enter if the city is a capital (%b): ", city.isCityCapital());
        String isCapital = scanner.nextLine();
        // TODO: Add error handling, loop until acceptable value:
        boolean cityCapital = isCapital.equals("Y");

        city.setCityName(cityName);
        city.setCityPopulationSize(cityPopulationSize);
        city.setCityArea(cityArea);
        city.setCityCapital(cityCapital);

        repository.save(city);
    }

    @Override
    public void deleteRowInTable() {
        System.out.println("Delete city\n===============");
        System.out.println("Please enter the id of the city to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        repository.deleteById(id);
    }

    @Override
    public void displayTable() {
        repository.findAll().forEach(System.out::println);
    }
}

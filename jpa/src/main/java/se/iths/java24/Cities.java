package se.iths.java24;

import java.util.List;

public class Cities {

    CountryRepository countryRepository = new CountryRepository();
    CityRepository cityRepository = new CityRepository();

    public void addCityToCountry(String cityName, int population, String countryName) {
        //Validation
        if (cityName == null || cityName.isBlank())
            throw new IllegalArgumentException("City name cannot be null or empty");
        if (population < 0)
            throw new IllegalArgumentException("Population cannot be negative");

        //Create and add new city to country
        var country = countryRepository.countryWithName(countryName);
        country.ifPresent(country1 -> {
            City newCity = new City();
            newCity.setCityName(cityName);
            newCity.setPopulation(population);
            newCity.setCountry(country1);
//            newCity.setCountry(country1);
//            country1.getCities().add(newCity);
            cityRepository.saveCity(newCity);
        });
    }

    public List<CitiesWithHighPopulation> citiesWithMorePopulationThan(int minpopulation) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}

record CitiesWithHighPopulation(String cityName, int population) {
}

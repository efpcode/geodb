package se.iths.java24;

public record CityDTO(String cityName, String countryName, int population) {
    // This record can be used for projections and lightweight data transfer
}

package se.iths.java24.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "city", schema = "demo")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(name = "population", nullable = false)
    private Integer population;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        country.getCities().add(this);
    }

    @Override
    public String toString() {
        return "City{" +
               "id=" + id +
               ", cityName='" + cityName + '\'' +
               ", population=" + population +
               '}';
    }
}

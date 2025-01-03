package geodb.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "country", schema = "geodb")
@NamedEntityGraph(name = "Continent.country",
        attributeNodes = @NamedAttributeNode("countryContinent"))
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryID", nullable = false)
    private Integer id;

    @Column(name = "countryCode", nullable = false)
    private String countryCode;

    @Column(name = "countryName", nullable = false)
    private String countryName;

    @Column(name = "countryArea", nullable = false)
    private Double countryArea;

    @ColumnDefault("'Island'")
    @Column(name = "countryNeighbor", nullable = false)
    private String countryNeighbor;

    @Column(name = "countryPopulationSize", nullable = false)
    private Long countryPopulationSize;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryContinent", nullable = false)
    private Continent countryContinent;

    @OneToMany(mappedBy = "cityCountry", cascade = CascadeType.REMOVE)
    private Set<City> cities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "currencyCountry", cascade = CascadeType.REMOVE)
    private Set<geodb.entity.Currency> currencies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "landMarkCountry", cascade = CascadeType.REMOVE)
    private Set<LandMark> landmarks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "oceanCountry", cascade = CascadeType.REMOVE)
    private Set<geodb.entity.Ocean> oceans = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(Double countryArea) {
        this.countryArea = countryArea;
    }

    public String getCountryNeighbor() {
        return countryNeighbor;
    }

    public void setCountryNeighbor(String countryNeighbor) {
        this.countryNeighbor = countryNeighbor;
    }

    public Long getCountryPopulationSize() {
        return countryPopulationSize;
    }

    public void setCountryPopulationSize(Long countryPopulationSize) {
        this.countryPopulationSize = countryPopulationSize;
    }

    public Continent getCountryContinent() {
        return countryContinent;
    }

    public void setCountryContinent(Continent countryContinent) {
        this.countryContinent = countryContinent;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<geodb.entity.Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<geodb.entity.Currency> currencies) {
        this.currencies = currencies;
    }

    public Set<LandMark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(Set<LandMark> landmarks) {
        this.landmarks = landmarks;
    }

    public Set<geodb.entity.Ocean> getOceans() {
        return oceans;
    }

    public void setOceans(Set<geodb.entity.Ocean> oceans) {
        this.oceans = oceans;
    }

}

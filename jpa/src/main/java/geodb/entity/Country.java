package geodb.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "country", schema = "geodb")
public class Country {
    @Id
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
    @JoinColumn(name = "countryCity", nullable = false)
    private City countryCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryContinent", nullable = false)
    private Continent countryContinent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryCurrency", nullable = false)
    private Currency countryCurrency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryLandMark", nullable = false)
    private LandMark countryLandMark;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryOcean", nullable = false)
    private Ocean countryOcean;

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

    public City getCountryCity() {
        return countryCity;
    }

    public void setCountryCity(City countryCity) {
        this.countryCity = countryCity;
    }

    public Continent getCountryContinent() {
        return countryContinent;
    }

    public void setCountryContinent(Continent countryContinent) {
        this.countryContinent = countryContinent;
    }

    public Currency getCountryCurrency() {
        return countryCurrency;
    }

    public void setCountryCurrency(Currency countryCurrency) {
        this.countryCurrency = countryCurrency;
    }

    public LandMark getCountryLandMark() {
        return countryLandMark;
    }

    public void setCountryLandMark(LandMark countryLandMark) {
        this.countryLandMark = countryLandMark;
    }

    public Ocean getCountryOcean() {
        return countryOcean;
    }

    public void setCountryOcean(Ocean countryOcean) {
        this.countryOcean = countryOcean;
    }

}

package se.iths.java24.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country", schema = "demo")
@NamedEntityGraph(name = "Country.cities",
        attributeNodes = @NamedAttributeNode("cities"))
public class Country {
    @Id
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Transient
    private String threeLetterName;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    public List<City> getCities() {
        return cities;
    }
    public String getThreeLetterName() {
        return threeLetterName;
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

    @Override
    public String toString() {
        return "Country{" +
               "countryCode='" + countryCode + '\'' +
               ", countryName='" + countryName + '\'' +
               ", threeLetterName='" + threeLetterName + '\'' +
               ", cities=" + cities +
               '}';
    }

    @PostLoad
    public void postLoad() {
        threeLetterName = countryName.substring(0, 3);
    }
}

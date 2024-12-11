package se.iths.java24;

import jakarta.persistence.*;

@Entity
@Table(name = "country", schema = "demo")
public class Country {
    @Id
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Transient
    private String threeLetterName;

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
               '}';
    }

    @PostLoad
    public void postLoad() {
        threeLetterName = countryName.substring(0, 3);
    }
}

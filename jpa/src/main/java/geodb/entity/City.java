package geodb.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "city", schema = "geodb")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityID", nullable = false)
    private Integer id;

    @Column(name = "cityName", nullable = false)
    private String cityName;

    @Column(name = "cityPopulationSize", nullable = false)
    private Long cityPopulationSize;

    @Column(name = "cityArea", nullable = false)
    private Double cityArea;

    @ColumnDefault("0")
    @Column(name = "cityCapital", nullable = false)
    private Boolean cityCapital = false;

    public City() {
        // Required by JPA
    }

    public City(String cityName, Long cityPopulationSize, Double cityArea, Boolean cityCapital) {
        this.cityName = cityName;
        this.cityPopulationSize = cityPopulationSize;
        this.cityArea = cityArea;
        this.cityCapital = cityCapital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCityPopulationSize() {
        return cityPopulationSize;
    }

    public void setCityPopulationSize(Long cityPopulationSize) {
        this.cityPopulationSize = cityPopulationSize;
    }

    public Double getCityArea() {
        return cityArea;
    }

    public void setCityArea(Double cityArea) {
        this.cityArea = cityArea;
    }

    public Boolean getCityCapital() {
        return cityCapital;
    }

    public void setCityCapital(Boolean cityCapital) {
        this.cityCapital = cityCapital;
    }
}
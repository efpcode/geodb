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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cityCountry", nullable = false)
    private geodb.entity.Country cityCountry;

    @Column(name = "cityName", nullable = false)
    private String cityName;

    @Column(name = "cityPopulationSize", nullable = false)
    private Long cityPopulationSize;

    @Column(name = "cityArea", nullable = false)
    private Double cityArea;

    @ColumnDefault("0")
    @Column(name = "cityCapital", nullable = false)
    private Boolean cityCapital = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public geodb.entity.Country getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(geodb.entity.Country cityCountry) {
        this.cityCountry = cityCountry;
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

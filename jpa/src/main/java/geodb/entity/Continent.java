package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "continent", schema = "geodb")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continentID", nullable = false)
    private Integer id;

    @Column(name = "continentName", nullable = false)
    private String continentName;

    @Column(name = "continentArea", nullable = false)
    private Double continentArea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public Double getContinentArea() {
        return continentArea;
    }

    public void setContinentArea(Double continentArea) {
        this.continentArea = continentArea;
    }

}
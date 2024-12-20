package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "landmark", schema = "geodb")
public class LandMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "landMarkID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "landMarkCountry", nullable = false)
    private Country landMarkCountry;

    @Column(name = "landMarkName", nullable = false)
    private String landMarkName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getLandMarkCountry() {
        return landMarkCountry;
    }

    public void setLandMarkCountry(Country landMarkCountry) {
        this.landMarkCountry = landMarkCountry;
    }

    public String getLandMarkName() {
        return landMarkName;
    }

    public void setLandMarkName(String landMarkName) {
        this.landMarkName = landMarkName;
    }

    @Override
    public String toString() {
        return "LandMark{" +
                "id=" + id +
                ", landMarkCountry=" + landMarkCountry +
                ", landMarkName='" + landMarkName + '\'' +
                '}';
    }
}

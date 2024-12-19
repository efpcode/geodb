package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ocean", schema = "geodb")
public class Ocean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oceanID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "oceanCountry", nullable = false)
    private Country oceanCountry;

    @Column(name = "oceanName", nullable = false)
    private String oceanName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getOceanCountry() {
        return oceanCountry;
    }

    public void setOceanCountry(Country oceanCountry) {
        this.oceanCountry = oceanCountry;
    }

    public String getOceanName() {
        return oceanName;
    }

    public void setOceanName(String oceanName) {
        this.oceanName = oceanName;
    }

}

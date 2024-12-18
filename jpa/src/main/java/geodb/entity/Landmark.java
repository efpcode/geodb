package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "landmark", schema = "geodb")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "landMarkID", nullable = false)
    private Integer id;

    @Column(name = "landMarkName", nullable = false)
    private String landMarkName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandMarkName() {
        return landMarkName;
    }

    public void setLandMarkName(String landMarkName) {
        this.landMarkName = landMarkName;
    }

    @Override
    public String toString() {
        return "Landmark{" +
                "id=" + id +
                ", landMarkName='" + landMarkName + '\'' +
                '}';
    }
}
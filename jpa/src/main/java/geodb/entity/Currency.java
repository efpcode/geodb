package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency", schema = "geodb")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currencyID", nullable = false)
    private Integer id;

    @Column(name = "currencyName", nullable = false)
    private String currencyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

}

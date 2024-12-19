package geodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency", schema = "geodb")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currencyID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currencyCountry", nullable = false)
    private Country currencyCountry;

    @Column(name = "currencyName", nullable = false)
    private String currencyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCurrencyCountry() {
        return currencyCountry;
    }

    public void setCurrencyCountry(Country currencyCountry) {
        this.currencyCountry = currencyCountry;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

}

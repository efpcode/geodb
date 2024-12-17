package geodb.entity;

import geodb.Crudable;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "currency", schema = "geodb")
public class Currency implements Crudable {
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

    @Override
    public void insertToTable() {

    }

    @Override
    public void updateTable() {

    }

    @Override
    public void deleteRowInTable() {

    }

    @Override
    public void displayTable() {

    }
}

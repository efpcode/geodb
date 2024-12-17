package geodb.repository;
import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Currency;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CurrencyRepository implements Crudable {
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
        System.out.println("Currency Table\n\n");
        System.out.println("Currency ID  |  Currency Name");
        JPAUtil.inTransaction(entityManager -> {
            TypedQuery<Currency> query = entityManager.createQuery("SELECT c FROM Currency c", Currency.class);
            List<Currency> rows = query.getResultList();
            rows.stream().map(n -> n.getId() + ".  |  " + n.getCurrencyName()).forEach(System.out::println);
        });
    }



}

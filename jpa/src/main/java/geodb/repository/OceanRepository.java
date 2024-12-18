package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Ocean;
import jakarta.persistence.EntityManager;
import java.util.Scanner;
import static geodb.JPAUtil.inTransaction;


public class OceanRepository implements Crudable {
    EntityManager em = JPAUtil.getEntityManager();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void insertToTable() {
        inTransaction(entityManager -> {

        });
    }

    @Override
    public void updateTable() {
        inTransaction(entityManager -> {

        });
    }

    @Override
    public void displayTable() {
        String queryDisplay = "SELECT o FROM Ocean o";
        inTransaction(entityManager -> {
            var o =entityManager.createQuery(queryDisplay, Ocean.class)
                    .getResultList();
            o.forEach(ocean -> {
                System.out.println("Name: "+ ocean.getOceanName());
            });
        });
    }

    @Override
    public void deleteRowInTable() {
        inTransaction(entityManager -> {

        });
    }
}

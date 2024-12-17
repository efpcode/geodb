package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Continent;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class ContinentRepository implements Crudable {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the name of the continent:");
        String continentName = sc.nextLine();

        System.out.println("Enter the area of the continent:");
        double continentArea = sc.nextDouble();
        sc.nextLine();

        JPAUtil.inTransaction(entityManager -> {
            Continent continent = new Continent();
            continent.setContinentName(continentName);
            continent.setContinentArea(continentArea);

            entityManager.persist(continent);
            System.out.println("Continent inserted: " + continent);
        });
    }

    @Override
    public void updateTable() {
        JPAUtil.inTransaction(entityManager -> {
            Continent continent = entityManager.createQuery(
                            "SELECT c FROM Continent c WHERE c.continentName = :name", Continent.class)
                    .setParameter("name", "Asia")
                    .getSingleResult();

            if (continent != null) {
                continent.setContinentArea(45000000.0);
                entityManager.merge(continent);
                System.out.println("Continent updated: " + continent);
            } else {
                System.out.println("Continent not found to update.");
            }
        });
    }

    @Override
    public void deleteRowInTable() {
        JPAUtil.inTransaction(entityManager -> {
            Continent continent = entityManager.createQuery(
                            "SELECT c FROM Continent c WHERE c.continentName = :name", Continent.class)
                    .setParameter("name", "Asia")
                    .getSingleResult();

            if (continent != null) {
                entityManager.remove(continent);
                System.out.println("Continent deleted: " + continent);
            } else {
                System.out.println("Continent not found to delete.");
            }
        });
    }

    @Override
    public void displayTable() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            List<Continent> continents = entityManager.createQuery(
                    "SELECT c FROM Continent c", Continent.class).getResultList();

            System.out.println("Continent Table:");
            continents.forEach(System.out::println);
        } finally {
            entityManager.close();
        }
    }

}

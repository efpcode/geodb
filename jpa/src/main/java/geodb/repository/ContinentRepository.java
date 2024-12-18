package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Continent;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Scanner;

public class ContinentRepository implements Crudable {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the name of the continent:");
        String continentName = sc.nextLine();

        if (continentName.trim().isEmpty()) {
            System.out.println("Continent name cannot be empty.");
            return;
        }

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
        System.out.println("Enter the name of the continent you want to update:");
        String continentName = sc.nextLine();

        if (continentName == null || continentName.trim().isEmpty()) {
            System.out.println("Continent name cannot be blank. Please try again.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                Continent continent = entityManager.createQuery(
                                "SELECT c FROM Continent c WHERE c.continentName = :name", Continent.class)
                        .setParameter("name", continentName)
                        .getSingleResult();

                if (continent != null) {
                    System.out.println("Enter the new name for the continent (blank to keep current name):");
                    String newName = sc.nextLine();

                    System.out.println("Enter the new area for the continent:");
                    double newArea = sc.nextDouble();
                    sc.nextLine();

                    if (newName != null && !newName.trim().isEmpty()) {
                        continent.setContinentName(newName.trim());
                    }
                    continent.setContinentArea(newArea);

                    entityManager.merge(continent);
                    System.out.println("Continent updated: " + continent);
                }
            } catch (NoResultException e) {
                System.out.println("Continent not found: " + continentName);
            } catch (Exception e) {
                System.out.println("An error occurred while updating the continent: " + e.getMessage());
            }
        });

    }

    @Override
    public void deleteRowInTable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the continent you want to delete:");
        String continentName = sc.nextLine();

        if (continentName.trim().isEmpty()) {
            System.out.println("Continent name cannot be empty.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                Continent continent = entityManager.createQuery(
                                "SELECT c FROM Continent c WHERE c.continentName = :name", Continent.class)
                        .setParameter("name", continentName)
                        .getSingleResult();

                if (continent != null) {
                    entityManager.remove(continent);
                    System.out.println("Continent deleted: " + continent);
                }
            } catch (NoResultException e) {
                System.out.println("Continent not found: " + continentName);
            } catch (Exception e) {
                System.out.println("An error occurred while deleting the continent: " + e.getMessage());
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

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

        if (continentArea <= 0) {
            System.out.println("Continent area cannot be less than 0.");
            return;
        }

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
        System.out.println("Enter the ID of the continent you want to update:");
        int continentID = sc.nextInt();
        sc.nextLine();

        JPAUtil.inTransaction(entityManager -> {
            try {
                Continent continent = entityManager.find(Continent.class, continentID);

                if (continent != null) {
                    System.out.println("Enter a new name for the continent (blank to keep current name):");
                    String newName = sc.nextLine().trim();

                    System.out.println("Enter new area for the continent:");
                    double newArea = sc.nextDouble();
                    sc.nextLine();

                    if (!newName.isEmpty()) {
                        continent.setContinentName(newName);
                    }
                    continent.setContinentArea(newArea);

                    entityManager.merge(continent);
                    System.out.println("Continent updated: " + continent);
                } else {
                    System.out.println("Continent with that ID not found: " + continentID);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while updating the continent: " + e.getMessage());
            }
        });
    }

    @Override
    public void deleteRowInTable() {
        System.out.println("Enter the ID of the continent you want to delete:");
        long continentId = sc.nextLong();
        sc.nextLine();

        JPAUtil.inTransaction(entityManager -> {
            try {
                Continent continent = entityManager.find(Continent.class, continentId);

                if (continent != null) {
                    entityManager.remove(continent);
                    System.out.println("Continent deleted: " + continent);
                } else {
                    System.out.println("Continent with that ID not found: " + continentId);
                }
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

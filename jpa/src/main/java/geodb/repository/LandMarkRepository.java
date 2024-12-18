package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Landmark;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Scanner;

public class LandMarkRepository implements Crudable {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the name of the landmark:");
        String landmarkName = sc.nextLine().trim();

        if (landmarkName.isEmpty()) {
            System.out.println("Landmark name cannot be empty.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            Landmark landmark = new Landmark();
            landmark.setLandMarkName(landmarkName);

            entityManager.persist(landmark);
            System.out.println("Landmark inserted: " + landmarkName);
        });
    }

    @Override
    public void updateTable() {
        System.out.println("Enter the ID of the landmark you want to update:");
        long landmarkId = sc.nextLong();
        sc.nextLine();

        if (landmarkId <= 0) {
            System.out.println("Invalid landmark ID.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                Landmark landmark = entityManager.find(Landmark.class, landmarkId);

                if (landmark == null) {
                    System.out.println("Landmark with that ID not found: " + landmarkId);
                    return;
                }

                System.out.println("Enter a new name for the landmark (Leave blank to keep the current name):");
                String newLandmarkName = sc.nextLine().trim();

                if (!newLandmarkName.isEmpty()) {
                    landmark.setLandMarkName(newLandmarkName);
                }

                entityManager.merge(landmark);
                System.out.println("Landmark updated successfully: " + landmark);

            } catch (Exception e) {
                System.out.println("An error occurred while updating the landmark: " + e.getMessage());
            }
        });
    }

    @Override
    public void deleteRowInTable() {
        System.out.println("Enter the ID of the landmark you want to delete:");
        long landmarkId = sc.nextLong();
        sc.nextLine();

        if (landmarkId <= 0) {
            System.out.println("Invalid landmark ID.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                Landmark landmark = entityManager.find(Landmark.class, landmarkId);

                if (landmark == null) {
                    System.out.println("Landmark with that ID not found: " + landmarkId);
                    return;
                }

                entityManager.remove(landmark);
                System.out.println("Landmark deleted: " + landmark);

            } catch (Exception e) {
                System.out.println("An error occurred while deleting the landmark: " + e.getMessage());
            }
        });
    }

    @Override
    public void displayTable() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            List<Landmark> landmarks = entityManager.createQuery(
                            "SELECT l FROM Landmark l", Landmark.class)
                    .getResultList();

            System.out.println("Landmark Table:");
            landmarks.forEach(System.out::println);

        } finally {
            entityManager.close();
        }
    }
}


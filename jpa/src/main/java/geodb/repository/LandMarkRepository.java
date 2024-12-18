package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Landmark;
import jakarta.persistence.NoResultException;

import java.util.Scanner;

public class LandMarkRepository implements Crudable {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the name of the landmark:");
        String landmarkName = sc.nextLine();

        if (landmarkName.trim().isEmpty()) {
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
        System.out.println("Enter the name of the landmark you want to update:");
        String landmarkName = sc.nextLine().trim();

        if (landmarkName.isEmpty()) {
            System.out.println("Landmark name cannot be empty.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                Landmark landmark = entityManager.createQuery(
                                "SELECT c FROM Landmark c WHERE c.landMarkName = :name", Landmark.class)
                        .setParameter("name", landmarkName)
                        .getSingleResult();

                System.out.println("Enter a new name for the landmark:");
                String newLandmarkName = sc.nextLine().trim();

                if (newLandmarkName.isEmpty()) {
                    System.out.println("New name cannot be empty. Update aborted.");
                    return;
                }

                landmark.setLandMarkName(newLandmarkName);
                entityManager.merge(landmark);

                System.out.println("Landmark updated successfully: " + landmark);
            } catch (NoResultException e) {
                System.out.println("Landmark not found: " + landmarkName);
            } catch (Exception e) {
                System.out.println("An error occurred while updating the landmark: " + e.getMessage());
            }
        });
    }

    @Override
    public void deleteRowInTable() {

    }

    @Override
    public void displayTable() {

    }
}

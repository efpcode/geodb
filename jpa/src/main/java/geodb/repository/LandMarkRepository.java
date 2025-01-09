package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Country;
import geodb.entity.LandMark;
import jakarta.persistence.EntityManager;


import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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

        if (checkCharacters(landmarkName)) return;

        System.out.println("Enter the ID of the country:");
        long countryId = sc.nextLong();
        sc.nextLine();

        JPAUtil.inTransaction(entityManager -> {
            Country country = entityManager.find(Country.class, countryId);

            if (country == null) {
                System.out.println("Country with ID " + countryId + " not found.");
                return;
            }

            LandMark landmark = new LandMark();
            landmark.setLandMarkName(landmarkName);
            landmark.setLandMarkCountry(country);

            entityManager.persist(landmark);
            System.out.println("Landmark inserted: " + landmark.getLandMarkName() + "in country " + country.getCountryName());
        });
    }


    @Override
    public void updateTable() {
        displayTable();
        System.out.println("Enter the ID of the landmark you want to update:");
        long landmarkId = sc.nextLong();
        sc.nextLine();

        if (landmarkId <= 0) {
            System.out.println("Invalid landmark ID.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                LandMark landmark = entityManager.find(LandMark.class, landmarkId);

                if (landmark == null) {
                    System.out.println("Landmark with that ID not found: " + landmarkId);
                    return;
                }

                System.out.println("Enter a new name for the landmark (Leave blank to keep the current name):");
                String newLandmarkName = sc.nextLine().trim();

                if (checkCharacters(newLandmarkName)) return;

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
        displayTable();
        System.out.println("Enter the ID of the landmark you want to delete:");
        long landmarkId = sc.nextLong();
        sc.nextLine();

        if (landmarkId <= 0) {
            System.out.println("Invalid landmark ID.");
            return;
        }

        JPAUtil.inTransaction(entityManager -> {
            try {
                LandMark landmark = entityManager.find(LandMark.class, landmarkId);

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
            List<LandMark> landmarks = entityManager.createQuery(
                            "SELECT l FROM LandMark l JOIN FETCH l.landMarkCountry", LandMark.class)
                    .getResultList();

            System.out.println("Landmark Table:");
            landmarks.forEach(landmark -> {
                System.out.println("Landmark ID: " + landmark.getId());
                System.out.println("Landmark: " + landmark.getLandMarkName() +
                        " Country: " + landmark.getLandMarkCountry().getCountryName());
            });

        } finally {
            entityManager.close();
        }
    }

    private static boolean checkCharacters(String landmarkName) {
        if (!Pattern.compile("[a-zA-Z\\s]*").matcher(landmarkName).matches()) {
            System.out.println("Only alphabetic characters are allowed");
            return true;
        }
        return false;
    }
}

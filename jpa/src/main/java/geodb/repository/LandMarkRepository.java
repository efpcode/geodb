package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Landmark;

import java.util.Scanner;

public class LandMarkRepository implements Crudable {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the name of the landmark:");
        String landmarkName = sc.nextLine();

        JPAUtil.inTransaction(entityManager -> {
            Landmark landmark = new Landmark();
            landmark.setLandMarkName(landmarkName);

            entityManager.persist(landmark);
            System.out.println("Landmark inserted: " + landmarkName);
        });
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

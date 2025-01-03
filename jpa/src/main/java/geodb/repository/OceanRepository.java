package geodb.repository;

import geodb.Crudable;
import geodb.JPAUtil;
import geodb.entity.Ocean;
import geodb.entity.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;

import java.util.Scanner;

import static geodb.JPAUtil.inTransaction;


public class OceanRepository implements Crudable {
    EntityManager em = JPAUtil.getEntityManager();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void insertToTable() {
        System.out.println("Enter the ID of the country");
        int insertToTableCountryID = scanner.nextInt();

        // A scanner.nextLine that will be skipped on purpose. So that Row 28 will be called
        scanner.nextLine();

        System.out.println("Enter the name of the New Ocean you want to insert");
        String insertToTableStringOcean = scanner.nextLine();



        if (insertToTableStringOcean.isEmpty() || insertToTableCountryID <= 0) {
            System.out.println("Empty/Wrong input");
            return;
        }

        inTransaction(entityManager -> {
            Ocean newOcean = new Ocean();
            Country countryID = entityManager.find(Country.class, insertToTableCountryID);
            newOcean.setOceanName(insertToTableStringOcean);
            newOcean.setOceanCountry(countryID);
            entityManager.persist(newOcean);
        });
    }

    @Override
    public void updateTable() {
        System.out.println("Enter the ID number of the ocean you want to update");
        int updateTableStringOceanID = scanner.nextInt();
        System.out.println("Enter the new name/change of the Ocean");
        String updateTableStringOcean = scanner.nextLine();

        if (updateTableStringOcean.isEmpty() || updateTableStringOceanID <= 0) {
            System.out.println("Empty/Invalid input");
            return;
        }

        String query = "UPDATE Ocean SET oceanName = '" + updateTableStringOcean + "' WHERE oceanName = '" + updateTableStringOceanID + "' ";

        inTransaction(entityManager -> {
            var o = entityManager.createQuery(query).executeUpdate();
        });
    }

    @Override
    public void displayTable() {
        String queryDisplay = "SELECT o FROM Ocean o";
        inTransaction(entityManager -> {
            var o = entityManager.createQuery(queryDisplay, Ocean.class)
                    .getResultList();
            o.forEach(ocean -> {
                System.out.println("CountryID: " + ocean.getOceanCountry().getId());
                System.out.println("OceanID: " + ocean.getId());
                System.out.println("OceanName: " + ocean.getOceanName());
            });
        });
    }

    @Override
    public void deleteRowInTable() {
        System.out.println("Enter the name of the Ocean you want to delete");
        String deleteRowInTableOcean = scanner.next();

        if (deleteRowInTableOcean.isEmpty()) {
            System.out.println("Empty/Invalid input");
            return;
        }

        String query = "DELETE FROM Ocean WHERE oceanName = '" + deleteRowInTableOcean + "' ";
        inTransaction(entityManager -> {
            var o = entityManager.createQuery(query).executeUpdate();
        });
    }
}

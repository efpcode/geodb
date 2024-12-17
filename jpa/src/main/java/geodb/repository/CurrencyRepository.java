package geodb.repository;

import geodb.Crudable;
import geodb.entity.Currency;
import jakarta.persistence.TypedQuery;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static geodb.JPAUtil.inTransaction;

public class CurrencyRepository implements Crudable {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void insertToTable() {

    }

    @Override
    public void updateTable() {
        Pattern isAlpha = Pattern.compile("[a-zA-Z ]+");
        displayTable();

        System.out.println("Enter the ID of the currency you want to update: ");
        Integer id = Integer.parseInt(scanner.nextLine());
        if (id <= 0) {
            throw new InputMismatchException("ID must be a positive number greater than 0");
        }


        System.out.println("Enter the currency name to be updated: ");
        String currencyName = scanner.nextLine();
        if ((currencyName == null) || currencyName.isBlank() || !isAlpha.matcher(currencyName).matches()) {
            throw  new InputMismatchException("The currency must not be blank and only alpha characters are allowed");
        }

        inTransaction(entityManager -> {
            Currency currency = entityManager.find(Currency.class, id);
            if (currency != null) {
                currency.setCurrencyName(currencyName);
            }
        });
        displayTable();
        System.out.println("Press enter to continue");
        scanner.nextLine();

    }

    @Override
    public void deleteRowInTable() {

    }

    @Override
    public void displayTable() {
        System.out.println("Currency Table\n\n");
        System.out.println("Currency ID  |  Currency Name");
        inTransaction(entityManager -> {
            TypedQuery<Currency> query = entityManager.createQuery("SELECT c FROM Currency c", Currency.class);
            List<Currency> rows = query.getResultList();
            rows.stream().map(n -> n.getId() + ".  |  " + n.getCurrencyName()).forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        CurrencyRepository repository = new CurrencyRepository();
        repository.updateTable();
    }


}

package geodb.repository;

import geodb.Crudable;
import jakarta.persistence.TypedQuery;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static geodb.JPAUtil.inTransaction;

public class CurrencyRepository implements Crudable {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void insertToTable() {
        displayTable();
        Currency currency = new Currency();
        System.out.println("Enter the name of the currency you want to add: ");
        String newCurrencyName = scanner.nextLine();
        var isOnlyAlpha = isAlpha(newCurrencyName);
        if (!newCurrencyName.isEmpty() || isOnlyAlpha) {
            currency.setCurrencyName(newCurrencyName);
            try {
                inputInsertToTable(currency);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        displayTable();
        continuePrompt();

    }

    private static void inputInsertToTable(Currency currency) {
        inTransaction(entityManager -> entityManager.persist(currency));
    }


    /**
     * isAlpha
     * <p>The method checks with regex pattern matching if 'currencyName' contains characters from a to z or A to Z
     * and regular space.
     * </p>
     *
     * @param currencyName String passed for evaluation against regex.
     * @return boolean 'true' if regex pattern is matched, otherwise 'false'.
     */
    private boolean isAlpha(String currencyName) {
        Pattern isAlpha = Pattern.compile("[a-zA-Z ]+");
        return isAlpha.matcher(currencyName).matches();

    }

    private void continuePrompt() {
        System.out.println("Press enter to continue");
        scanner.nextLine();
    }

    @Override
    public void updateTable() {
        displayTable();
        System.out.println("Enter the ID of the currency you want to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (id <= 0) {
            throw new InputMismatchException("ID must be a positive number greater than 0");
        }

        System.out.println("Enter the currency name to be updated: ");
        String currencyName = scanner.nextLine();
        boolean isOnlyAlpha = isAlpha(currencyName);
        if ((currencyName == null) || currencyName.isBlank() || !isOnlyAlpha) {
            throw new InputMismatchException("The currency must not be blank and only alpha characters are allowed");
        }

        inputUpdateTable(id, currencyName);
        displayTable();
        continuePrompt();

    }

    private static void inputUpdateTable(int id, String currencyName) {
        inTransaction(entityManager -> {
            Currency currency = entityManager.find(Currency.class, id);
            if (currency != null) {
                currency.setCurrencyName(currencyName);
            }
        });
    }

    @Override
    public void deleteRowInTable() {
        displayTable();
        System.out.println("Enter the ID of the currency you want to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (id <= 0) {
            throw new InputMismatchException("ID must be a positive number greater than 0");
        }
        inputDeletionOfRow(id);
        displayTable();
        continuePrompt();

    }

    private static void inputDeletionOfRow(int id) {
        inTransaction(entityManager -> {
            Currency currency = entityManager.find(Currency.class, id);
            if (currency != null) {
                entityManager.remove(currency);
            }
        });
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

}

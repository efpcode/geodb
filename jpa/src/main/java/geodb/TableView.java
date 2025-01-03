package geodb;

import geodb.repository.*;

import java.util.List;

public class TableView implements Viewable{
    @Override
    public Viewable goToView(String viewName) {
        try {
            var table = switch (viewName) {
                case "City" -> new CityRepository();
                case "Continent" -> new ContinentRepository();
                case "Country" -> new CountryRepository();
                case "Currency" -> new CurrencyRepository();
                case "LandMark" -> new LandMarkRepository();
                case "Ocean" -> new OceanRepository();
                default -> throw new IllegalArgumentException("Unknown view name " + viewName);
            };
            return new CrudView(table);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this;
        }
    }

    @Override
    public Viewable goBackToView() {
        return new RootView();

    }

    @Override
    public Viewable goBackToMainView() {
        return new RootView();

    }

    @Override
    public void goToExitView() {
        System.exit(0);

    }

    @Override
    public void promptView() {
        String banner = """
                  _    _                                                                _   _     _____            _             _                              _______           _       _             \s
                 | |  | |                            /\\                         /\\     | | | |   |  __ \\          | |           | |                            |__   __|         | |     | |            \s
                 | |__| |   ___   _ __    ___       /  \\     _ __    ___       /  \\    | | | |   | |  | |   __ _  | |_    __ _  | |__     __ _   ___    ___       | |      __ _  | |__   | |   ___   ___\s
                 |  __  |  / _ \\ | '__|  / _ \\     / /\\ \\   | '__|  / _ \\     / /\\ \\   | | | |   | |  | |  / _` | | __|  / _` | | '_ \\   / _` | / __|  / _ \\      | |     / _` | | '_ \\  | |  / _ \\ / __|
                 | |  | | |  __/ | |    |  __/    / ____ \\  | |    |  __/    / ____ \\  | | | |   | |__| | | (_| | | |_  | (_| | | |_) | | (_| | \\__ \\ |  __/      | |    | (_| | | |_) | | | |  __/ \\__ \\
                 |_|  |_|  \\___| |_|     \\___|   /_/    \\_\\ |_|     \\___|   /_/    \\_\\ |_| |_|   |_____/   \\__,_|  \\__|  \\__,_| |_.__/   \\__,_| |___/  \\___|      |_|     \\__,_| |_.__/  |_|  \\___| |___/
                """;

        System.out.println(banner);

        allOptions();

    }

    public void allOptions() {
        List<String> tables = List.of("City", "Continent", "Country", "Currency", "Land Mark", "Ocean");
        List<String> options = List.of("Teleport to Main Menu", "Exit", "Go Back to Previous Menu");

        System.out.println("Type a Table Name Below: ");
        for (String table : tables) {
            System.out.println("\t" + table);
        }

        System.out.println(".-.-".repeat(9));

        System.out.println("\nOr Select a Navigation Option Below\n");
        for (int i = 1; i <= options.size(); i++) {
            System.out.println(i + ". " + options.get(i - 1));

        }
    }

}

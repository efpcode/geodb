package geodb.view;

import geodb.repository.*;

import java.util.List;

public class TableView implements Viewable {
    @Override
    public Viewable goToView(String viewName) {
        try {
            var table = switch (viewName) {
                case "City" -> new CityRepository();
                case "Continent" -> new ContinentRepository();
                case "Country" -> new CountryRepository();
                case "Currency" -> new CurrencyRepository();
                case "Land Mark" -> new LandMarkRepository();
                case "Ocean" -> new OceanRepository();
                default -> throw new ViewNotImplementedYet("Unknown view name " + viewName);
            };
            return new CrudView(table);
        }catch (ViewNotImplementedYet e) {
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
        System.out.println("\n");
        allOptions();
        ViewUtil.defaultOptions();

    }

    public void allOptions() {
        List<String> tables = List.of("City", "Continent", "Country", "Currency", "Land Mark", "Ocean");

        System.out.println("Type a Table Name Below: ");
        for (String table : tables) {
            System.out.println("\t" + table);
        }

    }

}

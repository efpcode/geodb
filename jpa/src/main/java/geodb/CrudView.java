package geodb;

import java.util.List;

public class CrudView implements Viewable {
    Crudable table;

    CrudView( Crudable table ) {
        this.table = table;
    }

    @Override
    public Viewable goToView(String viewName) {
        switch (viewName) {
            case "Insert" -> this.table.insertToTable();
            case "Update" -> this.table.updateTable();
            case "Delete" -> this.table.deleteRowInTable();
            case "Display" -> this.table.displayTable();
        }
        return this;

    }

    @Override
    public Viewable goBackToView() {
        return new TableView();
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
                  _    _                                                                _   _      _____   _____    _    _   _____       ____            _     _                      \s
                 | |  | |                            /\\                         /\\     | | | |    / ____| |  __ \\  | |  | | |  __ \\     / __ \\          | |   (_)                     \s
                 | |__| |   ___   _ __    ___       /  \\     _ __    ___       /  \\    | | | |   | |      | |__) | | |  | | | |  | |   | |  | |  _ __   | |_   _    ___    _ __    ___\s
                 |  __  |  / _ \\ | '__|  / _ \\     / /\\ \\   | '__|  / _ \\     / /\\ \\   | | | |   | |      |  _  /  | |  | | | |  | |   | |  | | | '_ \\  | __| | |  / _ \\  | '_ \\  / __|
                 | |  | | |  __/ | |    |  __/    / ____ \\  | |    |  __/    / ____ \\  | | | |   | |____  | | \\ \\  | |__| | | |__| |   | |__| | | |_) | | |_  | | | (_) | | | | | \\__ \\
                 |_|  |_|  \\___| |_|     \\___|   /_/    \\_\\ |_|     \\___|   /_/    \\_\\ |_| |_|    \\_____| |_|  \\_\\  \\____/  |_____/     \\____/  | .__/   \\__| |_|  \\___/  |_| |_| |___/
                                                                                                                                                | |                                   \s
                                                                                                                                                |_|                                   \s
                """;
        System.out.println(banner);
        allOptions();
    }
    public void allOptions() {
        List<String> cruds = List.of("Insert", "Update", "Delete", "Display");
        List<String> options = List.of("Teleport to Main Menu", "Exit", "Go Back to Previous Menu");

        System.out.println("Type Any of the Operations Available Below: ");
        for (String crud : cruds) {
            System.out.println("\t" + crud);
        }

        System.out.println(".-.-".repeat(10));

        System.out.println("\nOr Select a Navigation Option Below\n");
        for (int i = 1; i <= options.size(); i++) {
            System.out.println(i + ". " + options.get(i - 1));

        }
    }

}

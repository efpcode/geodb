package geodb;

import java.util.List;

public class RootView implements Viewable {
    @Override
    public Viewable goToView(String viewName) {
        try {

        return switch (viewName) {
            case "Quiz" -> new QuizView();
            case "Table" -> new TableView();
            default -> throw new IllegalArgumentException("Unknown view: " + viewName);
        };
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return this;
        }

    }

    @Override
    public Viewable goBackToView() {
        return this;

    }

    @Override
    public Viewable goBackToMainView() {
        return this;

    }

    @Override
    public void goToExitView() {
        System.exit(0);

    }

    @Override
    public void promptView() {
        String banner = """
                 __          __         _                                       _                _____                  _____    ____ \s
                 \\ \\        / /        | |                                     | |              / ____|                |  __ \\  |  _ \\\s
                  \\ \\  /\\  / /    ___  | |   ___    ___    _ __ ___     ___    | |_    ___     | |  __    ___    ___   | |  | | | |_) |
                   \\ \\/  \\/ /    / _ \\ | |  / __|  / _ \\  | '_ ` _ \\   / _ \\   | __|  / _ \\    | | |_ |  / _ \\  / _ \\  | |  | | |  _ <\s
                    \\  /\\  /    |  __/ | | | (__  | (_) | | | | | | | |  __/   | |_  | (_) |   | |__| | |  __/ | (_) | | |__| | | |_) |
                     \\/  \\/      \\___| |_|  \\___|  \\___/  |_| |_| |_|  \\___|    \\__|  \\___/     \\_____|  \\___|  \\___/  |_____/  |____/\s
                """;
        System.out.println(banner);

        System.out.println("Available options are: ");
        optionsAvailable();



    }

    public void optionsAvailable() {
        List<String> options = List.of("Type: 'Quiz' or 'Table' to navigate to menu",
                "Teleport to Main Menu", "Exit", "Go Back to Previous Menu");
        System.out.println(options.get(0));
        System.out.println();

        System.out.println("\nOr Select an Option Below:\n");

        for (int i = 1; i < options.size(); i++) {
            System.out.println(i + ". " + options.get(i));

        }

    }

}

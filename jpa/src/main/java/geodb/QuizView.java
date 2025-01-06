package geodb;

import java.util.List;

public class QuizView implements Viewable {
    @Override
    public Viewable goToView(String viewName) {
        return this;

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
                   ____    _    _   _____   ______  ______  ______    _____               __      __             _____   _                   ____    _        ______\s
                  / __ \\  | |  | | |_   _| |___  / |___  / |  ____|  / ____|       /\\     \\ \\    / /     /\\     |_   _| | |          /\\     |  _ \\  | |      |  ____|
                 | |  | | | |  | |   | |      / /     / /  | |__    | (___        /  \\     \\ \\  / /     /  \\      | |   | |         /  \\    | |_) | | |      | |__  \s
                 | |  | | | |  | |   | |     / /     / /   |  __|    \\___ \\      / /\\ \\     \\ \\/ /     / /\\ \\     | |   | |        / /\\ \\   |  _ <  | |      |  __| \s
                 | |__| | | |__| |  _| |_   / /__   / /__  | |____   ____) |    / ____ \\     \\  /     / ____ \\   _| |_  | |____   / ____ \\  | |_) | | |____  | |____\s
                  \\___\\_\\  \\____/  |_____| /_____| /_____| |______| |_____/    /_/    \\_\\     \\/     /_/    \\_\\ |_____| |______| /_/    \\_\\ |____/  |______| |______|
                                                                                                                                                                    \s
                                                                                                                                                                    \s
                """;
        System.out.println(banner);
        System.out.println("\n");
        optionsAvailable();
        ViewUtil.defaultOptions();


    }

    public void optionsAvailable() {
        String option ="Type: Start to run quiz";
        System.out.println(option);

        }


}

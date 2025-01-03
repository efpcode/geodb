package geodb;
import geodb.ViewNotImplementedYet.*;

public class RootView implements Viewable {
    @Override
    public void goToView(String viewName) {

    }

    @Override
    public void goBackToView() {

    }

    @Override
    public void goBackToMainView() {

    }

    @Override
    public void goToExitView() {

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



    }

    public void optionsAvailable(){



    }

    public static void main(String[] args) {
        RootView v = new RootView();
        v.promptView();
    }
}

package geodb;

import java.util.List;

public class ViewUtil {

    public static void defaultOptions(){
        List<String> options = List.of("Teleport to Main Menu", "Exit or x", "Go Back to Previous Menu");

        System.out.println(".-.-".repeat(9));

        System.out.println("\nOr Select a Navigation Option Below\n");
        for (int i = 1; i <= options.size(); i++) {
            System.out.println(i + ". " + options.get(i - 1));

        }

    }
}

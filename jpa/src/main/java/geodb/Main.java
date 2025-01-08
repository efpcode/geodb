package geodb;

import geodb.view.RootView;
import geodb.view.Viewable;

import java.util.Scanner;

public class Main {
    Viewable currentView;

    public Viewable getCurrentView() {
        return currentView;
    }

    public void setCurrentView(Viewable currentView) {
        this.currentView = currentView;
    }

    Main(Viewable currentView) {
        this.currentView = currentView;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main(new RootView());
        boolean isNotLoop = false;
        while (!isNotLoop) {
            main.currentView.promptView();
            System.out.printf(">>> ");

            var inputUser = scanner.nextLine();

            switch (inputUser) {
                case "x" -> isNotLoop = true;
                case "1" -> main.setCurrentView(main.currentView.goBackToMainView());
                case "2" -> main.currentView.goToExitView();
                case "3" -> main.setCurrentView(main.currentView.goBackToView());
                default -> main.setCurrentView(main.currentView.goToView(inputUser));

            }


        }

    }


}

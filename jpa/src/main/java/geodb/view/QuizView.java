package geodb.view;


import geodb.GeoQuiz;

public class QuizView implements Viewable {
    GeoQuiz quiz;

    public QuizView(GeoQuiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public Viewable goToView(String viewName) {
        try{
            switch (viewName){
                case "Start" -> this.quiz.startQuiz();
                default -> throw new ViewNotImplementedYet("");
            }
            return this;

        } catch ( ViewNotImplementedYet e) {
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
                   _  _        _____   ______    ____      _____    ____       ____    _    _   _____   ______      _  _  \s
                 _| || |_     / ____| |  ____|  / __ \\    |  __ \\  |  _ \\     / __ \\  | |  | | |_   _| |___  /    _| || |_\s
                |_  __  _|   | |  __  | |__    | |  | |   | |  | | | |_) |   | |  | | | |  | |   | |      / /    |_  __  _|
                 _| || |_    | | |_ | |  __|   | |  | |   | |  | | |  _ <    | |  | | | |  | |   | |     / /      _| || |_\s
                |_  __  _|   | |__| | | |____  | |__| |   | |__| | | |_) |   | |__| | | |__| |  _| |_   / /__    |_  __  _|
                  |_||_|      \\_____| |______|  \\____/    |_____/  |____/     \\___\\_\\  \\____/  |_____| /_____|     |_||_| \s
                                                                                                                          \s
                                                                                                                          \s 
                """;
        System.out.println(banner);
        System.out.println("\n");
        viewOptions();
        ViewUtil.defaultOptions();


    }

    public void viewOptions() {
        String option ="Type: Start to run quiz";
        System.out.println(option);

        }


}

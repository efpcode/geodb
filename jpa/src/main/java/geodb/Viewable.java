package geodb;

public interface Viewable {
    void goToView(String viewName);
    void goBackToView();
    void goBackToMainView();
    void goToExitView();
    void promptView();
}

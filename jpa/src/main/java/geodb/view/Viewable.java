package geodb.view;

public interface Viewable {
    Viewable goToView(String viewName);
    Viewable goBackToView();
    Viewable goBackToMainView();
    void goToExitView();
    void promptView();
}

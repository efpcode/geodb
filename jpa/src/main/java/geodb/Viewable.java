package geodb;

public interface Viewable {
    void goToView(String viewName);
    void goBackToView(String viewName);
    void promptView();
}

package geodb;
import geodb.repository.*;

public class CrudView implements Viewable {
    Crudable table;

    CrudView( Crudable table ) {
        this.table = table;
    }

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

    }
}

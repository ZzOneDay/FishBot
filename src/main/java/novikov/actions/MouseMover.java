package novikov.actions;

import java.awt.*;

public interface MouseMover {

    /**
     * Facade layer
     * <p>
     * Moving mouse from screen. Method is used random for changing finish point
     *
     * @param start  where is mouse now
     * @param finish where is a hook
     */
    boolean move(Point start, Point finish);
}

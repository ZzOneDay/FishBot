package novikov.entity.ways;

import java.awt.*;
import java.util.List;

public interface Way {


    /**
     * Every way has to have start and endpoint in list of points.
     *
     * @param startPoint start
     * @param finishPoint finish
     * @return way, that started from startPoint to finishPoint
     */
    List<Point> getWay(Point startPoint, Point finishPoint);
}

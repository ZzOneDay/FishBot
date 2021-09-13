package novikov.services.randoms;

import novikov.entity.equations.LineEquation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GenerateRandomPoints {

    @Autowired
    private GenerateValue generateValue;

    public List<Point> generateRandomPoints(Point startPoint, Point finishPoint, int count) {
        return generatePoints(startPoint, finishPoint, count);
    }

    public List<Point> generateRandomPointsAndSorted(final Point startPoint, Point finishPoint, int count) {
        List<Point> points = generatePoints(startPoint, finishPoint, count);
        points.sort((point1, point2) ->
                (int) (LineEquation.getDistanceBetween2Points(startPoint, point1) - LineEquation.getDistanceBetween2Points(startPoint, point2)));
        return points;
    }

    private List<Point> generatePoints(Point startPoint, Point finishPoint, int count) {
        int maxY = (int) (Math.max(startPoint.getY(), finishPoint.getY()));
        int maxX = (int) (Math.max(startPoint.getX(), finishPoint.getX()));

        int minY = (int) (Math.min(startPoint.getY(), finishPoint.getY()));
        int minX = (int) (Math.min(startPoint.getX(), finishPoint.getX()));


        List<Point> pointList = new ArrayList<>(count + 2);
        for (int i = 0; i < count; i++) {
            int indexX = generateValue.getRandomValue(minX, maxX, Math.random());
            int indexY = generateValue.getRandomValue(minY, maxY, Math.random());
            pointList.add(new Point(indexX, indexY));
        }
        return pointList;
    }
}

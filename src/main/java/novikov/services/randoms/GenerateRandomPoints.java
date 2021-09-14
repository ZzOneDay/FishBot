package novikov.services.randoms;

import novikov.entity.equations.LineEquation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GenerateRandomPoints {
    private static final Logger LOG = LogManager.getLogger(GenerateRandomPoints.class);

    @Value("${service.generate.randomPoints.delta}")
    private int delta; //SENSITIVITY

    @Autowired
    private GenerateValue generateValue;

    public List<Point> generateRandomPoints(Point startPoint, Point finishPoint, int count) {
        LOG.info("Start generateRandomPoint from {}, to {}; Count need to be {}", startPoint, finishPoint, count);
        return generatePoints(startPoint, finishPoint, count);
    }

    public List<Point> generateRandomPointsAndSorted(final Point startPoint, Point finishPoint, int count) {
        LOG.info("Start generateRandomPoint SORTED from {}, to {}; Count need to be {}", startPoint, finishPoint, count);
        List<Point> points = generatePoints(startPoint, finishPoint, count);
        if (startPoint.getX() > finishPoint.getX()) {
            points.sort((point1, point2) -> (int) point2.getX() - (int) point1.getX());
        } else {
            points.sort(Comparator.comparingInt(point -> (int) point.getX()));
        }

        return cleanedPointList(startPoint, finishPoint, points);
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
        LOG.info("Generated {} points", pointList.size());
        return pointList;
    }

    private List<Point> cleanedPointList(Point startPoint, Point finishPoint, List<Point> points) {
        List<Point> cleanList = new ArrayList<>(points.size());
        LineEquation lineEquation = LineEquation.createLineOf2Points(startPoint, finishPoint);

        for (Point point : points) {
            int indexY = lineEquation.getY((int) point.getX());
            if (Math.abs(indexY - point.getY()) < delta) {
                cleanList.add(point);
            }
        }
        LOG.info("Finished generateRandomPoint. Cleaned from List {} points, sensitivity < {}",
                points.size() - cleanList.size(), delta);
        return cleanList;
    }
}

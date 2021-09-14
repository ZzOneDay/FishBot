package novikov.entity.ways;

import novikov.entity.equations.LineEquation;
import novikov.services.randoms.GenerateRandomPoints;
import novikov.services.randoms.GenerateValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class RandomPointWay implements Way {
    private static final Logger LOG = LogManager.getLogger(RandomPointWay.class);

    @Autowired
    private GenerateRandomPoints generateRandomPoints;

    @Autowired
    private GenerateValue generateValue;

    @Value("${service.generate.randomPoints.min}")
    private int randomPointsMin;

    @Value("${service.generate.randomPoints.max}")
    private int randomPointsMax;

    @Override
    public List<Point> getWay(Point startPoint, Point finishPoint) {
        LOG.info("Start creating Way from {} to {}", startPoint, finishPoint);
        int countPoints = generateValue.getRandomValue(randomPointsMin, randomPointsMax, Math.random());
        List<Point> listOfPoints = generateRandomPoints.generateRandomPointsAndSorted(startPoint, finishPoint, countPoints);
        listOfPoints.add(0, startPoint);
        listOfPoints.add(finishPoint);
        List<Point> way = new LinkedList<>();
        Point temp = startPoint;
        for (Point point : listOfPoints) {
            way.addAll(way.size(), getWayFrom2Points(temp, point));
            temp = point;
        }
        LOG.info("Finish creating Way from {} to {}", startPoint, finishPoint);
        return way;
    }

    private List<Point> getWayFrom2Points(Point point1, Point point2) {
        List<Point> way = new ArrayList<>((int) (Math.abs(point2.getX() - point1.getX())));
        LineEquation lineEquation = LineEquation.createLineOf2Points(point1, point2);
        // ---- >
        if (point1.getX() < point2.getX()) {
            for (int x = (int) point1.getX(); x < point2.getX(); x++) {
                way.add(new Point(x, lineEquation.getY(x)));
            }
        } else {
            // <--------
            for (int x = (int) point2.getX(); x > point1.getX(); x--) {
                way.add(new Point(x, lineEquation.getY(x)));
            }
        }
        return way;
    }
}

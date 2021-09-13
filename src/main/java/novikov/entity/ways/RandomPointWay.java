package novikov.entity.ways;

import novikov.services.randoms.GenerateRandomPoints;
import novikov.services.randoms.GenerateValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class RandomPointWay implements Way  {

    @Autowired
    private GenerateRandomPoints generateRandomPoints;

    @Autowired
    private GenerateValue generateValue;

    @Override
    public List<Point> getWay(Point startPoint, Point finishPoint) {
        int countPoints = generateValue.getRandomValue(5,15, Math.random());
        List<Point> listOfPoints = generateRandomPoints.generateRandomPoints(startPoint, finishPoint, countPoints);
        listOfPoints.add(0, startPoint);
        listOfPoints.add(finishPoint);
        return listOfPoints;
    }
}

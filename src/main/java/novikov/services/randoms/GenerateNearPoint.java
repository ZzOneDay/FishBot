package novikov.services.randoms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class GenerateNearPoint {

    @Autowired
    private GenerateValue generateValue;

    @Value("${service.generate.nearPoint.deltaX}")
    private int deltaX;

    @Value("${service.generate.nearPoint.deltaY}")
    private int deltaY;

    public Point createNearPoint(Point point) {
        int indexX = (int) point.getX();
        int indexY = (int) point.getY();

        indexX = generateValue.getRandomValue(indexX - deltaX, indexX + deltaX, Math.random());
        indexY = generateValue.getRandomValue(indexY - deltaY, indexY + deltaY, Math.random());
        return new Point(indexX, indexY);
    }
}

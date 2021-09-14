package novikov.actions.impl;

import novikov.actions.MouseMover;
import novikov.entity.ways.Way;
import novikov.services.randoms.GenerateNearPoint;
import novikov.services.randoms.GenerateValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class MouseMoverImpl implements MouseMover {
    private static final Logger LOG = LogManager.getLogger(MouseMoverImpl.class);

    @Autowired
    private Way randomPointWay;

    @Autowired
    private GenerateValue generateValue;

    @Autowired
    private GenerateNearPoint generateNearPoint;

    @Autowired
    private Robot robot;

    @Value("${mouse.moving.speedTime.from}")
    private int speedTimeFrom;

    @Value("${mouse.moving.speedTime.to}")
    private int speedTimeTo;

    @Override
    public void move(Point start, Point finish) {
        LOG.info("Start moving, from {} to {}", start, finish);
        List<Point> way = randomPointWay.getWay(start, finish);
        setNewFinishPointInWay(way, generateNearPoint.createNearPoint(finish));
        for (Point point : way) {
            try {
                Thread.sleep(generateValue.getRandomValue(speedTimeFrom, speedTimeTo, Math.random()));
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
            robot.mouseMove((int) point.getX(), (int) point.getY());
        }
        LOG.info("Finish moving, from {} to {}", start, finish);
    }

    private void setNewFinishPointInWay(List<Point> way, Point newFinish) {
        way.set(way.size() - 1, newFinish);
    }
}

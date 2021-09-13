package novikov.entity.ways;

import junit.framework.TestCase;
import novikov.configuration.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.awt.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class RandomPointWayTest extends TestCase {

    @Autowired
    private Way randomPointWay;

    @Test
    public void testCreateWayCheckStartAndFinish() {
        Point point1 = new Point(400,800);
        Point point2 = new Point(800,1440);

        List<Point> points = randomPointWay.getWay(point1, point2);
        assertEquals(point1, points.get(0));
        assertEquals(point2, points.get(points.size() - 1));
        assertTrue(points.size() > 5);
    }

}
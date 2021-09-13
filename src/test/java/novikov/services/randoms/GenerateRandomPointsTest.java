package novikov.services.randoms;

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

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class GenerateRandomPointsTest extends TestCase {

    @Autowired
    private GenerateRandomPoints generateRandomPoints;

    @Test
    public void testGetRandomPoints() {
        Point point1 = new Point(400,800);
        Point point2 = new Point(800,1440);

        List<Point> list = generateRandomPoints.generateRandomPoints(point1,point2, 20);
        assertEquals(20, list.size());
        assertNotEquals(list.get(0), list.get(5));
    }
}
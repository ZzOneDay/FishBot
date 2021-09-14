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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class GenerateNearPointTest extends TestCase {

    @Autowired
    private GenerateNearPoint generateNearPoint;

    @Test
    public void testGenerateNewPoint() {
        Point point = new Point(100, 100);
        Point newPoint = generateNearPoint.createNearPoint(point);
        assertNotSame(point, newPoint);
    }
}
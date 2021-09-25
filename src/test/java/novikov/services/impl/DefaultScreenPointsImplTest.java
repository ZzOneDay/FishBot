package novikov.services.impl;

import junit.framework.TestCase;
import novikov.configuration.AppConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.awt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class, loader = AnnotationConfigContextLoader.class)
public class DefaultScreenPointsImplTest extends TestCase {

    @Autowired
    DefaultScreenPointsImpl defaultScreenPoints;

    @Test
    public void testGetPoint1() {
        Point point1 = defaultScreenPoints.getPoint1();
        assertEquals(512.0, point1.getX());
        assertEquals(240.0, point1.getY());
    }

    @Test
    public void testGetPoint2() {
        Point point2 = defaultScreenPoints.getPoint2();
        assertEquals(2048.0, point2.getX());
        assertEquals(960.0, point2.getY());
    }
}
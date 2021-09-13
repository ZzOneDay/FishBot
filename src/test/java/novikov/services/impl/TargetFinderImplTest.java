package novikov.services.impl;

import junit.framework.TestCase;
import novikov.configuration.AppConfig;
import novikov.entity.resolutions.ScreenResolution;
import novikov.services.TargetFinder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class TargetFinderImplTest extends TestCase {

    @Autowired
    TargetFinder targetFinder;

    @Autowired
    ScreenResolution screenResolution;

    @Test
    public void testTestGetTargetScreenshot_1Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_1.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point1 = new Point(400, 350);
        Point point2 = new Point(2100, 900);
        Point point = targetFinder.getTarget(image, point1, point2);

        assertEquals(1366.0, point.getX(), 3);
        assertEquals(506.0, point.getY(), 3);
    }

    @Test
    public void testTestGetTargetScreenshot_2Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_2.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point1 = new Point(400, 350);
        Point point2 = new Point(2100, 900);
        Point point = targetFinder.getTarget(image, point1, point2);

        assertEquals(1435.0, point.getX(), 3);
        assertEquals(541.0, point.getY(), 3);
    }

    @Test
    public void testTestGetTargetScreenshot_3Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_3.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point1 = new Point(400, 350);
        Point point2 = new Point(2100, 900);
        Point point = targetFinder.getTarget(image, point1, point2);

        assertEquals(1223, point.getX(), 3);
        assertEquals(463.0, point.getY(), 3);
    }

    @Test
    public void testTestGetTargetScreenshot_4Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_4.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point1 = new Point(400, 350);
        Point point2 = new Point(2100, 900);
        Point point = targetFinder.getTarget(image, point1, point2);

        assertEquals(1335.0, point.getX(), 3);
        assertEquals(427.0, point.getY(), 3);
    }

    @Test
    public void testTestGetTargetScreenshot_5Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_5.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point1 = new Point(400, 350);
        Point point2 = new Point(2100, 900);
        Point point = targetFinder.getTarget(image, point1, point2);

        assertEquals(1300.0, point.getX(), 3);
        assertEquals(452.0, point.getY(), 3);
    }

    @Test
    public void testTestGetTargetResolution_Test() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File img = new File(Objects.requireNonNull(classLoader.getResource("Screenshot_1.jpg")).getFile());
        BufferedImage image = ImageIO.read(img);
        Point point = targetFinder.getTarget(image, screenResolution.getTargetFramePoint1(), screenResolution.getTargetFramePoint2());

        assertEquals(1366.0, point.getX(), 3);
        assertEquals(506.0, point.getY(), 3);
    }
}
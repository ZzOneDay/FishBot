package novikov.services.impl;

import junit.framework.TestCase;
import novikov.configuration.AppConfig;
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

    @Test
    public void testGetTargetWithBackground1() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File background = new File(Objects.requireNonNull(classLoader.getResource("withBackground/back1.jpg")).getFile());
        BufferedImage backgroundImage = ImageIO.read(background);

        File fish = new File(Objects.requireNonNull(classLoader.getResource("withBackground/fish1.jpg")).getFile());
        BufferedImage fishImage = ImageIO.read(fish);
        int width = fishImage.getWidth();
        int height = fishImage.getHeight();

        Point point1 = new Point((width / 6), (height / 7));
        Point point2 = new Point((width / 8 * 6), (height / 8 * 5));
        Point point = targetFinder.getTarget(fishImage, backgroundImage, point1, point2);

        markPoint(point, fishImage);

        assertEquals(1228, point.getX(), 10);
        assertEquals(709.0, point.getY(), 10);
    }

    @Test
    public void testGetTargetWithBackground2() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File background = new File(Objects.requireNonNull(classLoader.getResource("withBackground/back2.jpg")).getFile());
        BufferedImage backgroundImage = ImageIO.read(background);

        File fish = new File(Objects.requireNonNull(classLoader.getResource("withBackground/fish2.jpg")).getFile());
        BufferedImage fishImage = ImageIO.read(fish);
        int width = fishImage.getWidth();
        int height = fishImage.getHeight();

        Point point1 = new Point((width / 6), (height / 7));
        Point point2 = new Point((width / 8 * 6), (height / 8 * 5));
        Point point = targetFinder.getTarget(fishImage, backgroundImage, point1, point2);

        markPoint(point, fishImage);

        assertEquals(1215.0, point.getX(), 10);
        assertEquals(792.0, point.getY(), 10);
    }

    @Test
    public void testGetTargetWithBackground3() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File background = new File(Objects.requireNonNull(classLoader.getResource("withBackground/back3.jpg")).getFile());
        BufferedImage backgroundImage = ImageIO.read(background);

        File fish = new File(Objects.requireNonNull(classLoader.getResource("withBackground/fish3.jpg")).getFile());
        BufferedImage fishImage = ImageIO.read(fish);
        int width = fishImage.getWidth();
        int height = fishImage.getHeight();

        Point point1 = new Point((width / 6), (height / 7));
        Point point2 = new Point((width / 8 * 6), (height / 8 * 5));
        Point point = targetFinder.getTarget(fishImage, backgroundImage, point1, point2);

        markPoint(point, fishImage);

        assertEquals(1395, point.getX(), 10);
        assertEquals(508.0, point.getY(), 10);
    }

    @Test
    public void testGetTargetWithBackground4() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File background = new File(Objects.requireNonNull(classLoader.getResource("withBackground/back4.jpg")).getFile());
        BufferedImage backgroundImage = ImageIO.read(background);

        File fish = new File(Objects.requireNonNull(classLoader.getResource("withBackground/fish4.jpg")).getFile());
        BufferedImage fishImage = ImageIO.read(fish);
        int width = fishImage.getWidth();
        int height = fishImage.getHeight();

        Point point1 = new Point((width / 6), (height / 7));
        Point point2 = new Point((width / 8 * 6), (height / 8 * 5));
        Point point = targetFinder.getTarget(fishImage, backgroundImage, point1, point2);

        markPoint(point, fishImage);

        assertEquals(1276, point.getX(), 10);
        assertEquals(629.0, point.getY(), 10);
    }

    @Test
    public void testGetTargetWithBackground5() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File background = new File(Objects.requireNonNull(classLoader.getResource("withBackground/back5.jpg")).getFile());
        BufferedImage backgroundImage = ImageIO.read(background);

        File fish = new File(Objects.requireNonNull(classLoader.getResource("withBackground/fish5.jpg")).getFile());
        BufferedImage fishImage = ImageIO.read(fish);
        int width = fishImage.getWidth();
        int height = fishImage.getHeight();

        Point point1 = new Point((width / 6), (height / 7));
        Point point2 = new Point((width / 8 * 6), (height / 8 * 5));
        Point point = targetFinder.getTarget(fishImage, backgroundImage, point1, point2);

        markPoint(point, fishImage);

        assertEquals(1270, point.getX(), 10);
        assertEquals(681.0, point.getY(), 10);
    }

    private void markPoint(Point point, BufferedImage image) {
        for (int i = -50; i < 50; i++) {
            image.setRGB((int) point.getX() + i, (int) point.getY() - 1, Color.RED.getRGB());
            image.setRGB((int) point.getX() + i, (int) point.getY(), Color.RED.getRGB());
            image.setRGB((int) point.getX() + i, (int) point.getY() + 1, Color.RED.getRGB());
        }

        for (int i = -50; i < 50; i++) {
            image.setRGB((int) point.getX() - 1, (int) point.getY() + i, Color.RED.getRGB());
            image.setRGB((int) point.getX(), (int) point.getY() + i, Color.RED.getRGB());
            image.setRGB((int) point.getX() + 1, (int) point.getY() + i, Color.RED.getRGB());
        }
    }
}
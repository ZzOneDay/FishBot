package novikov.input;

import junit.framework.TestCase;
import novikov.configuration.AppConfig;
import novikov.services.DefaultScreenPoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.awt.*;
import java.awt.image.BufferedImage;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class ImageCatcherTest extends TestCase {

    @Autowired
    DefaultScreenPoints defaultScreenPoints;

    @Autowired
    ImageCatcher imageCatcher;

    @Test
    public void testSaveImageIsCorrect() {
        Point point1 = defaultScreenPoints.getPoint1();
        Point point2 = defaultScreenPoints.getPoint2();

        BufferedImage bufferedImage = imageCatcher.getImageFromScreen();

        markPoint(point1, bufferedImage);

        markPoint(point2, bufferedImage);
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
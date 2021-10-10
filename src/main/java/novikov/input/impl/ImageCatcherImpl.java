package novikov.input.impl;

import novikov.input.ImageCatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ImageCatcherImpl implements ImageCatcher {

    @Autowired
    private Robot robot;

    @Override
    public BufferedImage getImageFromScreen() {
        Rectangle rectangleFromSystem = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return robot.createScreenCapture(rectangleFromSystem);
    }
}

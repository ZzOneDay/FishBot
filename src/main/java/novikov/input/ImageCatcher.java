package novikov.input;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageCatcher {

    /**
     * Create screenshot in buffer, return all screen.
     *
     * @return image of screenshot
     */
    BufferedImage getImageFromScreen();

    /**
     * Create screenshot in frame.
     * Position1-------Position
     * |----------------------|
     * Position-------Position2
     *
     * @param position1 point1
     * @param position2 point2
     * @return mini image of screen
     */
    BufferedImage getImageFromScreen(Point position1, Point position2);
}

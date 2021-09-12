package novikov.services;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface TargetFinder {


    /**
     * Find hook from BufferedImage.
     * <p>
     * Method is used colorScopes of redFeather, blueFeather, whiteHook.
     * Every pixel check to scope of colors.
     * If pixel contains special color, it will join to list.
     * Avg of list is avg of Pixels -> point for return.
     *
     * @param bufferedImage image from screen
     * @return point with hook
     */
    Point getTarget(BufferedImage bufferedImage);

    Point getTarget(BufferedImage bufferedImage, Point point1, Point point2);
}

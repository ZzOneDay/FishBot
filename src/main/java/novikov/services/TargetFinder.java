package novikov.services;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Author Pavel Novikov
 * Date 09.10.2021
 */
public interface TargetFinder {

    /**
     * Main Idea of this method.
     * <p>
     * We find different between old image and new image. (version 2.0)
     * <p>
     * Method is used colorScopes of redFeather, blueFeather, whiteHook.
     * Every pixel check to scope of colors.
     * If pixel contains special color, it will join to list.
     * Avg of list is avg of Pixels -> point for return.
     *
     * @param bufferedImage   current image
     * @param backgroundImage old image(without hook)
     * @param point1          size frame1
     * @param point2          size fram2
     * @return point with target
     */
    Point getTarget(BufferedImage bufferedImage, BufferedImage backgroundImage,
                    Point point1, Point point2);
}

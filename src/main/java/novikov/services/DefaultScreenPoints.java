package novikov.services;

import java.awt.*;

/**
 * Default value for points of System property screen.
 * for example, for 2560 and 1440,
 * point1 is 512,240
 * point2 is 2048,960
 * It's avg screen for fishing
 */
public interface DefaultScreenPoints {

    Point getPoint1();

    Point getPoint2();
}

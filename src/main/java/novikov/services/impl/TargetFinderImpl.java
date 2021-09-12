package novikov.services.impl;

import novikov.entity.colors.ColorScopeEntity;
import novikov.services.TargetFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class TargetFinderImpl implements TargetFinder {

    @Autowired
    private ColorScopeEntity redFeather;

    @Autowired
    private ColorScopeEntity blueFeather;

    @Autowired
    private ColorScopeEntity whiteHook;

    @Override
    public Point getTarget(BufferedImage bufferedImage) {
        Point point1 = new Point(bufferedImage.getMinX(), bufferedImage.getMinY());
        Point point2 = new Point(bufferedImage.getWidth(), bufferedImage.getHeight());
        return getTarget(bufferedImage, point1, point2);
    }

    @Override
    public Point getTarget(BufferedImage bufferedImage, Point point1, Point point2) {
        int minX = (int) point1.getX();
        int maxX = (int) point2.getX();

        int minY = (int) point1.getY();
        int maxY = (int) point2.getY();

        List<Point> points = new ArrayList<>();
        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                int argb = bufferedImage.getRGB(x, y);
                if (checkColor(argb, redFeather)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
                if (checkColor(argb, blueFeather)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
                if (checkColor(argb, whiteHook)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
            }
        }

        int sumOfX = 0;
        int sumOfY = 0;
        for (Point point : points) {
            sumOfX += point.getX();
            sumOfY += point.getY();
        }
        int avgPointX = sumOfX / points.size();
        int avgPointY = sumOfY / points.size();

        return new Point(avgPointX, avgPointY);
    }

    private boolean checkColor(int rgbColor, ColorScopeEntity colorScope) {
        int red = (rgbColor >> 16) & 0xff;
        int green = (rgbColor >> 8) & 0xff;
        int blue = (rgbColor) & 0xff;
        boolean hasRed = colorScope.getMinRed() < red && red < colorScope.getMaxRed();
        boolean hasGreen = colorScope.getMinGreen() < green && green < colorScope.getMaxGreen();
        boolean hasBlue = colorScope.getMinBlue() < blue && blue < colorScope.getMaxBlue();
        return hasRed && hasGreen && hasBlue;
    }
}

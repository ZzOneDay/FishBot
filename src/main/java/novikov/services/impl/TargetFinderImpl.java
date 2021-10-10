package novikov.services.impl;

import novikov.entity.colors.ColorScopeEntity;
import novikov.services.TargetFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${target.sensitivity.background}")
    private int sensitivityOfBackground;

    @Override
    public Point getTarget(BufferedImage bufferedImage, BufferedImage backgroundImage, Point point1, Point point2) {
        int minX = (int) point1.getX();
        int maxX = (int) point2.getX();

        int minY = (int) point1.getY();
        int maxY = (int) point2.getY();

        List<Point> points = new ArrayList<>();
        for (int y = minY; y < maxY; y++) {
            for (int x = minX; x < maxX; x++) {
                int current = bufferedImage.getRGB(x, y);
                if (backgroundImage != null && isLikeAsBackground(backgroundImage.getRGB(x, y), current)) {
                    continue;
                }
                if (checkColor(current, redFeather)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
                if (checkColor(current, blueFeather)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
                if (checkColor(current, whiteHook)) {
                    Point point = new Point(x, y);
                    points.add(point);
                }
            }
        }

        if (points.size() != 0) {
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
        throw new IllegalArgumentException("Didn't find hook on screen");
    }

    private boolean checkColor(int rgbColor, ColorScopeEntity colorScope) {
        int red = (rgbColor >> 16) & 0xff;
        int green = (rgbColor >> 8) & 0xff;
        int blue = (rgbColor) & 0xff;
        boolean hasRed = colorScope.getMinRed() <= red && red <= colorScope.getMaxRed();
        boolean hasGreen = colorScope.getMinGreen() <= green && green <= colorScope.getMaxGreen();
        boolean hasBlue = colorScope.getMinBlue() <= blue && blue <= colorScope.getMaxBlue();
        return hasRed && hasGreen && hasBlue;
    }

    private boolean isLikeAsBackground(int background, int current) {
        int redCurrent = (current >> 16) & 0xff;
        int greenCurrent = (current >> 8) & 0xff;
        int blueCurrent = (current) & 0xff;

        int redBackground = (background >> 16) & 0xff;
        int greenBackground = (background >> 8) & 0xff;
        int blueBackground = (background) & 0xff;

        boolean hasRed = (Math.abs(redCurrent - redBackground)) < sensitivityOfBackground;
        boolean hasGreen = (Math.abs(greenCurrent - greenBackground)) < sensitivityOfBackground;
        boolean hasBlue = (Math.abs(blueCurrent - blueBackground)) < sensitivityOfBackground;
        return hasRed && hasGreen && hasBlue;
    }
}

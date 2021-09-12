package novikov.entity.resolution;

import java.awt.*;

public class ScreenResolution {
    private final int wight;//pixels
    private final int height;//pixels

    private final double point1ScaleX;//%
    private final double point1ScaleY;//%

    private final double point2ScaleX;//%
    private final double point2ScaleY;//%

    public ScreenResolution(int wight, int height, double point1ScaleX, double point1ScaleY, double point2ScaleX, double point2ScaleY) {
        this.wight = wight;
        this.height = height;
        this.point1ScaleX = point1ScaleX;
        this.point1ScaleY = point1ScaleY;
        this.point2ScaleX = point2ScaleX;
        this.point2ScaleY = point2ScaleY;
    }

    public Point getTargetFramePoint1() {
        return new Point((int) (wight * point1ScaleX), (int) (height * point1ScaleY));
    }

    public Point getTargetFramePoint2() {
        return new Point((int) (wight * point2ScaleX), (int) (height * point2ScaleY));
    }
}

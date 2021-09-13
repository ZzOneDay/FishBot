package novikov.entity.equations;

import java.awt.*;

/**
 * y(x) = kx + b
 * <p>
 * Ax+By+C=0
 * A = Y2 - Y1
 * B = X1 - X2
 * C = X2Y1 - X1Y2
 * <p>
 * k = -(A/B)
 * b = -(C/B)
 */
public class LineEquation {
    private final double indexA;
    private final double indexB;
    private final double indexC;

    private LineEquation(double indexA, double indexB, double indexC) {
        this.indexA = indexA;
        this.indexB = indexB;
        this.indexC = indexC;
    }

    public int getY(int X) {
        return (int) ((-(indexA / indexB) * X) - (indexC / indexB));
    }

    public LineEquation getPerpendicularLine(Point centerPoint) {
        double centerPointX = centerPoint.getX();
        double centerPointY = centerPoint.getY();

        double newIndexC = (indexB * centerPointX) - (indexA * centerPointY);
        double newIndexB = -indexB;

        return new LineEquation(indexA, newIndexB, newIndexC);
    }

   public static LineEquation createLineOf2Points(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();

        double indexA = y2 - y1;
        double indexB = x1 - x2;

        double indexC = ((x2 * y1) - (x1 * y2));
        return new LineEquation(indexA, indexB, indexC);
    }

    public static Point getCenterBetween2Points(Point point1, Point point2) {
        int centreX = (int) (point1.getX() + point2.getX())/2;
        int centreY = (int) (point1.getY() + point2.getY())/2;
        return new Point(centreX, centreY);
    }

    public static double getDistanceBetween2Points(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();

        double x2 = point2.getX();
        double y2 = point2.getY();
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }
}

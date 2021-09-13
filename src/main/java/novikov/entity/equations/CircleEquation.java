package novikov.entity.equations;

import java.awt.*;

public class CircleEquation {
    private final Point centre;
    private final double radius;

    public CircleEquation(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public int getY1(double X) {
        return (int) ((Math.sqrt((Math.pow(radius, 2) - Math.pow((X - centre.getX()), 2)))) + centre.getY());
    }

    public int getY2(double X) {
        return (int) ((-1) * (Math.sqrt((Math.pow(radius, 2) - Math.pow((X - centre.getX()), 2)))) + centre.getY());
    }
}

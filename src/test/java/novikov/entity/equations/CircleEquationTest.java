package novikov.entity.equations;

import junit.framework.TestCase;

import java.awt.*;

public class CircleEquationTest extends TestCase {

    public void testCircleSimple() {
        Point pointCentre = new Point(0, 0);
        double radius = 5.0;

        CircleEquation circleEquation = new CircleEquation(pointCentre, radius);
        assertEquals(5, circleEquation.getY1(0));
        assertEquals(-5, circleEquation.getY2(0));
    }

    public void testCircleHard() {
        Point pointCentre = new Point(4, 4);
        double radius = 9.0;

        CircleEquation circleEquation = new CircleEquation(pointCentre, radius);
        assertEquals(12, circleEquation.getY1(0));
        assertEquals(-4, circleEquation.getY2(0));
    }
}
package novikov.entity.equations;

import junit.framework.TestCase;

import java.awt.*;

public class LineEquationTest extends TestCase {

    public void testCreateLineFrom2pointsSimple() {
        Point point1 = new Point(0,0);
        Point point2 = new Point(10,10);
        LineEquation lineEquation = LineEquation.createLineOf2Points(point1, point2);
        assertEquals(1.0, lineEquation.getY(1), 0.1);
        assertEquals(5.0, lineEquation.getY(5), 0.1);
    }

    public void testCreateLineFrom2pointsHard() {
        Point point1 = new Point(4,2);
        Point point2 = new Point(10,12);
        LineEquation lineEquation = LineEquation.createLineOf2Points(point1, point2);
        assertEquals(12.0, lineEquation.getY(10), 0.1);
        assertEquals(160.0, lineEquation.getY(99), 0.1);
    }
}
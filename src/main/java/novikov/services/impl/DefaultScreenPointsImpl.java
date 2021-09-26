package novikov.services.impl;

import novikov.services.DefaultScreenPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class DefaultScreenPointsImpl implements DefaultScreenPoints {

    @Autowired
    Dimension screenSystemSize;

    @Override
    public Point getPoint1() {
        double width = screenSystemSize.getWidth();
        double height = screenSystemSize.getHeight();
        return new Point(((int) width / 5), (int) (height / 6));
    }

    @Override
    public Point getPoint2() {
        double width = screenSystemSize.getWidth();
        double height = screenSystemSize.getHeight();
        return new Point(((int) (width / 5 * 3.5)), ((int) (height / 6 * 4)));
    }
}

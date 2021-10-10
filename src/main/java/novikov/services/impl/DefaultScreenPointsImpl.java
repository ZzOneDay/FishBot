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
        return new Point((screenSystemSize.width / 6), (screenSystemSize.height / 7));
    }

    @Override
    public Point getPoint2() {
        return new Point((screenSystemSize.width / 8 * 6), (screenSystemSize.height / 8 * 5));
    }
}

package novikov.actions.impl;

import junit.framework.TestCase;
import novikov.actions.MouseMover;
import novikov.configuration.AppConfigTest;
import novikov.services.impl.DefaultScreenPointsImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.awt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigTest.class, loader = AnnotationConfigContextLoader.class)
public class MouseMoverImplTest extends TestCase {

    @Autowired
    MouseMover mouseMover;

    @Test
    public void test() {
        Point point1 = new Point(0,0);
        Point point2 = new Point(2000,1000);
        //mouseMover.move(point1, point2);
    }
}
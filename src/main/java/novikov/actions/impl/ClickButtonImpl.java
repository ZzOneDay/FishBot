package novikov.actions.impl;

import novikov.actions.ClickButton;
import novikov.entity.results.ClickResult;
import novikov.services.randoms.GenerateValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.InputEvent;

@Service
public class ClickButtonImpl implements ClickButton {
    private static final Logger LOG = LogManager.getLogger(ClickButtonImpl.class);

    @Autowired
    private Robot robot;

    @Autowired
    private GenerateValue generateValue;

    @Value("${mouse.click.clickTime.from}")
    private int clickTimeFrom;

    @Value("${mouse.click.clickTime.to}")
    private int clickTimeTo;

    @Override
    public ClickResult click(int buttonId) {
        try {
            Thread.sleep(generateValue.getRandomValue(clickTimeFrom, clickTimeTo, Math.random()));
            robot.mousePress(buttonId);
            Thread.sleep(generateValue.getRandomValue(10, 100, Math.random()));
            robot.mouseRelease(buttonId);
            return ClickResult.SUCCESS;
        } catch (InterruptedException e) {
            LOG.error("Application didn't click: {} id,  {}", buttonId,  e.getMessage());
            return ClickResult.FAIL;
        }
    }
}

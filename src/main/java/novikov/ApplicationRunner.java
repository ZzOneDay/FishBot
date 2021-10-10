package novikov;

import novikov.actions.ClickButton;
import novikov.actions.MouseMover;
import novikov.entity.results.ClickResult;
import novikov.entity.results.SoundCatcherResult;
import novikov.input.ImageCatcher;
import novikov.input.SoundCatcher;
import novikov.services.TargetFinder;
import novikov.services.randoms.GenerateValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Test version 0.01b
 */
@Service
public class ApplicationRunner implements Runnable {

    @Autowired
    private ClickButton clickButton;

    @Autowired
    private ImageCatcher imageCatcher;

    @Autowired
    private TargetFinder targetFinder;

    @Autowired
    private MouseMover mouseMover;

    @Autowired
    private SoundCatcher soundCatcher;

    @Autowired
    private GenerateValue generateValue;

    private Point point1;
    private Point point2;
    private int clickForThrowHook;
    private int clickForCheckHook;


    @Override
    public void run() {
        if (point1 == null || point2 == null || clickForThrowHook == 0 || clickForCheckHook == 0) {
            throw new IllegalArgumentException("Not setting all of fields for start application");
        }
        int successFish = 0;

        //START APPLICATION
        boolean start = startApplicationInConsole();
        if (!start) {
            throw new RuntimeException("Start got result - Error");
        }
        while (!Thread.currentThread().isInterrupted()) {
            //Get SCREEN FOR BACKGROUND
            BufferedImage background = getScreen();

            waitTime(1000, 3000);
            //THROW HOOK
            boolean throwHook = actionThrowHook();
            if (!throwHook) {
                throw new RuntimeException("Throw hook without SUCCESS");
            }

            waitTime(1500, 2000);
            //GET SCREEN WITH HOOK
            BufferedImage screen = getScreen();

            //FINDING HOOK
            Point targetPoint = targetFinder.getTarget(screen, background, point1, point2);

            //Moving mouse
            boolean resultOfMoving = mouseMover.move(null, targetPoint);
            if (!resultOfMoving) {
                throw new RuntimeException("Moving without SUCCESS");
            } else {
                System.out.println("mouse is moved");
            }

            //LISTING FOR CATCH FISH ON HOOK 2 VARIANTS
            SoundCatcherResult result = soundCatcher.catchLoudSound();
            if (result.equals(SoundCatcherResult.TIME_IS_LEFT)) {
                System.out.println("Fish not on the hook, restart fishing process");
                continue;
            } else {
                System.out.println("Fish on hook");
            }

            //FISH ON HOOK, NEED TO CLICK ON HERE.
            boolean click = actionCheckFish();
            waitTime(800, 1800);
            if (!click) {
                throw new RuntimeException("didn't get hook from water. without SUCCESS");
            } else {
                System.out.println("SUCCESS FOR FISHING");
                successFish++;
            }
        }
        System.out.println("Finish of Fishing: count success is " + successFish);
    }

    private void waitTime(int min, int max) {
        try {
            Thread.sleep(generateValue.getRandomValue(min, max, Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private boolean startApplicationInConsole() {
        for (int i = 10; i > 0; i--) {
            System.out.println("Start: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean actionThrowHook() {
        ClickResult clickResult = clickButton.click(clickForThrowHook);
        return clickResult.equals(ClickResult.SUCCESS);
    }

    private BufferedImage getScreen() {
        return imageCatcher.getImageFromScreen();
    }

    private boolean actionCheckFish() {
        ClickResult clickResult = clickButton.click(clickForCheckHook);
        return clickResult.equals(ClickResult.SUCCESS);
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void setClickForThrowHook(int clickForThrowHook) {
        this.clickForThrowHook = clickForThrowHook;
    }

    public void setClickForCheckHook(int clickForCheckHook) {
        this.clickForCheckHook = clickForCheckHook;
    }
}

package novikov;

import novikov.configuration.AppConfig;
import novikov.services.DefaultScreenPoints;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

public class StartApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        Thread application = null;
        System.out.println("Welcome to finishBot version 0.01");

        DefaultScreenPoints defaultScreenPoints = ctx.getBean(DefaultScreenPoints.class);
        Point point1 = defaultScreenPoints.getPoint1();
        Point point2 = defaultScreenPoints.getPoint2();
        int buttonForThrowHook = InputEvent.BUTTON2_DOWN_MASK;
        int buttonForCheckHook = InputEvent.BUTTON3_DOWN_MASK;

        while (true) {
            System.out.println("Commands: /start /finish /help");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();

            if (command.equals("/set point1")) {
                point1 = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Set point 1 is " + point1);
            }

            if (command.equals("/set point2")) {
                point2 = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Set point 2 is " + point2);
            }

            if (command.equals("/help")) {
                System.out.println("Other commands: /set point1 /set point2 " +
                        " If you want to change default value for finding hook");
                System.out.println("Point1 is : " + point1 +
                        " Point2 is : " + point2);
                System.out.println("buttonForThrowHook is : " + buttonForCheckHook +
                        "; buttonForCheckHook is : " + buttonForThrowHook);
            }

            if (command.equals("/start")) {
                ApplicationRunner applicationRunner = ctx.getBean(ApplicationRunner.class);
                applicationRunner.setPoint1(point1);
                applicationRunner.setPoint2(point2);
                applicationRunner.setClickForThrowHook(buttonForThrowHook);
                applicationRunner.setClickForCheckHook(buttonForCheckHook);

                application = new Thread(applicationRunner);
                application.start();
            }

            if (command.equals("/finish")) {
                if (application != null) {
                    application.interrupt();
                }
            }
        }
    }
}

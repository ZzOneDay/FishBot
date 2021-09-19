package novikov;

import novikov.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

public class StartApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
        Thread application = null;
        System.out.println("Welcome to finishBot version 0.01");
        Point point1 = null;
        Point point2 = null;
        int buttonForThrowHook = InputEvent.BUTTON2_DOWN_MASK;
        int buttonForCheckHook = InputEvent.BUTTON3_DOWN_MASK;

        while (true) {
            System.out.println("Commands: start, info, finish, set point1, set point2");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            //start = MouseInfo.getPointerInfo().getLocation();

            if (command.equals("admin")) {
                System.out.println("Hello, Admin");
                point1 = new Point(500,200);
                point2 = new Point(1545,670);
                System.out.println("Set point 1 is " + point1);
                System.out.println("Set point 2 is " + point2);
            }

            if (command.equals("set point1")) {
                point1 = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Set point 1 is " + point1);
            }

            if (command.equals("set point2")) {
                point2 = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Set point 2 is " + point2);
            }

            if (command.equals("info")) {
                System.out.println("Point1 is : " + point1);
                System.out.println("Point2 is : " + point2);
                System.out.println("buttonForThrowHook is : " + buttonForCheckHook);
                System.out.println("buttonForCheckHook is : " + buttonForThrowHook);
            }

            if (point1 == null) {
                System.out.println("Please set point 1");
            }
            if (point2 == null) {
                System.out.println("Please set point 2");
            }

            if (command.equals("start")) {
                ApplicationRunner applicationRunner = ctx.getBean(ApplicationRunner.class);
                applicationRunner.setPoint1(point1);
                applicationRunner.setPoint2(point2);
                applicationRunner.setClickForThrowHook(buttonForThrowHook);
                applicationRunner.setClickForCheckHook(buttonForCheckHook);

                application = new Thread(applicationRunner);
                application.start();
            }

            if (command.equals("finish")) {
                if (application != null) {
                    application.interrupt();
                }
            }
        }
    }
}

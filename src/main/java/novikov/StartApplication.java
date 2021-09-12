package novikov;

import novikov.configuration.AppConfig;
import novikov.input.SoundCatcher;
import novikov.services.TargetFinder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

public class StartApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
//        Image
//        ImageCatcher imageCatcher = ctx.getBean(ImageCatcher.class);
//        Point point1 = new Point(200,200);
//        Point point2 = new Point(400,400);
//
//        BufferedImage imageFromScreen = imageCatcher.getImageFromScreen(point1, point2);
//        File file = new File("screen-capture.png");
//        boolean status = ImageIO.write(imageFromScreen, "png", file);
//        System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());


//        Sound
//        SoundCatcher soundCatcher = ctx.getBean(SoundCatcher.class);
//        while (true) {
//            soundCatcher.catchLoudSound();
//            System.out.println("catch it!" + new Date());
//            Thread.sleep(2000);
//        }


        File img = new File("Screenshot_4.jpg");
        BufferedImage image = ImageIO.read(img );
        TargetFinder targetFinder = ctx.getBean(TargetFinder.class);
        Point point1 = new Point(400,350);
        Point point2 = new Point(2100,900);


        Point point = targetFinder.getTarget(image, point1, point2);
        System.out.println("X - " + point.getX() + "; Y - " + point.getY());


    }
}

package novikov.services.impl;

import novikov.services.TargetFinder;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.Objects;

@Service
public class TargetFinderOpenCVImpl implements TargetFinder {

    @Override
    public java.awt.Point getTarget(BufferedImage bufferedImage) {
        OpenCV.loadLocally();
        CascadeClassifier hookDetector = new CascadeClassifier();
        String cascadePath = Objects.requireNonNull(getClass().getResource("/hook_cascade.xml")).getPath().substring(1);
        hookDetector.load(cascadePath);
        Mat image = bufferedImageToMat(bufferedImage);

        // Detecting hook
        MatOfRect hookDetections = new MatOfRect();
        hookDetector.detectMultiScale(image, hookDetections);

//        Rect rect = hookDetections.toArray()[0];
//        int targetX = (rect.x + rect.width) / 2;
//        int targetY = (rect.y + rect.height) / 2;
//        for (Rect rect : hookDetections.toArray()) {
//            System.out.println(rect);
//        }

        //return new Point(targetX, targetY);


        // Creating box around hook
        for (Rect rect : hookDetections.toArray())
        {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        // Saving the output image
        File file = new File("AfterTarget1.jpg");
        Imgcodecs.imwrite(file.getPath(), image);
        return null;
    }

    @Override
    public java.awt.Point getTarget(BufferedImage bufferedImage, java.awt.Point point1, java.awt.Point point2) {
        return getTarget(bufferedImage);
    }

    private static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}

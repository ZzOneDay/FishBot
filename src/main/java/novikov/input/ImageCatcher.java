package novikov.input;

import java.awt.image.BufferedImage;

public interface ImageCatcher {

    /**
     * Create screenshot in buffer, return all screen.
     *
     * @return image of screenshot
     */
    BufferedImage getImageFromScreen();
}

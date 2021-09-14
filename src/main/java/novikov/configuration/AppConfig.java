package novikov.configuration;

import novikov.entity.colors.ColorScopeEntity;
import novikov.entity.resolutions.ScreenResolution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sound.sampled.AudioFormat;
import java.awt.*;

@Configuration
@ComponentScan("novikov")
@PropertySource("classpath:application.properties")
public class AppConfig {
    private static final Logger LOG = LogManager.getLogger(AppConfig.class);

    @Bean
    Robot robot() {
        try {
            return new Robot();
        } catch (AWTException e) {
            LOG.error("Project did not start working. Robot was not created {}", e.getMessage());
        }
        throw new IllegalArgumentException("Robot service was not initialized");
    }

    @Bean
    AudioFormat audioFormat() {
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    @Bean
    ColorScopeEntity redFeather() {
        return new ColorScopeEntity(
                48, 61,
                20, 60,
                10, 50
        );
    }

    @Bean
    ColorScopeEntity blueFeather() {
        return new ColorScopeEntity(
                25, 50,
                45, 91,
                88, 157
        );
    }

    @Bean
    ColorScopeEntity whiteHook() {
        return new ColorScopeEntity(
                77, 92,
                92, 112,
                97, 111
        );
    }

    @Bean
    ScreenResolution screenResolution() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        return new ScreenResolution(width, height,
                0.15, 0.24,
                0.82, 0.65);
    }
}

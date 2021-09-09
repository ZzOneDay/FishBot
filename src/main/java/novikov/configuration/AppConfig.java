package novikov.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sound.sampled.AudioFormat;
import java.awt.*;

@Configuration
@ComponentScan("novikov")
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
}

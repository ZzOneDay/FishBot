package novikov.configuration;

import novikov.entity.colors.ColorScopeEntity;
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
                22, 85,
                6, 47,
                2, 30
        );
    }

    @Bean
    ColorScopeEntity blueFeather() {
        return new ColorScopeEntity(
                13, 54,
                12, 70,
                19, 95
        );
    }

    @Bean
    ColorScopeEntity whiteHook() {
        return new ColorScopeEntity(
                178, 225,
                87, 184,
                58, 119
        );
    }

    @Bean
    Dimension screenSystemSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}

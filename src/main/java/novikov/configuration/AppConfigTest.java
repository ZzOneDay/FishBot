package novikov.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.awt.*;

@Configuration
@ComponentScan("novikov")
@PropertySource("classpath:application.properties")
public class AppConfigTest {

    //2560x1480 x 1.25 scale
    @Bean
    Dimension screenSystemSize() {
        Dimension dimension = new Dimension();
        dimension.setSize(2048, 1152);
        return dimension;
    }
}

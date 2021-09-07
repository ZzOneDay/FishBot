package novikov;

import novikov.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();
    }
}

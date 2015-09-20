package name.zemon.david.propwareide.server.config;

import name.zemon.david.propwareide.server.controller.SpringMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by david on 9/19/15.
 */
@Configuration
@Import({
        PropertyConfig.class,
        ServiceConfig.class
})
@ComponentScan(basePackageClasses = SpringMarker.class)
@EnableWebMvc
public class MvcConfig {
}

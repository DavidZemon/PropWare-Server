package name.zemon.david.propwareide.server.build.config;

import name.zemon.david.propwareide.server.build.controller.SpringMarker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by david on 9/23/15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = SpringMarker.class)
public class MvcConfig {
}

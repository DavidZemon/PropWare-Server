package name.zemon.david.propwareide.server.file.config;

import name.zemon.david.propwareide.common.controller.SimpleCORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;

/**
 * Created by david on 9/19/15.
 */
@Configuration
@Import({
        PropertyConfig.class,
        ServiceConfig.class
})
@ComponentScan(basePackageClasses = {
        name.zemon.david.propwareide.server.file.controller.SpringMarker.class
})
@EnableWebMvc
public class MvcConfig {
    @Bean
    public Filter filter() {
        return new SimpleCORSFilter();
    }
}

package name.zemon.david.propwareide.server.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by david on 9/19/15.
 */
@Configuration
@PropertySources({
        @PropertySource("classpath:default.properties"),
        @PropertySource("classpath:${environment}.properties")
})
public class PropertyConfig {
}

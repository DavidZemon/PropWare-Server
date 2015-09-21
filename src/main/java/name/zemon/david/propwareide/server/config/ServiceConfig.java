package name.zemon.david.propwareide.server.config;

import name.zemon.david.propwareide.server.service.FileService;
import name.zemon.david.propwareide.server.service.LocalFileService;
import name.zemon.david.propwareide.server.service.LocalProjectService;
import name.zemon.david.propwareide.server.service.ProjectService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.io.File;

/**
 * Created by david on 9/19/15.
 */
@Configuration
public class ServiceConfig {
    @Inject
    private Environment env;

    @Bean
    public FileService fileRetriever() {
        return new LocalFileService(this.getWorkspaceRoot());
    }

    @Bean
    public ProjectService projectService (final FileService fileService) {
        return new LocalProjectService(this.getWorkspaceRoot(), fileService);
    }

    private String getWorkspaceRoot() {
        final String rootVarName = this.env.getRequiredProperty("workspace.location.root_var_name");
        final String root = this.env.getRequiredProperty(rootVarName);
        final String child = this.env.getRequiredProperty("workspace.location.child");
        return String.join(File.separator, root, child);
    }
}

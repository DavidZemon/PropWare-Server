package name.zemon.david.propwareide.server.controller;

import name.zemon.david.propwareide.server.pojo.Project;
import name.zemon.david.propwareide.server.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by david on 9/20/15.
 */
@Controller
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @Inject
    public ProjectController(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<String> getAll(final String user) {
        return this.projectService.getUserProjects(user);
    }

    @RequestMapping(value = "{project}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Project get(final String user, @PathVariable("project") final String project) {
        return this.projectService.getProject(user, project);
    }
}

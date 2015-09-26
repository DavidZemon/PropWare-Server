package name.zemon.david.propwareide.server.file.controller;

import name.zemon.david.propwareide.common.pojo.BuildRequest;
import name.zemon.david.propwareide.common.pojo.BuildResponse;
import name.zemon.david.propwareide.server.file.pojo.Project;
import name.zemon.david.propwareide.server.file.service.FileService;
import name.zemon.david.propwareide.server.file.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by david on 9/25/15.
 */
@Controller
@RequestMapping("buildRequest")
public class BuildRequestController {
    private final ProjectService projectService;
    private final FileService    fileService;

    @Inject
    public BuildRequestController(final ProjectService projectService, final FileService fileService) {
        this.projectService = projectService;
        this.fileService = fileService;
    }

    @RequestMapping(value = "{user}/{project}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BuildResponse build(@PathVariable("user") final String user,
                               @PathVariable("project") final String projectName,
                               @RequestBody final BuildRequest buildRequest) throws IOException {
        final Project project = this.projectService.getProject(user, projectName);

        for (String fileName : project.getFileNames())
            buildRequest.addFile(this.fileService.getFile(user, projectName, fileName));

        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/propwareide/build-server/jas/build", buildRequest,
                BuildResponse.class);
    }
}

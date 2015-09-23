package name.zemon.david.propwareide.server.controller;

import name.zemon.david.propwareide.server.pojo.PWFile;
import name.zemon.david.propwareide.server.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by david on 9/19/15.
 */
@Controller
@RequestMapping("file")
public class FileController {
    private final FileService fileService;

    @Inject
    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Collection<PWFile> getAllProjectFiles(final String user, final String project) throws IOException {
        return this.fileService.getAllProjectFiles(user, project);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PWFile get(final String user, final String project, final @PathVariable("name") String name)
            throws IOException {
        return this.fileService.getFile(user, project, name);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void update(final String user, final String project, final @PathVariable("name") String name, @RequestBody final PWFile file)
            throws IOException {
        this.fileService.save(user, project, file);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void create(final String user, final String project, final @PathVariable("name") String name)
            throws IOException {
        this.fileService.create(user, project, name);
    }

    @RequestMapping(value = "{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void delete(final String user, final String project, final @PathVariable("name") String name)
            throws IOException {
        this.fileService.delete(user, project, name);
    }
}

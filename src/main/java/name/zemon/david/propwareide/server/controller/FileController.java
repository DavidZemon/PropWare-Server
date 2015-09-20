package name.zemon.david.propwareide.server.controller;

import name.zemon.david.propwareide.server.pojo.StringWrapper;
import name.zemon.david.propwareide.server.service.FileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

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
    public StringWrapper get(final String user, final String project, final String file) {
        return this.fileService.getFile(user, project, file);
    }
}

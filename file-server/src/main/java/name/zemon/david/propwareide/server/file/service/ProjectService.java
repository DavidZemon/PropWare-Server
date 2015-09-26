package name.zemon.david.propwareide.server.file.service;

import name.zemon.david.propwareide.server.file.pojo.Project;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by david on 9/20/15.
 */
public interface ProjectService {
    Collection<String> getUserProjects(String user) throws IOException;

    Project getProject(String user, String project) throws IOException;

    boolean createProject(String user, String project) throws IOException;
}

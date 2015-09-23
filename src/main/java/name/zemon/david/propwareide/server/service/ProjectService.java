package name.zemon.david.propwareide.server.service;

import name.zemon.david.propwareide.server.pojo.Project;

import java.util.Collection;

/**
 * Created by david on 9/20/15.
 */
public interface ProjectService {
    Collection<String> getUserProjects(String user);

    Project getProject(String user, String project);

    boolean createProject(String user, String project);
}

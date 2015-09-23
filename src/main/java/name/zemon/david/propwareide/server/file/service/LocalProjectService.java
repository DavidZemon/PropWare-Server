package name.zemon.david.propwareide.server.file.service;

import name.zemon.david.propwareide.server.file.pojo.Project;

import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;

import static name.zemon.david.propwareide.server.file.util.FilesystemUtility.*;

/**
 * Created by david on 9/20/15.
 */
public class LocalProjectService implements ProjectService {
    private final String      workspaceRoot;

    public LocalProjectService(final String workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
    }

    @Override
    public Collection<String> getUserProjects(final String user) {
        Collection<String> directoryContents = getDirectoryContents(this.workspaceRoot, user);
        return directoryContents.stream()
                .filter(entry -> isDirectory(this.workspaceRoot, user, entry))
                .collect(Collectors.toList());
    }

    @Override
    public Project getProject(final String user, final String projectName) {
        final Collection<String> directoryContents = getDirectoryContents(this.workspaceRoot, user, projectName);
        final Collection<String> fileNames = directoryContents.stream()
                .filter(entry -> isFile(this.workspaceRoot, user, projectName, entry))
                .collect(Collectors.toList());

        return new Project(projectName, fileNames);
    }

    @Override
    public boolean createProject(final String user, final String project) {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project));
        return file.mkdir();
    }
}

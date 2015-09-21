package name.zemon.david.propwareide.server.service;

import name.zemon.david.propwareide.server.pojo.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by david on 9/20/15.
 */
public class LocalProjectService implements ProjectService {
    private final String      workspaceRoot;
    private final FileService fileService;

    public LocalProjectService(final String workspaceRoot, final FileService fileService) {
        this.workspaceRoot = workspaceRoot;
        this.fileService = fileService;
    }

    @Override
    public Collection<String> getUserProjects(final String user) {
        Collection<String> directoryContents = this.getDirectoryContents(this.workspaceRoot, user);
        return directoryContents.stream()
                .filter(entry -> this.isDirectory(this.workspaceRoot, user, entry))
                .collect(Collectors.toList());
    }

    @Override
    public Project getProject(final String user, final String projectName) {
        Collection<String> directoryContents = this.getDirectoryContents(this.workspaceRoot, user, projectName);
        final Collection<String> fileNames = directoryContents.stream()
                .filter(entry -> this.isFile(this.workspaceRoot, user, projectName, entry))
                .collect(Collectors.toList());

        final Project project = new Project(projectName);
        for (final String fileName : fileNames)
            project.addFile(fileName, this.fileService.getFile(user, projectName, fileName));
        return project;
    }

    private Collection<String> getDirectoryContents(final String... directories) {
        final String projectRoot = String.join(File.separator, directories);
        final String[] contents = new File(projectRoot).list();
        final Collection<String> contentList = new ArrayList<>();
        Collections.addAll(contentList, contents);
        return contentList;
    }

    private boolean isDirectory(final String... path) {
        final String absolutePath = String.join(File.separator, path);
        final File file = new File(absolutePath);
        return file.isDirectory();
    }

    private boolean isFile(final String... path) {
        final String absolutePath = String.join(File.separator, path);
        final File file = new File(absolutePath);
        return file.isFile();
    }
}

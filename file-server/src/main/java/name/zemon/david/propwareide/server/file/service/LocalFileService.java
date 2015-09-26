package name.zemon.david.propwareide.server.file.service;

import name.zemon.david.propwareide.common.pojo.PWFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static name.zemon.david.propwareide.server.file.util.FilesystemUtility.*;

/**
 * Created by david on 9/19/15.
 */
public class LocalFileService implements FileService {
    private final String workspaceRoot;

    public LocalFileService(final String workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
    }

    @Override
    public PWFile getFile(final String user, final String project, final String fileName) throws IOException {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project, fileName));
        try (FileInputStream stream = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            //noinspection ResultOfMethodCallIgnored
            stream.read(data);
            return new PWFile(createUrlSafeString(fileName), new String(data, "UTF-8"));
        }
    }

    @Override
    public Collection<PWFile> getAllProjectFiles(final String user, final String project) throws IOException {
        final Collection<String> directoryContents = getDirectoryContents(this.workspaceRoot, user, project);
        final Collection<String> fileNames = directoryContents.stream()
                .filter(entry -> isFile(this.workspaceRoot, user, project, entry))
                .collect(Collectors.toList());
        final Collection<PWFile> files = new ArrayList<>();
        for (final String fileName : fileNames)
            files.add(this.getFile(user, project, fileName));
        return files;
    }

    @Override
    public void save(final String user, final String project, final PWFile pwFile) throws IOException {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project,
                undoUrlSafeString(pwFile.getName())));
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(pwFile.getContent().getBytes());
        }
    }

    @Override
    public boolean create(final String user, final String project, final String name) throws IOException {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project,
                undoUrlSafeString(name)));
        return file.createNewFile();
    }

    @Override
    public void delete(final String user, final String project, final String name) throws FileNotFoundException {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project,
                undoUrlSafeString(name)));
        if (!file.delete())
            throw new FileNotFoundException(file.toString());
    }
}

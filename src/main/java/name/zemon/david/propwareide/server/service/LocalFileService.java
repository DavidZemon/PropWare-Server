package name.zemon.david.propwareide.server.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by david on 9/19/15.
 */
public class LocalFileService implements FileService {
    private final String workspaceRoot;

    public LocalFileService(final String workspaceRoot) {
        this.workspaceRoot = workspaceRoot;
    }

    @Override
    public String getFile(final String user, final String project, final String fileName) {
        final File file = new File(String.join(File.separator, this.workspaceRoot, user, project, fileName));
        try (FileInputStream stream = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            stream.read(data);
            return new String(data, "UTF-8");
        } catch (final IOException ignored) {
            return "";
        }
    }
}

package name.zemon.david.propwareide.server.file.service;


import name.zemon.david.propwareide.common.pojo.PWFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by david on 9/19/15.
 */
public interface FileService {
    PWFile getFile(String user, String project, String fileName) throws IOException;

    Collection<PWFile> getAllProjectFiles(String user, String project) throws IOException;

    void save(String user, String project, PWFile file) throws IOException;

    boolean create(String user, String project, String name) throws IOException;

    void delete(String user, String project, String name) throws FileNotFoundException;
}

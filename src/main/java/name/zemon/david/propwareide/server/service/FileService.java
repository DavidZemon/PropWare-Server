package name.zemon.david.propwareide.server.service;

import name.zemon.david.propwareide.server.pojo.StringWrapper;

/**
 * Created by david on 9/19/15.
 */
public interface FileService {
    StringWrapper getFile(String user, String project, String fileName);
}

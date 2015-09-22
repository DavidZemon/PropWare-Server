package name.zemon.david.propwareide.server.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by david on 9/21/15.
 */
public class FilesystemUtility {
    public static Collection<String> getDirectoryContents(final String... directories) {
        final String projectRoot = String.join(File.separator, directories);
        final String[] contents = new File(projectRoot).list();
        final Collection<String> contentList = new ArrayList<>();
        Collections.addAll(contentList, contents);
        return contentList;
    }

    public static boolean isDirectory(final String... path) {
        final String absolutePath = String.join(File.separator, path);
        final File file = new File(absolutePath);
        return file.isDirectory();
    }

    public static boolean isFile(final String... path) {
        final String absolutePath = String.join(File.separator, path);
        final File file = new File(absolutePath);
        return file.isFile();
    }
}

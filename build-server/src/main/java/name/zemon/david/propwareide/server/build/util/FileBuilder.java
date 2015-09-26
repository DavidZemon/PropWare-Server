package name.zemon.david.propwareide.server.build.util;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by david on 9/23/15.
 */
public class FileBuilder {
    private static final String CMAKE_HEADER                    = "cmake_minimum_required (VERSION 3.0.0)\n" +
            "find_package(PropWare REQUIRED)\n";
    private static final String CMAKE_OPTION_TEMPLATE           = "set(%s \"%s\")\n";
    private static final String PROJECT_NAME_TEMPLATE           = "%s%d";
    private static final String CMAKE_PROJECT_TEMPLATE          = "project(%s)\n";
    private static final String CMAKE_SOURCE_FILE_GLOB_TEMPLATE = "file(GLOB SRCS \"*\")\n";
    private static final String CMAKE_ADD_EXE_TEMPLATE          = "create_simple_executable(%s%d ${SRCS})\n";
    private static final String PROJECT_DIR_TEMPLATE            = System.getProperty("java.io.tmpdir") + "/%d";

    public static String buildCMakeScript(final int buildId, @Nonnull final Map<String, String> options) {
        final List<String> cmakeLines = new ArrayList<>();

        cmakeLines.add(CMAKE_HEADER);
        cmakeLines.add("\n");
        options.forEach(
                (name, value) -> cmakeLines.add(String.format(CMAKE_OPTION_TEMPLATE, name, value))
        );
        cmakeLines.add("\n");
        cmakeLines.add(String.format(CMAKE_PROJECT_TEMPLATE, getProjectName(buildId)));
        cmakeLines.add("\n");
        cmakeLines.add(CMAKE_SOURCE_FILE_GLOB_TEMPLATE);
        cmakeLines.add("\n");
        cmakeLines.add(String.format(CMAKE_ADD_EXE_TEMPLATE, "PropWareIDE", buildId));

        return cmakeLines.stream().collect(Collectors.joining());
    }

    public static File getProjectDirectory(final int buildId) throws IOException {
        final File directory = new File(String.format(PROJECT_DIR_TEMPLATE, buildId));
        if (!directory.mkdir())
            throw new IOException("Unable to create directory: " + directory.getCanonicalPath());
        return directory;
    }

    public static void dropFile(@Nonnull final File directory, @Nonnull final String name,
                                @Nonnull final String content) throws IOException {
        final File file = new File(directory.getCanonicalPath() + File.separator + name);
        try (FileOutputStream stream = new FileOutputStream(file)) {
            stream.write(content.getBytes());
        }
    }

    public static String getProjectName(final int buildId) {
        return String.format(PROJECT_NAME_TEMPLATE, "PropWareIDE", buildId);
    }
}

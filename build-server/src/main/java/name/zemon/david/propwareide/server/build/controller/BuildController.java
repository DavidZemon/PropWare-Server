package name.zemon.david.propwareide.server.build.controller;

import name.zemon.david.propwareide.common.pojo.BuildRequest;
import name.zemon.david.propwareide.common.pojo.BuildResponse;
import name.zemon.david.propwareide.common.pojo.CommandResult;
import name.zemon.david.propwareide.common.pojo.PWFile;
import name.zemon.david.propwareide.server.build.exception.BuildFailedException;
import name.zemon.david.propwareide.server.build.exception.HangingProcessException;
import name.zemon.david.propwareide.server.build.util.FileBuilder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static name.zemon.david.propwareide.server.build.util.FileBuilder.getProjectName;

/**
 * Created by david on 9/23/15.
 */
@Controller
@RequestMapping("build")
public class BuildController {
    private static final Logger        LOG          = LoggerFactory.getLogger(BuildController.class);
    private static final AtomicInteger buildCounter = new AtomicInteger(0);

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BuildResponse build(@RequestBody @Nonnull final BuildRequest buildRequest)
            throws IOException, InterruptedException, HangingProcessException, BuildFailedException {
        final int buildId = buildCounter.incrementAndGet();
        final File projectDirectory = FileBuilder.getProjectDirectory(buildId);

        dropFiles(buildId, buildRequest, projectDirectory);

        BuildResponse buildResponse;
        try {
            final CommandResult cmakeResult = runCMake(projectDirectory);
            if (0 == cmakeResult.getExitCode()) {
                final CommandResult makeResult = runMake(projectDirectory);
                if (0 == makeResult.getExitCode()) {
                    final byte[] binary = readElf(buildId, projectDirectory);
                    buildResponse = new BuildResponse(cmakeResult, makeResult, binary);
                } else
                    buildResponse = new BuildResponse(cmakeResult, makeResult, null);
            } else
                buildResponse = new BuildResponse(cmakeResult, null, null);

            return sanitizePaths(buildResponse, projectDirectory);
        } finally {
            FileUtils.deleteDirectory(projectDirectory);
        }
    }

    private static BuildResponse sanitizePaths(@Nonnull final BuildResponse buildResponse, @Nonnull final File
            projectDirectory) throws IOException {
        buildResponse.setCmakeResult(sanitizePaths(buildResponse.getCmakeResult(), projectDirectory));
        buildResponse.setMakeResult(sanitizePaths(buildResponse.getMakeResult(), projectDirectory));
        return buildResponse;
    }

    private static CommandResult sanitizePaths(@Nullable final CommandResult commandResult,
                                               @Nonnull final File directory) throws IOException {
        if (null != commandResult)
            commandResult.setOutput(commandResult.getOutput().replaceAll(directory.getCanonicalPath(), "<root>"));
        return commandResult;
    }

    private static void dropFiles(final int buildId, final @Nonnull BuildRequest buildRequest,
                                  final File projectDirectory) throws IOException, InterruptedException {
        final String cmakeScript = FileBuilder.buildCMakeScript(buildId, buildRequest.getCMakeOptions());
        FileBuilder.dropFile(projectDirectory, "CMakeLists.txt", cmakeScript);
        for (PWFile file : buildRequest.getFiles())
            FileBuilder.dropFile(projectDirectory, file.getName().replaceAll("\\$", "."), file.getContent());
    }

    private static CommandResult runCMake(@Nonnull final File projectDirectory)
            throws IOException, InterruptedException, HangingProcessException, BuildFailedException {
        final File binaryDirectory = new File(String.join(File.separator, projectDirectory.getCanonicalPath(), "bin"));
        return runCommand(binaryDirectory, "cmake", "..");
    }

    private static CommandResult runMake(@Nonnull final File projectDirectory)
            throws IOException, InterruptedException, HangingProcessException, BuildFailedException {
        final File binaryDirectory = new File(String.join(File.separator, projectDirectory.getCanonicalPath(), "bin"));
        return runCommand(binaryDirectory, "make", "all");
    }

    private static byte[] readElf(final int buildId, @Nonnull final File projectDirectory) throws IOException {
        final File binaryFile = new File(String.join(File.separator, projectDirectory.getCanonicalPath(), "bin",
                getProjectName(buildId) + ".elf"));

        byte[] result = new byte[(int) binaryFile.length()];
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(binaryFile))) {
            int totalBytesRead = 0;
            while (totalBytesRead < result.length) {
                int bytesRemaining = result.length - totalBytesRead;
                int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                if (0 < bytesRead)
                    totalBytesRead = totalBytesRead + bytesRead;
            }
            return result;
        }
    }

    private static CommandResult runCommand(@Nonnull File workingDirectory, final String command, final String... args)
            throws IOException, InterruptedException, HangingProcessException, BuildFailedException {
        if (!workingDirectory.exists())
            if (!workingDirectory.mkdir())
                throw new IOException("Unable to create binary directory: " + workingDirectory.getCanonicalPath());

        final List<String> cmd = new ArrayList<>();
        cmd.add(command);
        Collections.addAll(cmd, args);

        final ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        processBuilder.directory(workingDirectory);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        if (!process.waitFor(15, TimeUnit.SECONDS))
            throw new HangingProcessException(String.format("[%s]: %s", workingDirectory.getCanonicalPath(), cmd));

        final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        final StringBuilder sb = new StringBuilder();
        String line;
        while (null != (line = br.readLine()))
            sb.append(line).append('\n');

        return new CommandResult(process.exitValue(), sb.toString());
    }
}

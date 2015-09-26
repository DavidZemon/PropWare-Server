package name.zemon.david.propwareide.server.build.controller;

import name.zemon.david.propwareide.common.pojo.BuildRequest;
import name.zemon.david.propwareide.common.pojo.BuildResponse;
import name.zemon.david.propwareide.common.pojo.PWFile;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by david on 9/24/15.
 */
public class BuildControllerTest {
    private BuildController testable;

    @Before
    public void setUp() throws Exception {
        this.testable = new BuildController();
    }

    @Test
    public void testBuild() throws Exception {
        final BuildRequest buildRequest = new BuildRequest();
        buildRequest.addCMakeOption("MODEL", "lmm");
        buildRequest.addFile(new PWFile("Sample.cpp", "int main () {return 0;}"));
        buildRequest.addTarget("all");

        final BuildResponse response = this.testable.build(buildRequest);
        System.out.println(response);
    }
}

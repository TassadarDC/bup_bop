package functional;

import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.utils.FileUtils;

public class BasePingTests {

    //Todo - update this to somehow execute after each test case using @AfterClass as for now negatives leave files as they're failing before method execution.
    // Need to handle negative scenarios and it'll work, but AfterClass is preferable
    public void cleanUpGeneratedFiles(TestDataDto testData) {
        cleanUpGeneratedFile(testData.getConfig().getPath());
        cleanUpGeneratedFile(testData.getReport().getPath());
    }

    public void cleanUpGeneratedFile(String path) {
        FileUtils.deleteFile(path);
    }
}

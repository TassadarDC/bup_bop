package functional;

import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.utils.PingerFileUtils;

public class BasePingTests {

    public void cleanUpGeneratedFiles(TestDataDto testData) {
        cleanUpGeneratedFile(testData.getConfig().getPath());
        cleanUpGeneratedFile(testData.getReport().getPath());
    }

    public void cleanUpGeneratedFile(String path) {
        PingerFileUtils.deleteFile(path);
    }
}

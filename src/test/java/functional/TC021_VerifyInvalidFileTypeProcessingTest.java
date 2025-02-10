package functional;

import com.pinger.automation.core.enums.annotations.Defect;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerAppConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC021_VerifyInvalidFileTypeProcessingTest extends BasePingTests {

    @Test()
    @Defect(ids = {"DF_008"})
    @Description("Application prints an error and exits gracefully in case of non JSON file.")
    public void test() {
        String output = AppRunner.runApplication(PingerAppConfig.getPingerExecutable(), PingerAppConfig.getPingerWorkingDirectory(), "invalidFileType.png");
        Assert.assertTrue(output.contains("Failed to parse JSON"), "Application printed error and exit gracefully.");
    }
}
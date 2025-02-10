package functional;

import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerAppConfig;
import com.pinger.automation.utils.annotations.Defect;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC020_VerifyBrokenJsonProcessingTest extends BasePingTests {

    @Test()
    @Defect(ids = {"DF_006"})
    @Description("Application prints an error and exits gracefully in case of broken JSON file structure.")
    public void test() {
        String output = AppRunner.runApplication(PingerAppConfig.getPingerExecutable(), PingerAppConfig.getPingerWorkingDirectory(), "brokenConfig.json");
        Assert.assertTrue(output.contains("Failed to parse JSON"), "Application printed error and exit gracefully.");
    }
}
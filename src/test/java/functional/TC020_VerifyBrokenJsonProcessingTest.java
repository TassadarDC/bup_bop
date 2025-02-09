package functional;

import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerAppConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC020_VerifyBrokenJsonProcessingTest extends BasePingTests {

    @Test()
    @Description("Application prints an error and exits gracefully in case of broken JSON file structure.")
    public void test() {
        String output = AppRunner.runApplication(PingerAppConfig.getPingerExecutable(), PingerAppConfig.getPingerWorkingDirectory(), "brokenConfig.json");
        Assert.assertTrue(output.contains("Failed to parse JSON: unexpected end of JSON input"), "Application printed error and exit gracefully.");
    }
}
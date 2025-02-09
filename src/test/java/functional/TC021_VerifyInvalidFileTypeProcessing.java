package functional;

import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC021_VerifyInvalidFileTypeProcessing extends BasePingTest {

    @Test
    @Description("Application prints an error and exits gracefully in case of non JSON file.")
    public void test() {
        String output = AppRunner.runApplication(PingerConfig.getPingerExecutable(), PingerConfig.getPingerWorkingDirectory(), "invalidFileType.png");
        Assert.assertTrue(output.contains("Failed to parse JSON"), "Application printed error and exit gracefully.");
    }
}
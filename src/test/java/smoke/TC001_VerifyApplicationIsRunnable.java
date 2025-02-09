package smoke;

import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_VerifyApplicationIsRunnable {

    @Test
    @Description("Verify application usage message when no arguments are provided")
    public void test() {
        String output = AppRunner.runApplication(PingerConfig.getPingerExecutable(), PingerConfig.getPingerWorkingDirectory());

        Assert.assertNotNull(output, "Application provides output.");
        Assert.assertTrue(output.contains("Usage: [path_to_json_file] [path_to_result_file]"), "Application is executable.");
    }
}

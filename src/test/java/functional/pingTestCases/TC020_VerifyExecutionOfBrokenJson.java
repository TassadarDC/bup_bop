package functional.pingTestCases;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.entites.dto.input.PingerInputDataFile;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC020_VerifyExecutionOfBrokenJson extends BasePingTests {

    @Test
    @Description("Application prints an error and exits gracefully in case of broken JSON file structure.")
    public void test() {
        String output = AppRunner.runApplication(PingerConfig.getPingerExecutable(), PingerConfig.getPingerWorkingDirectory(), "brokenConfig.json");
        Assert.assertTrue(output.contains("Failed to parse JSON: unexpected end of JSON input"), "Application printed error and exit gracefully.");
    }
}
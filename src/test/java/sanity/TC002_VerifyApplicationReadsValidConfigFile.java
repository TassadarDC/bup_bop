package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerAppConfig;
import functional.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_VerifyApplicationReadsValidConfigFile extends BasePingTests {
    private String configName;

    @BeforeClass
    public void beforeClass() {
        TestDataDto testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
        configName = testDataDto.getConfig().getName();
    }


    @Test
    @Description("Application successfully loads configuration JSON and exits without errors.")
    public void verifyApplicationReadsValidConfigFile() {
        String output = AppRunner.runApplication(PingerAppConfig.getPingerExecutable(), PingerAppConfig.getPingerWorkingDirectory(), configName);
        Assert.assertTrue(output.contains(String.format("Loading configuration from %s..", configName)), "Application successfully runs with only input file provided.");
        cleanUpGeneratedFile(PingerAppConfig.getPingerWorkingDirectory() + configName);
    }
}

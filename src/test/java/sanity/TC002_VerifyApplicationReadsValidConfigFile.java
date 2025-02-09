package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_VerifyApplicationReadsValidConfigFile {
    private String inputFileName;

    @BeforeClass
    public void beforeClass() {
        TestDataDto testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
        inputFileName = testDataDto.getInputDataFile().getName();
    }


    @Test
    @Description("Application successfully loads configuration JSON and exits without errors.")
    public void verifyApplicationReadsValidConfigFile() {
        String output = AppRunner.runApplication(PingerConfig.getPingerExecutable(), PingerConfig.getPingerWorkingDirectory(), inputFileName);
        Assert.assertTrue(output.contains(String.format("Loading configuration from %s..", inputFileName)), "Application successfully runs with only input file provided.");
    }
}

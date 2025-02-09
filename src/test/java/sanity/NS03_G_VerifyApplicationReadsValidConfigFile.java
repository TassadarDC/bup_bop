package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoints;
import com.pinger.automation.utils.AppRunner;
import com.pinger.automation.utils.PingerConfig;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NS03_G_VerifyApplicationReadsValidConfigFile {
    @Test
    @Description("NS03_G_VerifyApplicationReadsValidConfigFile")
    public void verifyApplicationReadsValidConfigFile() {
        TestDataDto testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoints.GOOGLE_DNS);
        String inputFileName = testDataDto.getInputDataFile().getName();
        String output = AppRunner.runApplication(PingerConfig.getPingerExecutable(), PingerConfig.getPingerWorkingDirectory(), inputFileName);

        Assert.assertTrue(output.contains(String.format("Loading configuration from %s..", inputFileName)), "Application successfully runs with only input file provided.");
    }
}

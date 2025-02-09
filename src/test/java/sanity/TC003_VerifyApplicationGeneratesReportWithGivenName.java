package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.FileUtils;
import functional.pingTestCases.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC003_VerifyApplicationGeneratesReportWithGivenName extends BasePingTests {
    private TestDataDto testDataDto;

    @BeforeClass
    public void beforeClass() {
        testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
    }

    @Test
    @Description("Application successfully creates file with given name.")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testDataDto).execute();
        File report = FileUtils.getFile(testDataDto.getOutputDataFile().getPath());

        Assert.assertTrue(report.getName().startsWith(testDataDto.getOutputDataFile().getName()));
        cleanUpGeneratedFiles(testDataDto);
    }
}


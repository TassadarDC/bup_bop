package sanity;

import com.pinger.automation.core.factories.TestDataDtoFactory;
import com.pinger.automation.core.helpers.executable.PingerExecutableHelper;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.PingerFileUtils;
import functional.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC003_VerifyApplicationGeneratesReportWithGivenNameTest extends BasePingTests {
    private TestDataDto testDataDto;

    @BeforeClass
    public void beforeClass() {
        testDataDto = TestDataDtoFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
    }

    @Test()
    @Description("Application successfully creates file with given name.")
    public void test() {
        PingerExecutableHelper.getPingerClient(testDataDto).execute();
        File report = PingerFileUtils.getFile(testDataDto.getReport().getPath());

        Assert.assertTrue(report.getName().startsWith(testDataDto.getReport().getName()));
        cleanUpGeneratedFiles(testDataDto);
    }
}


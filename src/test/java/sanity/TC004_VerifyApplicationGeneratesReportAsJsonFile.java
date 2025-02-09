package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.FileUtils;
import functional.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC004_VerifyApplicationGeneratesReportAsJsonFile extends BasePingTests {
    private TestDataDto testDataDto;

    @BeforeClass
    public void beforeClass() {
        testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
    }

    @Test
    @Description("Application successfully creates *.json file.")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testDataDto).execute();
        File report = FileUtils.getFile(testDataDto.getReport().getPath());

        Assert.assertTrue(FileUtils.hasJsonExtension(report));
        cleanUpGeneratedFiles(testDataDto);
    }
}


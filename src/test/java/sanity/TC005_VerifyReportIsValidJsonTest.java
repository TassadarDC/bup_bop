package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.FileUtils;
import com.pinger.automation.utils.JsonUtils;
import functional.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC005_VerifyReportIsValidJsonTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void beforeClass() {
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoint.GOOGLE_DNS);
    }
    @Test()
    @Description("Report file reflects JSON structure.")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).execute();
        File report = FileUtils.getFile(testData.getReport().getPath());

        Assert.assertTrue(JsonUtils.isValidJson(report));
        cleanUpGeneratedFiles(testData);
    }
}

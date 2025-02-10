package sanity;

import com.pinger.automation.core.annotations.Defect;
import com.pinger.automation.core.factories.TestDataDtoFactory;
import com.pinger.automation.core.helpers.executable.PingerExecutableHelper;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.PingerAppConfig;
import functional.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.pinger.automation.utils.JsonSchemaValidator.validateJsonSchema;

public class TC006_VerifyReportStructureTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void beforeClass() {
        ConfigDto configDto = new ConfigDto();
        configDto.setMaxPings(3)
                .setMinSuccessfulPings(2)
                .setEndpoints(List.of(new EndpointDto(Endpoint.GOOGLE_DNS), new EndpointDto(Endpoint.CLOUDFLARE_DNS)));
        testData = TestDataDtoFactory.createTestDataDto(this.getClass(), configDto);
        PingerExecutableHelper.getPingerClient(testData).execute();
    }

    @Test()
    @Defect(ids = {"DF_005"})
    @Description("Report JSON file matches expected schema.")
    public void test() {
        String jsonFilePath = testData.getReport().getPath();
        Assert.assertTrue(validateJsonSchema(jsonFilePath, PingerAppConfig.getPingerJsonSchema()));
        cleanUpGeneratedFiles(testData);
    }
}

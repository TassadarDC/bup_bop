package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
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

import static com.pinger.automation.utils.JsonSchemaValidator.validateJson;

public class TC006_VerifyReportStructureTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void beforeClass() {
        ConfigDto configDto = new ConfigDto();
        configDto.setMaxPings(3)
                .setMinSuccessfulPings(2)
                .setEndpoints(List.of(new EndpointDto(Endpoint.GOOGLE_DNS), new EndpointDto(Endpoint.CLOUDFLARE_DNS)));
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), configDto);
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
    }

    @Test()
    @Description("Report JSON file matches expected schema.")
    public void test() {
        String jsonFilePath = testData.getReport().getPath();
        Assert.assertTrue(validateJson(jsonFilePath, PingerAppConfig.getPingerJsonSchema()));
        cleanUpGeneratedFiles(testData);
    }
}

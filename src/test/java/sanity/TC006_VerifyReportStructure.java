package sanity;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import com.pinger.automation.utils.PingerConfig;
import functional.pingTestCases.BasePingTests;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.pinger.automation.utils.JsonStructureValidator.validateJsonStructure;

public class TC006_VerifyReportStructure extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void beforeClass() {
        InputDataDto inputDto = new InputDataDto();
        inputDto.setMaxPings(3)
                .setMinSuccessfulPings(2)
                .setEndpoints(List.of(new EndpointDto(Endpoint.GOOGLE_DNS), new EndpointDto(Endpoint.CLOUDFLARE_DNS)));
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), inputDto);
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
    }

    @Test
    @Description("Report JSON file matches expected schema.")
    public void test() {
        String jsonFilePath = testData.getOutputDataFile().getPath();
        Assert.assertTrue(validateJsonStructure(jsonFilePath, PingerConfig.getPingerJsonSchema()));
        cleanUpGeneratedFiles(testData);
    }
}

package functional.pingTestCases;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.enums.Endpoints;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class NS01_CloudFlareTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        InputDataDto inputDto = new InputDataDto();
        inputDto.setMaxPings(6).setMinSuccessfulPings(3).setEndpoints(List.of(new EndpointDto(Endpoints.GOOGLE_DNS),
                new EndpointDto(Endpoints.CLOUDFLARE)));
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), inputDto);
    }

    @Test
    @Description("NS01_CloudFlareTest")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
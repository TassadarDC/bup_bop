package functional;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.enums.Endpoint;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC017_VerifyPingerFlowWithDuplicatesTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        ConfigDto configDto = new ConfigDto();
        configDto.setMaxPings(3).setMinSuccessfulPings(1).setEndpoints(List.of(new EndpointDto(Endpoint.GOOGLE_DNS),
                new EndpointDto(Endpoint.GOOGLE_DNS)));
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), configDto);
    }

    @Test(enabled = false)
    @Description("Should not execute pings for duplicated endpoints")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
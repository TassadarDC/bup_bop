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

import java.util.Arrays;
import java.util.List;

public class TC016_VerifyElevenEndpointsProcessing extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        ConfigDto configDto = new ConfigDto();

        List<EndpointDto> list = Arrays.stream(Endpoint.values())
                .map(EndpointDto::new)
                .toList();

        configDto.setMaxPings(3).setMinSuccessfulPings(2).setEndpoints(list);
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), configDto);
    }

    @Test
    @Description("Positive case scenario")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
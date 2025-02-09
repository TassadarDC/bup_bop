package functional;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.enums.Endpoint;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class TC016_VerifyElevenEndpointsProcessing extends BasePingTest {
    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        InputDataDto inputDto = new InputDataDto();

        List<EndpointDto> list = Arrays.stream(Endpoint.values())
                .map(EndpointDto::new)
                .toList();

        inputDto.setMaxPings(3).setMinSuccessfulPings(2).setEndpoints(list);
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), inputDto);
    }

    @Test
    @Description("Positive case scenario")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
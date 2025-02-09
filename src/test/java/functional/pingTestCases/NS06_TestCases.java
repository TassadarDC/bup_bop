package functional.pingTestCases;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.entites.dto.output.OutputDataDto;
import com.pinger.automation.core.model.enums.Endpoints;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

public class NS06_TestCases extends BasePingTests {
    @Test
    @Description("ignoredEndpointTest")
    public void ignoredEndpointTest() {
        //      dto = pingerInputDataFactory.getPingDataDto(Endpoints.GOOGLE_DNS);
//        executeTest(dto);
    }

    @Test
    @Description("minSuccessfulPingsEdgeCaseTest")
    public void minSuccessfulPingsEdgeCaseTest() {
        //      dto = pingerInputDataFactory.getPingDataDto(Endpoints.GOOGLE_DNS_EDGE, 5, 3);
//        executeTest(dto);
    }

    @Test
    @Description("malformedConfigTest")
    public void malformedConfigTest() {
        // This will intentionally create a malformed config by not setting required fields
        OutputDataDto config = new OutputDataDto();
//        executeTest(config);
    }

    @Test
    @Description("multipleConcurrentPingsTest")
    public void multipleConcurrentPingsTest() {
        EndpointDto endpoint1 = new EndpointDto(Endpoints.GOOGLE_DNS);
        EndpointDto endpoint2 = new EndpointDto(Endpoints.CLOUDFLARE);
        EndpointDto endpoint3 = new EndpointDto(Endpoints.UNREACHABLE);

        InputDataDto inputDataDto = new InputDataDto();
        inputDataDto.setMinSuccessfulPings(4);
        inputDataDto.setMaxPings(6);
        inputDataDto.setEndpoints(List.of(endpoint1, endpoint2, endpoint3));


        TestDataDto testDataDto = PingerTestDataFactory.createTestDataDto(this.getClass(), inputDataDto);

        BSL.pingerExecutableHelper.executePinger(testDataDto).processValidScenario();
        cleanUpGeneratedFiles(testDataDto);
    }
}

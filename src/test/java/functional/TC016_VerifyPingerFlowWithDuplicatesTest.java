package functional;

import com.pinger.automation.core.annotations.Defect;
import com.pinger.automation.core.factories.TestDataDtoFactory;
import com.pinger.automation.core.helpers.executable.PingerExecutableHelper;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.config.ConfigDto;
import com.pinger.automation.core.model.enums.Endpoint;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TC016_VerifyPingerFlowWithDuplicatesTest extends BasePingTests {
    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        ConfigDto configDto = new ConfigDto();
        configDto.setMaxPings(3).setMinSuccessfulPings(1).setEndpoints(List.of(new EndpointDto(Endpoint.GOOGLE_DNS),
                new EndpointDto(Endpoint.GOOGLE_DNS)));
        testData = TestDataDtoFactory.createTestDataDto(this.getClass(), configDto);
    }

    @Test()
    @Defect(ids = {"DF_007"})
    @Description("Should not execute pings for duplicated endpoints")
    public void test() {
        PingerExecutableHelper.getPingerClient(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
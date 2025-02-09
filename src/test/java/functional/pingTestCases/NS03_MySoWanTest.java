package functional.pingTestCases;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.enums.Endpoints;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NS03_MySoWanTest extends BasePingTests {

    private TestDataDto testData;

    @BeforeClass
    public void setupTestConfig() {
        testData = PingerTestDataFactory.createTestDataDto(this.getClass(), Endpoints.MY_SO_WAN);
    }

    @Test
    @Description("NS03_MySoWanTest")
    public void test() {
        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();
        cleanUpGeneratedFiles(testData);
    }
}
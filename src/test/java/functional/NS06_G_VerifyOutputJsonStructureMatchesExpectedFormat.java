package functional;

import com.pinger.automation.core.factories.PingerTestDataFactory;
import com.pinger.automation.core.helpers.BSL;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.enums.Endpoints;
import functional.pingTestCases.BasePingTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static com.pinger.automation.utils.JsonStructureValidator.validateJsonStructure;

public class NS06_G_VerifyOutputJsonStructureMatchesExpectedFormat extends BasePingTests {
    @Test
    public void verifyOutputJsonStructureMatchesExpectedFormat() {
        InputDataDto inputDto = new InputDataDto();
        inputDto.setMaxPings(6).setMinSuccessfulPings(3).setEndpoints(List.of(new EndpointDto(Endpoints.GOOGLE_DNS),
                new EndpointDto(Endpoints.CLOUDFLARE)));

        TestDataDto testData = PingerTestDataFactory.createTestDataDto(this.getClass(), inputDto);



        BSL.pingerExecutableHelper.executePinger(testData).processValidScenario();

        String jsonFilePath = testData.getOutputDataFile().getPath();
        String schemaFilePath = "src/main/resources/schemas/reportSchema";

        Assert.assertTrue(validateJsonStructure(jsonFilePath, schemaFilePath));
        cleanUpGeneratedFiles(testData);
    }
}

package com.pinger.automation.core.model.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pinger.automation.core.model.entites.dto.EndpointDto;
import com.pinger.automation.core.model.entites.dto.TestDataDto;
import com.pinger.automation.core.model.entites.dto.input.InputDataDto;
import com.pinger.automation.core.model.entites.dto.output.EntryDto;
import com.pinger.automation.core.model.entites.dto.output.OutputDataDto;
import com.pinger.automation.utils.FileUtils;
import com.pinger.automation.utils.JsonUtils;
import com.pinger.automation.utils.SoftVerifier;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static com.pinger.automation.utils.PingerConfig.getPingerExecutable;
import static com.pinger.automation.utils.PingerConfig.getPingerWorkingDirectory;

@Slf4j
public class PingerClient extends ExecutableClient<OutputDataDto> {
    private static final String EXECUTABLE = getPingerExecutable();
    private static final String WORKING_DIRECTORY = getPingerWorkingDirectory();
    private final TestDataDto TEST_DATA_DTO;

    public PingerClient(TestDataDto dto) {
        super(EXECUTABLE, WORKING_DIRECTORY, dto.getInputDataFile().getName(), dto.getOutputDataFile().getName());
        TEST_DATA_DTO = dto;
    }

    @Override
    public OutputDataDto processValidScenario() {
        OffsetDateTime expectedStartDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        execute();
        OffsetDateTime expectedEndDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        //Get an object with expected data.
        InputDataDto expected = TEST_DATA_DTO.getInputDataFile().getDto();

        //Get an object with actual data after execution
        OutputDataDto actual = FileUtils.parseFileToObject(WORKING_DIRECTORY + TEST_DATA_DTO.getOutputDataFile().getName(), new TypeReference<>() {
        });

        //Check if output file has data in it.
        if (actual.getEntries() == null || actual.getEntries().isEmpty()) {
            Assert.fail("No entries found in result.json");
        }
        log.info("Result file: {}", JsonUtils.toJsonString(actual));
        SoftVerifier verifier = new SoftVerifier();

        //Validate timestamps (doesn't work properly, fails from time to time due to one second edge)
        OffsetDateTime outputStartDateTime = OffsetDateTime.parse(actual.getStartTime());
        OffsetDateTime outputEndDateTime = OffsetDateTime.parse(actual.getEndTime());
        verifier.assertNotEquals(outputStartDateTime, outputEndDateTime, "Verify that start and end time differs.");
        verifier.assertTrue(outputStartDateTime.isBefore(outputEndDateTime), "Verify that start date is before end date.");
        verifier.assertEquals(expectedStartDateTime.truncatedTo(ChronoUnit.SECONDS), outputStartDateTime.truncatedTo(ChronoUnit.SECONDS), "Verity start date time.");
        verifier.assertEquals(expectedEndDateTime.truncatedTo(ChronoUnit.SECONDS), outputEndDateTime.truncatedTo(ChronoUnit.SECONDS), "Verity end date time.");

        //Validate min/max pings
        verifier.assertEquals(actual.getMinSuccessfulPings(), expected.getMinSuccessfulPings(), "Verity min successful pings.");
        verifier.assertTrue(actual.getMaxPings() <= expected.getMaxPings(), "Verity max successful pings is less then successful.");

        //Validate total count of entries (report should contain only values for endpoint where ignore = false)
        //verifier.assertEquals(expected.getEndpoints().stream().filter(e -> !e.isIgnore()).count(), actual.getEntries().size(), "Verify total count of entries."); //BUG

        //Navigate through the list of endpoints to validate all of them.
        for (EntryDto actualEntry : actual.getEntries()) {
            //move this to validate list of expected vs list of actual
            EndpointDto expectedEndpoint = expected.getEndpoints().stream().filter(e -> e.getDescription().equals(actualEntry.getEndpoint().getDescription())).findFirst().orElse(null);

            if (expectedEndpoint == null) {
                log.warn("There is no {} endpoint in output file.", actualEntry.getEndpoint().getDescription());
                break;
            }
            log.warn("Verifying entry: {}.", actualEntry);

            //verifier.assertTrue(actualEntry.getSuccessfulPings() >= expected.getMinSuccessfulPings(), "Assert actual vs minimum successful pings.");
            verifier.assertEquals(actualEntry.getEndpoint(), expectedEndpoint, "Assert endpoint data.");
            verifier.assertTrue(actualEntry.getTotalPings() <= expected.getMaxPings(), "Assert actual total vs maximum successful pings.");

//          Assert actual vs minimum successful pings.
            if (actualEntry.getSuccessfulPings() == 0) {
                log.info("{} is unreachable. Successful pings = 0", actualEntry.getEndpoint().getDescription());
            } else if (actualEntry.getSuccessfulPings() == expected.getMinSuccessfulPings()) {
                log.info("{} is reachable.", actualEntry.getEndpoint().getDescription());
            } else if (actualEntry.getSuccessfulPings() < expected.getMinSuccessfulPings()) {
                log.info("{} failed to get minimum required pings. Needed {}, Actual {}", actualEntry.getEndpoint().getDescription(), expected.getMinSuccessfulPings(), actualEntry.getTotalPings());
            }
        }
        verifier.verifyAll();
        return actual;
    }
}
